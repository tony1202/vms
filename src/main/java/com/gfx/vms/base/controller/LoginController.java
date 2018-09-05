package com.gfx.vms.base.controller;

import com.gfx.vms.base.controller.BaseController;
import com.gfx.vms.base.dto.VMSResponse;
import com.gfx.vms.base.dto.VMSResponseFactory;
import com.gfx.vms.base.util.CaptchaGenerator;
import com.gfx.vms.common.entity.User;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
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
public class LoginController extends BaseController {

    @PostMapping("/login")
    public VMSResponse login(@RequestBody Map<String,Object> user){

        //创建响应
        VMSResponse vmsResponse = VMSResponseFactory.newInstance();
        //获取当前的用户
        Subject currentUser = SecurityUtils.getSubject();
        //判断当前用户是否登录过
        if (currentUser !=null && currentUser.isAuthenticated()){
            String userId = (String) user.get("userId");
            String passWord = (String) user.get("passWord");
        }


        return null;
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
                session.setAttribute("checkCode", checkCodeStr);

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
