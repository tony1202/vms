package com.gfx.vms.base.service;

/**
 * @author tony
 * @date 2018/9/6
 * @Description: 系统操作记录
 */
public interface SystemLogService {
    /**登入操作*/
    String ACCESS_TYPE_LOGIN = "login";
    /**登出操作*/
    String ACCESS_TYPE_LOGOUT = "logout";

    /**
     * 新增用户访问记录
     * @param userId 用户id
     * @param accessIp 用户ip
     * @param accessType 访问类型
     */
    void addAccessRecord(String userId,String accessIp,String accessType);

}
