package com.gfx.vms.base.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tony
 * @date 2018/9/5
 * @Description:
 */
@Data
public class UserInfoDto {
    /**用户id*/
    private String userId;
    /**用户名*/
    private String userName;
    /**密码(已加密)*/
    private String passWord;
    /**是否第一次登录*/
    private boolean isFirstLoad;
    /**登录Ip*/
    private String ip;
    /**角色*/
    private List<String> roles = new ArrayList<>();


}
