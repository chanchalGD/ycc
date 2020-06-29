package com.ycc.controller;

import com.ycc.common.hint.CommonPrompts;
import com.ycc.common.utils.Assert;
import com.ycc.common.vo.Response;
import com.ycc.model.dto.UserLoginDto;
import com.ycc.model.dto.UserRegisterDto;
import com.ycc.model.entity.UserAdmin;
import com.ycc.security.model.JwtAuthenticatioToken;
import com.ycc.security.utils.SecurityUtils;
import com.ycc.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ccc
 * @date 2020/06/29
 */
@RequestMapping("/ums/user")
@RestController
@Slf4j
@Api(tags = "UserController", description = "后台用户管理")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;


    @GetMapping("/authCode")
    public String getAuthCode() {
        return null;
    }

    /**
     * 登陆
     */
    @ApiOperation(value = "用户登陆")
    @PostMapping(value = "/login")
    public Response login(UserLoginDto userLoginDto, HttpServletRequest request) {
        String userName = userLoginDto.getUserName();
        String passWord = userLoginDto.getPassWord();
        // 系统登录认证
        JwtAuthenticatioToken token = SecurityUtils.login(request, userName, passWord, authenticationManager);
        return new Response<>().succcess(token);
    }

    /**
     * 后台管理员注册
     **/
    @ApiOperation(value = "用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Response<UserAdmin> register(@RequestBody @Validated UserRegisterDto userRegisterDto) {
        UserAdmin userAdmin = userService.register(userRegisterDto);
        Assert.isNull(userAdmin,CommonPrompts.REGISTER_FAILED);
        return new Response<UserAdmin>().succcess(userAdmin);
    }


}
