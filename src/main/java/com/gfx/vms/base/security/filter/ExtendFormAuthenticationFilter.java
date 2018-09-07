package com.gfx.vms.base.security.filter;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author tony
 * @date 2018/9/6
 * @Description: 扩展 FormAuthenticationFilter ，对部分方法重写，使其支持 Ajax 请求
 */
public class ExtendFormAuthenticationFilter extends FormAuthenticationFilter {
    private static final Logger log = LoggerFactory.getLogger(ExtendFormAuthenticationFilter.class);

    /**
     * 当访问被拒后的处理;如果返回true,表示自己不处理,继续向下执行;返回false,表示自己处理,不执行原有顺序
     *
     * @param request  请求
     * @param response 响应
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        //判断请求类型
        if (!StringUtils.equalsIgnoreCase("XMLHttpServletRequest", httpServletRequest.getHeader("X-Requested-with"))) {
            //普通请求

            if (this.isLoginRequest(request, response)) {//登录请求
                if (this.isLoginSubmission(request, response)) { //提交POST类型请求
                    if (log.isTraceEnabled()) {
                        log.trace("Login submission detected.  Attempting to execute login.");
                    }
                    //继续执行登录
                    return this.executeLogin(request, response);
                } else {
                    if (log.isTraceEnabled()) {
                        log.trace("Login page view.");
                    }

                    return true;
                }
            } else {
                if (log.isTraceEnabled()) {
                    log.trace("Attempting to access a path which requires authentication.  " +
                            "Forwarding to the Authentication url [" + this.getLoginUrl() + "]");
                }
                //进行重定向认证
                this.saveRequestAndRedirectToLogin(request, response);
                return false;
            }
        } else {//ajax请求
            //返回状态码403,权限不够
            httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return false;
        }
    }

    /**
     * 当成功登录时
     *
     * @param token    认证信息
     * @param subject  用户信息
     * @param request  请求
     * @param response 响应
     * @return true:继续执行;false:不继续执行
     * @throws Exception
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        if (!StringUtils.equalsIgnoreCase("XMLHttpServletRequest", httpServletRequest.getHeader("X-Requested-with"))) {
            //普通请求
            this.issueSuccessRedirect(request, response);
        } else {
            //ajax请求
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        }
        return false;
    }

    /**
     * 当登录失败时
     *
     * @param token    认证信息
     * @param e        认证异常
     * @param request  请求
     * @param response 响应
     * @return
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        if (log.isDebugEnabled()){
            log.debug("Authentication Exception -->{}",e.getMessage());
        }
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        if (!StringUtils.equalsIgnoreCase("XMLHttpServletRequest",httpServletRequest.getHeader("X-Requested-with"))){
            //普通请求
            this.setFailureAttribute(request,e); //设置登录失败属性
            return true;
        }else {//ajax
            //登录失败,设置响应码为403
            httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return false;
        }
    }
}
