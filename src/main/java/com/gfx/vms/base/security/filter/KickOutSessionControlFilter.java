package com.gfx.vms.base.security.filter;

import com.gfx.vms.base.constant.VMSConstant;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author tony
 * @date 2018/9/6
 * @Description: 并发登录人数控制
 */
public class KickOutSessionControlFilter extends AccessControlFilter {
    private static final Logger log = LoggerFactory.getLogger(KickOutSessionControlFilter.class);
    /**
     * 踢出后的跳转的地址
     */
    private String kickOutUrl;
    /**
     * 是否踢出后登录的用户
     */
    private boolean kickOutAfter;
    /**
     * 同一账号能登录的最大数量
     */
    private int maxSessionNum;

    private SessionManager sessionManager;
    private Cache<String, Deque<Serializable>> cache;

    public void setKickOutUrl(String kickOutUrl) {
        this.kickOutUrl = kickOutUrl;
    }

    public void setKickOutAfter(boolean kickOutAfter) {
        this.kickOutAfter = kickOutAfter;
    }

    public void setMaxSessionNum(int maxSessionNum) {
        this.maxSessionNum = maxSessionNum;
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public void setCache(CacheManager cacheManager) {
        this.cache = cacheManager.getCache(VMSConstant.ShiroConstant.SESSION_CACHE);
    }

    /**
     * 是否允许访问
     *
     * @param request
     * @param response
     * @param mappedValue
     * @return
     * @throws Exception
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return false;
    }

    /**
     * 表示访问拒绝时是否自己处理，如果返回true表示自己不处理且继续拦截器链执行，
     * false表示访问拒绝,自己处理(重定向),
     * 根据 isAccessAllowed 方法的返回值.
     *
     * @param request  请求
     * @param response 响应
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        //如果用户没有登录,则继续后面的流程
        Subject subject = getSubject(request, response);
        if (!subject.isAuthenticated() || !subject.isRemembered()) {
            return true;
        }
        //判断当前用户是否超过数量
        Session session = subject.getSession();
        //获取用户名(实际存储的是用户id)
        String userName = (String) subject.getPrincipal();
        Serializable sessionId = session.getId();
        //初始化用户队列,将用户放入队列中
        Deque<Serializable> deque = cache.get(userName);
        if (deque == null) {
            deque = new LinkedList<>();
            //进行压栈
            cache.put(userName, deque);
        }

        //如果队列中没有此用户的 sessionId 且用户没有被踢出，则放入队列
        if (!deque.contains(sessionId) && session.getAttribute(VMSConstant.ShiroConstant.KICK_OUT) == null) {
            deque.push(sessionId);
        }

        //若队列中的 sessionId 是否超出最大会话数目， 则踢出用户
        while (deque.size() > maxSessionNum) {
            Serializable kickOutSessionId;
            if (kickOutAfter) {
                kickOutSessionId = deque.removeFirst();
            } else {
                kickOutSessionId = deque.removeLast();
            }

            // 设置 sessionId 对应的 session 中的字段，表示该用户已经被踢出
            Session kickOutSession = null;
            try {
                kickOutSession = sessionManager.getSession(new DefaultSessionKey(kickOutSessionId));
            } catch (SessionException e) {
                log.warn("kickOut session error -->{}",e.getMessage());
            }
            if (kickOutSession != null) {
                kickOutSession.setAttribute(VMSConstant.ShiroConstant.KICK_OUT, true);
            }
        }

        // 如果当前登陆用户被踢出，则退出并跳转
        Object kickOut = session.getAttribute(VMSConstant.ShiroConstant.KICK_OUT);
        if (kickOut != null && Boolean.TRUE.equals(kickOut)) {
            try {
                //登出
                subject.logout();
                // 根据请求类型作出处理
                HttpServletRequest httpServletRequest = (HttpServletRequest) request;
                HttpServletResponse httpServletResponse = (HttpServletResponse) response;
                //判断请求类型
                if (!StringUtils.equalsIgnoreCase("XMLHttpRequest",httpServletRequest.getHeader("X-Requested-with"))){
                    //普通请求 -- 重定向
                    WebUtils.issueRedirect(request,response,kickOutUrl);
                }else {
                    //ajax请求 -- 设置状态码
                    httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
                }
            } catch (IOException e) {
                log.warn("kick out user error -->{}",e.getMessage());
            }
            return false;
        }

        return true;
    }
}
