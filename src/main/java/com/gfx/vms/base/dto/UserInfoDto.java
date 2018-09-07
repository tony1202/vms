package com.gfx.vms.base.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tony
 * @date 2018/9/5
 * @Description: session中存储用户的信息
 */
@Data
public class UserInfoDto implements Serializable{
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
