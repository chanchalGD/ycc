package com.ycc.controller;

import com.ycc.common.vo.Response;
import com.ycc.model.dto.UserDto;
import com.ycc.model.dto.UserLoginDto;
import com.ycc.security.model.JwtAuthenticatioToken;
import com.ycc.security.utils.SecurityUtils;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/ums/user")
@RestController
@Slf4j
@Api(tags = "UserController", description = "后台用户管理")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * 后台系统用户注册
     **/
    @PostMapping("/register")
    public Response registerUser(@RequestBody UserDto userDto){
        return null;
    }


    @GetMapping("/authCode")
    public String getAuthCode(){
        return null;
    }

    /**
     * 登陆
     * */
    @PostMapping(value = "/login")
    public Response login(UserLoginDto userLoginDto, HttpServletRequest request){
        String userName = userLoginDto.getUserName();
        String passWord = userLoginDto.getPassWord();
        // 系统登录认证
        JwtAuthenticatioToken token = SecurityUtils.login(request, userName, passWord, authenticationManager);
        return new Response().succcess(token);
    }

}
