package com.gfx.vms.base.service;

import com.gfx.vms.base.dto.UserInfoDto;

/**
 * @author tony
 * @date 2018/9/6
 * @Description:
 */
public interface UserService {
    /**
     * 根据userId 获取用户信息
     * @param userId 用户id
     * @return
     */
    UserInfoDto getUserInfo(String userId);
}
