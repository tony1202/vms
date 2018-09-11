package com.gfx.vms.base.context;

import com.gfx.vms.base.constant.VMSConstant;
import com.gfx.vms.base.dto.UserInfoDto;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * @author tony
 * @date 2018/9/11
 * @Description:
 */
@Component
public class UserContextHolder {

    private static final RequestAttributes attributes = RequestContextHolder.getRequestAttributes();

    /**
     * 获取存储在session中的用户信息
     * @return 用户信息实体
     */
    public static UserInfoDto getUserInfo(){
        UserInfoDto userInfo = new UserInfoDto();
        //获取session中用户信息
        userInfo = (UserInfoDto) attributes.getAttribute(VMSConstant.SessionConstant.USER_INFO, RequestAttributes.SCOPE_SESSION);

        return userInfo;
    }

}
