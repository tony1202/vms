package com.gfx.vms.base.security.controller;

import com.gfx.vms.base.constant.VMSConstant;
import com.gfx.vms.base.controller.BaseController;
import com.gfx.vms.base.dto.UserInfoDto;
import com.gfx.vms.base.dto.VMSResponse;
import com.gfx.vms.base.dto.VMSResponseFactory;
import com.gfx.vms.base.service.SystemLogService;
import com.gfx.vms.base.util.CaptchaGenerator;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

/**
 * @author tony
 * @date 2018/9/5
 * @Description:
 */
@RestController("/account")
public class AccessController extends BaseController {

    @Autowired
    private SystemLogService systemLogService;

    //与登录相对应的参数
    private static final String USER_ID = "id";
    private static final String USER_NAME = "userName";
    private static final String USER_PASSWORD = "password";

    @PostMapping("/login")
    public Map<String,Object> login(@RequestBody Map<String,Object> user){

        //创建响应
        VMSResponse vmsResponse = VMSResponseFactory.newInstance();
        //默认失败
        String result = vmsResponse.RESPONSE_RESULT_ERROR;
        //登录结果消息
        String  msg= "";

        //获取当前的用户
        Subject currentUser = SecurityUtils.getSubject();
        //判断当前用户是否登录过
        if (currentUser !=null && !currentUser.isAuthenticated()){
            String userId = (String) user.get(USER_ID);
            String passWord = (String) user.get(USER_PASSWORD);
            //获取session
            Session session = currentUser.getSession();
            //创建认证token
            UsernamePasswordToken token = new UsernamePasswordToken(userId,passWord);

            try {
                //执行登录
                currentUser.login(token);
                //设置session用户其他信息
                UserInfoDto userInfo = (UserInfoDto) session.getAttribute(VMSConstant.SessionConstant.USER_INFO);
                //设置登录ip
                userInfo.setIp(session.getHost());
                //登录成功
                result = VMSResponse.RESPONSE_RESULT_SUCCESS;

                //记录登录日志
                systemLogService.addAccessRecord(userInfo.getUserId(),userInfo.getIp(),SystemLogService.ACCESS_TYPE_LOGIN);

            } catch (AuthenticationException e) {
                log.warn("authentication error ->{}",e.getMessage());
                msg = "the user not exist";
            } catch (InvalidSessionException e) {
                log.warn("session error ->{}",e.getMessage());
                msg = "session error";
            } finally {
                //如果登录失败,情况session中userInfo
                if (StringUtils.equals(result,VMSResponse.RESPONSE_RESULT_ERROR)){
                    session.setAttribute(VMSConstant.SessionConstant.USER_INFO,null);
                }
            }

        }else {//已经登入
            msg = "already login";
        }
        vmsResponse.setResponseBodyMsg(msg);
        vmsResponse.setResponseBodyResult(result);

        return vmsResponse.generateResponseBody();
    }


    /**
     * 获取图形码
     *
     * @param response
     */
    @RequestMapping("/checkCode/{time}")
    public void createCheckImage(@PathVariable String time, HttpServletResponse response, HttpServletRequest request) {

        BufferedImage checkCodeImage = null;
        String checkCodeStr = null;
        //生成验证码图片
        Map<String, Object> checkCode = CaptchaGenerator.generateCaptcha();
        if (MapUtils.isNotEmpty(checkCode)) {
            checkCodeStr = (String) checkCode.get("captchaString");
            checkCodeImage = (BufferedImage) checkCode.get("captchaImage");
        }

        if (StringUtils.isNotBlank(checkCodeStr) && checkCodeImage != null) {
            try {
                HttpSession session = request.getSession();
                session.setAttribute(VMSConstant.SessionConstant.CHECK_CODE, checkCodeStr);

                //止浏览器从本地机的缓存中调阅页面内容，设定后一旦离开网页就无法从Cache中再调出
                response.setHeader("Pragma", "no-cache");
                //设置浏览器缓存控制
                response.setHeader("Cache-Control", "no-cache");
                //设定网页的到期时间，一旦过期则必须到服务器上重新调用
                response.setDateHeader("Expires", 0);
                //设置图片格式
                response.setContentType("image/png");


                ImageIO.write(checkCodeImage, "png", response.getOutputStream());
            } catch (IOException e) {
                log.info("fail in checkCodeImage outputStream ->{}",e.getMessage());
            }

        }


    }
}
