package com.gfx.vms.base.security.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author tony
 * @date 2018/9/10
 * @Description: 页面重定向
 */
@Controller
public class PageForwardController {
    /**
     * 登录重定向
     * @return 登录页面
     */
    @RequestMapping("/login")
    public String loginPageForward(){
        //判断用户是否已经登录
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()){
            return "view/mainPage";
        }
        return "view/login";
    }

    /**
     * 重定向主页
     * @return 主页页面
     */
    @GetMapping("/vms")
    public String mainPageForward(){

        return "view/mainPage";
    }
}
