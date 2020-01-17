package com.cqc.portal.config;


import com.cqc.portal.service.LoginRegisterService;
import com.cqc.security.config.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * mall-security模块相关配置
 *
 * @author macro
 * @date 2020/01/16
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class CqcSecurityConfig extends SecurityConfig {

    @Autowired
    private LoginRegisterService service;

    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        //获取登录用户信息
        return username -> service.loadUser(username);
    }


}
