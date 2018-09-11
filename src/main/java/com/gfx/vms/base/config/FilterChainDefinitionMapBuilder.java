package com.gfx.vms.base.config;

import com.gfx.vms.common.dao.mapper.RolePermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;

/**
 * @author tony
 * @date 2018/9/7
 * @Description: 获取URL权限工厂类
 */
@Component
public class FilterChainDefinitionMapBuilder {

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    /**
     * 获取授权信息
     *
     * @return 返回授权map
     */
    public static LinkedHashMap<String, String> builderFilterChainDinitionMap() {
        LinkedHashMap<String, String> permissionMap = new LinkedHashMap<String, String>() {{
            //配置固定权限
            put("/css/**", "anon");//不需要权限
            put("/js/**", "anon");//不需要权限
            put("/fonts/**", "anon");//不需要权限
            put("/media/**", "anon");//不需要权限
            put("/page/**", "anon");//不需要权限
            put("/errorPage/**", "anon");//不需要权限
            put("/login","anon,kickOut");
            put("/account/**","anon");
            //put("/account/checkCode/**","anon");
        }};
        //其他可变权限
        permissionMap.put("/**","authc");

        return permissionMap;
    }

}
