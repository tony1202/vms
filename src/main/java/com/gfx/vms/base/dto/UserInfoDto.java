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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public boolean isFirstLoad() {
        return isFirstLoad;
    }

    public void setFirstLoad(boolean firstLoad) {
        isFirstLoad = firstLoad;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
