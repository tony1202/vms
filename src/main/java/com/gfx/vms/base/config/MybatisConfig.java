package com.gfx.vms.base.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author tony
 * @date 2018/9/4
 * @Description:
 */
@Configuration
@MapperScan(basePackages = "com.gfx.vms.common.dao.mapper")
public class MybatisConfig {
}
