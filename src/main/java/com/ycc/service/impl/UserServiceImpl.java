package com.ycc.service.impl;

import com.ycc.model.entity.User;
import com.ycc.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl  implements UserService {

    @Override
    public final User getUserByIdNum(Integer id) {
        return null;
    }

    @Override
    public User findByUserName(String userName) {
        return null;
    }

    @Override
    public String login(String username, String password) {
        return null;
    }
}
