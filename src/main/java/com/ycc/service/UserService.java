package com.ycc.service;

import com.ycc.model.entity.User;

public interface UserService {
    User getUserByIdNum(Integer id);

    User findByUserName(String userName);

    /**
     * 登录功能
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    String login(String username,String password);
}
