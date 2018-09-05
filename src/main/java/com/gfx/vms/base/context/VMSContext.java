package com.gfx.vms.base.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author tony
 * @date 2018/9/5
 * @Description: spring上下文
 */
@Component
public class VMSContext implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 通过注册的对象id名来获取对象
     * @param beanName
     * @return
     */
    public static Object getBeanByName(String beanName){
        return applicationContext.getBean(beanName);
    }
}
