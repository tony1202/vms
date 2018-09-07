package com.gfx.vms.base.constant;

/**
 * @author tony
 * @date 2018/9/6
 * @Description: 系统基础常量
 */
public abstract class VMSConstant {

    /**
     * session中常量
     */
    public interface SessionConstant{
        /**用户信息常量*/
        String USER_INFO = "userInfo";
        /**验证码*/
        String CHECK_CODE = "checkCode";
    }

    /**
     * shiro常量
     */
    public interface ShiroConstant{
        /**shiro cache 缓存*/
        String SESSION_CACHE = "sessionCache";
        /**用户被踢出的标记*/
        String KICK_OUT = "kickOut";
    }
}
