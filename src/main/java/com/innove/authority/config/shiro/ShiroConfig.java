package com.innove.authority.config.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Description shiro总配置文件
 * @Author mlf
 * @data 2020-01-09 17:44
 */
@Configuration
public class ShiroConfig {

    @Autowired
    private MyshiroRealm myshiroRealm;

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<>();
        Map<String, Filter> filters = new LinkedHashMap<String, Filter>();
        filters.put("wechatFilter", new WechatAuthorizationFilter());
//        filters.put("webFilter", new WebAuthorizationFilter());
        shiroFilterFactoryBean.setFilters(filters);
        //放行Swagger2页面
        filterChainDefinitionMap.put("/swagger-ui.html","anon");
        filterChainDefinitionMap.put("/swagger/**","anon");
        filterChainDefinitionMap.put("/webjars/**", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**","anon");
        filterChainDefinitionMap.put("/v2/**","anon");
        filterChainDefinitionMap.put("/static/**", "anon");
        //放行大屏页面
        filterChainDefinitionMap.put("/dashboard/**", "anon");
        //其他
        filterChainDefinitionMap.put("/login","anon");
        filterChainDefinitionMap.put("/getWechatToken","anon");
        filterChainDefinitionMap.put("/wechatLogin/**","anon");
        filterChainDefinitionMap.put("/error","anon");
        filterChainDefinitionMap.put("/getVerCode","anon");
        filterChainDefinitionMap.put("/wechat/**", "wechatFilter");
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        shiroFilterFactoryBean.setUnauthorizedUrl("/unAuth");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 凭证匹配器
     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     * ）
     * @return
     */
    @Bean
    public MyHashedCredentialsMatcher hashedCredentialsMatcher() {
        MyHashedCredentialsMatcher myCredentialsMatcher = new MyHashedCredentialsMatcher();
        myCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
        myCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));
        return myCredentialsMatcher;
    }

    @Bean("securityManager")
    public SecurityManager securityManager(SessionRedisDao sessionRedisDao) {
        DefaultWebSecurityManager def = new DefaultWebSecurityManager();
        myshiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        def.setRealm(myshiroRealm);
        // 自定义session管理 使用redis
        SessionConfig sessionConfig = new SessionConfig();
        sessionConfig.setSessionDAO(sessionRedisDao);
        def.setSessionManager(sessionConfig);
        // 自定义缓存实现 使用redis
        //def.setCacheManager();
        return def;
    }

    @Bean
    public RedisTemplate<String, SimpleSession> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, SimpleSession> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(MySimplSession.class));
        return redisTemplate;
    }

    /**
     *  开启shiro aop注解支持.
     *  使用代理方式;所以需要开启代码支持;
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean(name="simpleMappingExceptionResolver")
    public SimpleMappingExceptionResolver
    createSimpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver r = new SimpleMappingExceptionResolver();
        Properties mappings = new Properties();
        mappings.setProperty("DatabaseException", "databaseError");//数据库异常处理
        mappings.setProperty("UnauthorizedException","403");
        r.setExceptionMappings(mappings);  // None by default
        r.setDefaultErrorView("error");    // No default
        r.setExceptionAttribute("ex");     // Default is "exception"
        //r.setWarnLogCategory("example.MvcLogger");     // No default
        return r;
    }
}
