package com.ycc.controller;

import com.ycc.common.vo.Response;
import com.ycc.model.dto.UserDto;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
@Slf4j
@Api(tags = "UserController", description = "后台用户管理")
public class UserController {


    /**
     * @author ccc
     * @param userDto
     * 后台系统用户注册
     **/
    @PostMapping("/register")
    public Response registerUser(@RequestBody UserDto userDto){
        return null;
    }

    /**
     * @author ccc
     *
     **/
    @GetMapping("/authCode")
    public String getAuthCode(){
        return null;
    }
}
