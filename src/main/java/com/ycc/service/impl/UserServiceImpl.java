package com.ycc.service.impl;

import com.ycc.model.dto.UserRegisterDto;
import com.ycc.model.entity.UserAdmin;
import com.ycc.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl  implements UserService {

    @Override
    public final UserAdmin getUserByIdNum(Integer id) {
        return null;
    }

    @Override
    public UserAdmin findByUserName(String userName) {
        // 访问数据库，根据用户名取出用户的实体类
        //
        return null;
    }

    @Override
    public String login(String username, String password) {
        return null;
    }

    @Override
    public UserAdmin register(UserRegisterDto userRegisterDto) {
        return null;
    }
}
