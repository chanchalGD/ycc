package com.ycc.user.service;

import com.ycc.user.model.dto.UserRegisterDto;
import com.ycc.user.model.entity.UserAdmin;
import com.ycc.user.model.entity.UserResource;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

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
     * @return UserAdmin
     */
    UserAdmin userRegister(UserRegisterDto userRegisterDto);


    /**
     * 根据账号加载用户
     * @param userName
     * @return UserDetails
     */
    UserDetails loadUserByUsername(String userName);


    /**
     * 根据账号查找用户
     * @param userName
     */
    UserAdmin getAdminByUsername(String userName);


    /**
     * 根据账号查找用户
     * @param adminId
     * @return 生成的JWT的token
     */
    List<UserResource> getResourceList(Long adminId);

    /**
     * 记录用户登陆日志
     * @param userName
     * @return null
     */
    void insertLoginLog(String userName);
}
