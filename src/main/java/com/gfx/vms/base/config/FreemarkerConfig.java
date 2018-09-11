package com.gfx.vms.base.config;

import com.jagregory.shiro.freemarker.ShiroTags;
import freemarker.template.TemplateException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.IOException;

/**
 * @author tony
 * @date 2018/9/7
 * @Description:
 */
@Configuration
public class FreemarkerConfig {

    /**
     * freemarker配置
     * @return
     */
    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer() throws IOException, TemplateException {
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setTemplateLoaderPath("classpath:/templates/");
        freemarker.template.Configuration configuration = freeMarkerConfigurer.createConfiguration();
        configuration.setDefaultEncoding("UTF-8");
        //添加shiro标签 在页面中使用<@shiro.xxx>
        configuration.setSharedVariable("shiro",new ShiroTags());
        freeMarkerConfigurer.setConfiguration(configuration);

        return freeMarkerConfigurer;
    }
}
