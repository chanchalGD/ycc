package com.ycc.service;

import com.ycc.model.dto.UserRegisterDto;
import com.ycc.model.entity.UserAdmin;

public interface UserService {
    UserAdmin getUserByIdNum(Integer id);

    UserAdmin findByUserName(String userName);

    /**
     * 登录功能
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    String login(String username,String password);

    /**
     * 登录功能
     * @param userRegisterDto
     * @return 生成的JWT的token
     */
    UserAdmin register(UserRegisterDto userRegisterDto);

}
