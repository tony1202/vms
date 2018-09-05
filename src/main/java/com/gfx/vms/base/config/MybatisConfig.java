package com.gfx.vms.base.config;


import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author tony
 * @date 2018/9/4
 * @Description:
 */
@Configuration
@MapperScan(basePackages = {"com.gfx.vms.common.dao.mapper"})
public class MybatisConfig {
}
