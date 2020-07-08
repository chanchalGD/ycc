package com.ycc.admin.controller;

import com.ycc.admin.model.vo.UserLoginVo;
import com.ycc.admin.model.vo.UserRegisterVo;
import com.ycc.common.hint.CommonPrompts;
import com.ycc.common.utils.Assert;
import com.ycc.common.vo.Response;
import com.ycc.admin.model.dto.UserLoginDto;
import com.ycc.admin.model.dto.UserRegisterDto;
import com.ycc.admin.model.entity.UserAdmin;
import com.ycc.admin.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public Response<UserLoginVo> login(UserLoginDto userLoginDto) {
        String userName = userLoginDto.getUserName();
        String passWord = userLoginDto.getPassWord();
        // 系统登录认证
        String token = userService.login(userName, passWord);
        UserLoginVo userLoginVo = new UserLoginVo();
        userLoginVo.setToken(token);
        return new Response<UserLoginVo>().succcess(userLoginVo);
    }

    /**
     * 后台管理员注册
     **/
    @ApiOperation(value = "用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Response<UserRegisterVo> register(@RequestBody @Validated UserRegisterDto userRegisterDto) {
        UserAdmin userAdmin = userService.userRegister(userRegisterDto);
        Assert.isNull(userAdmin, CommonPrompts.REGISTER_FAILED);
        UserRegisterVo userRegisterVo = new UserRegisterVo();
        userRegisterVo.setUserAdmin(userAdmin);
        return new Response<UserRegisterVo>().succcess(userRegisterVo);
    }

}
