package com.ycc.security.config;

import com.ycc.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author ccc
 * @date 2020/06/30
 */
public class YccSecurityConfig extends WebSecurityConfig {

    @Autowired
    UserService userService;

    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        //获取登录用户信息
        return username -> userService.loadUserByUsername(username);
    }


}
