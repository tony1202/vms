package com.gfx.vms.base.config;

import com.gfx.vms.base.security.filter.ExtendFormAuthenticationFilter;
import com.gfx.vms.base.security.filter.KickOutSessionControlFilter;
import com.gfx.vms.base.security.realms.UserAuthorizingRealm;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.ExecutorServiceSessionValidationScheduler;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.SessionValidationScheduler;
import org.apache.shiro.session.mgt.ValidatingSessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author tony
 * @date 2018/9/7
 * @Description:
 */
@Configuration
public class ShiroConfig {
    /**
     * shiro 核心配置 securityManager
     * @return
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager(UserAuthorizingRealm realm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //开启shiro的ehcache缓存管理
        securityManager.setCacheManager(cacheManager());
        //配置realm
        securityManager.setRealm(realm);
        //配置sessionManager
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    /**
     * shiro过滤器配置
     * @return
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        //配置安全管理器
        shiroFilter.setSecurityManager(securityManager);

        /******************添加自定义的过滤器******************/
        //配置并发登录人数控制
        KickOutSessionControlFilter kickOutSessionControlFilter = new KickOutSessionControlFilter();
        kickOutSessionControlFilter.setCache(cacheManager());
        kickOutSessionControlFilter.setSessionManager(sessionManager());
        //同一账号登录踢出前面登录的
        kickOutSessionControlFilter.setKickOutAfter(false);
        //同一账号最多可登录的人数
        kickOutSessionControlFilter.setMaxSessionNum(1);
        //踢出跳转的路径
        kickOutSessionControlFilter.setKickOutUrl("/login");

        ExtendFormAuthenticationFilter extendFormAuthenticationFilter = new ExtendFormAuthenticationFilter();
        extendFormAuthenticationFilter.setUsernameParam("userName");
        extendFormAuthenticationFilter.setPasswordParam("passWord");
        extendFormAuthenticationFilter.setRememberMeParam("rememberMe");
        extendFormAuthenticationFilter.setLoginUrl("/login");

        shiroFilter.setFilters(new HashMap<String,Filter>(){{
            put("kickOut",kickOutSessionControlFilter);
            put("authc",extendFormAuthenticationFilter);
        }});

        /************设置shiro过滤链****/
       shiroFilter.setFilterChainDefinitionMap(FilterChainDefinitionMapBuilder.builderFilterChainDinitionMap());
        return shiroFilter;
    }

    /**
     * Shiro生命周期处理器
     * @return
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    /**
     *shiro与spring的整合
     * @return
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * shiro 缓存管理
     * @return
     */
    @Bean(name = "cacheManager")
    public EhCacheManager cacheManager(){
        EhCacheManager cacheManager = new EhCacheManager();
        cacheManager.setCacheManagerConfigFile("classpath:config/ehcache.xml");
        return cacheManager;
    }

    /**
     * session manager
     * @return DefaultWebSessionManager
     */
    @Bean(name = "sessionManager")
    public DefaultWebSessionManager sessionManager(){
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        //配置session持久化
        sessionManager.setSessionDAO(sessionDAO());

        //session cookie配置
        SimpleCookie cookie = new SimpleCookie("sid");
        cookie.setMaxAge(-1);
        cookie.setHttpOnly(true);
        sessionManager.setSessionIdCookieEnabled(true);
        sessionManager.setSessionIdCookie(cookie);

        //关闭 URL 的 JSESSIONID 重写
        sessionManager.setSessionIdUrlRewritingEnabled(false);

        //session 超时配置
        //开启session 验证定时器
        sessionManager.setSessionValidationSchedulerEnabled(true);
        //开启删除过期session
        sessionManager.setDeleteInvalidSessions(true);
        //配置验证定时间
        sessionManager.setSessionValidationScheduler(sessionValidationScheduler());

        //配置session监听器
        List<SessionListener> sessionListeners = new ArrayList<>();
        sessionListeners.add(new com.gfx.vms.base.security.listener.SessionListener());
        sessionManager.setSessionListeners(sessionListeners);

        //配置session缓存管理
        sessionManager.setCacheManager(cacheManager());

        return sessionManager;
    }

    /**
     * session 持久化
     * @return
     */
    private SessionDAO sessionDAO(){
        EnterpriseCacheSessionDAO sessionDAO = new EnterpriseCacheSessionDAO();
        //配置session缓存名字
        sessionDAO.setActiveSessionsCacheName("sessionCache");
        //配置sessionId生成器
        sessionDAO.setSessionIdGenerator(new JavaUuidSessionIdGenerator());

        return sessionDAO;
    }

    /**
     * 会话验证定时器
     * @return
     */
    private SessionValidationScheduler sessionValidationScheduler(){
        ExecutorServiceSessionValidationScheduler sessionValidationScheduler = new ExecutorServiceSessionValidationScheduler();
        // 配置定时时间
        sessionValidationScheduler.setInterval(9000);
        //sessionValidationScheduler.setSessionManager(sessionManager());
        return sessionValidationScheduler;
    }
}
