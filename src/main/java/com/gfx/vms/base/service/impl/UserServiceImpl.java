package com.gfx.vms.base.service.impl;

import com.gfx.vms.base.dto.UserInfoDto;
import com.gfx.vms.base.service.UserService;
import com.gfx.vms.common.dao.mapper.RoleMapper;
import com.gfx.vms.common.dao.mapper.UserMapper;
import com.gfx.vms.common.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tony
 * @date 2018/9/6
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    /**
     * 根据userId 获取用户信息
     *
     * @param userId 用户id
     * @return
     */
    @Override
    public UserInfoDto getUserInfo(String userId) {
        UserInfoDto userInfo = new UserInfoDto();
        User user = userMapper.selectByPrimaryKey(userId);
        //如果id查不到用户,就表示用户不存在,返回null
        if (user==null){
            return null;
        }
        userInfo.setUserId(userId);
        userInfo.setUserName(user.getUserName());
        userInfo.setPassWord(user.getPassWord());
        userInfo.setRoles(roleMapper.getRolesByUserId(userId));
        return userInfo;
    }
}
