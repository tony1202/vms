package com.gfx.vms.base.security.listener;

import com.gfx.vms.base.constant.VMSConstant;
import com.gfx.vms.base.dto.UserInfoDto;
import com.gfx.vms.base.service.SystemLogService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author tony
 * @date 2018/9/6
 * @Description: shiro session 监听器,监听session创建,销毁,过期时触发
 */
@Component
public class SessionListener extends SessionListenerAdapter {
    private static final Logger log = LoggerFactory.getLogger(SessionListener.class);

    @Autowired
    private SystemLogService systemLogService;

    /**
     * session创建
     *
     * @param session
     */
    @Override
    public void onStart(Session session) {
        super.onStart(session);
    }

    /**
     * session 销毁,需要记录用户登出
     *
     * @param session
     */
    @Override
    public void onStop(Session session) {
        super.onStop(session);
        sessionDestroyLog(session);
    }

    @Override
    public void onExpiration(Session session) {
        super.onExpiration(session);
        sessionDestroyLog(session);
    }

    /**
     * 当session销毁时,记录用户登出日志
     *
     * @param session
     */
    private void sessionDestroyLog(Session session) {

        if (!SecurityUtils.getSubject().isAuthenticated()) {
            return;
        }
        UserInfoDto userInfo = (UserInfoDto) session.getAttribute(VMSConstant.SessionConstant.USER_INFO);

        if (userInfo != null) {
            //记录登出日志
            try {
                systemLogService.addAccessRecord(userInfo.getUserId(),userInfo.getIp(),SystemLogService.ACCESS_TYPE_LOGOUT);
            } catch (Exception e) {
                log.warn("system logout error ->{}",e.getMessage());
            }
        }

    }


}
