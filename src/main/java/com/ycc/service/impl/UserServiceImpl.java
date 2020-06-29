package com.ycc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ycc.common.hint.CommonPrompts;
import com.ycc.common.hint.QueryElement;
import com.ycc.common.hint.UserAdminStatus;
import com.ycc.common.utils.Assert;
import com.ycc.mapper.UserAdminMapper;
import com.ycc.model.dto.UserRegisterDto;
import com.ycc.model.entity.UserAdmin;
import com.ycc.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author ccc
 * @date 2020/06/29
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserAdminMapper userAdminMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;


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

    /**
     * 用户注册
     */
    @Override
    public UserAdmin register(UserRegisterDto userRegisterDto) {
        UserAdmin userAdmin = new UserAdmin();
        BeanUtils.copyProperties(userRegisterDto, userAdmin);
        userAdmin.setCreateTime(new Date());
        userAdmin.setStatus(UserAdminStatus.NORMAL.getStatus());
        // 检查手机
        registerElementCheck(QueryElement.YCC_ADMIN_PHONE,userAdmin.getPhone());
        // 检查邮箱
        registerElementCheck(QueryElement.YCC_ADMIN_EMAIL,userAdmin.getEmail());
        String encodePassword = passwordEncoder.encode(userAdmin.getPassWord());
        userAdmin.setPassWord(encodePassword);
        userAdminMapper.insert(userAdmin);
        return userAdmin;
    }

    /**
     * 注册判断
     **/
    private void registerElementCheck(String element,Object elementValue) {
        // 可以搞个缓存
        String errorMessage = "";
        if(QueryElement.YCC_ADMIN_PHONE.equals(element)){
            errorMessage = CommonPrompts.REGISTER_PHONE_USED;
        }else if(QueryElement.YCC_ADMIN_EMAIL.equals(element)){
            errorMessage = CommonPrompts.REGISTER_EMAIL_USED;
        }
        int count = userAdminMapper.selectCount(new QueryWrapper<UserAdmin>().eq(element, elementValue).and(w -> w.eq(QueryElement.YCC_ADMIN_STATUS, UserAdminStatus.NORMAL)));
        // 如果存在（不为0）则抛出error异常信息
        Assert.isNotZero(count,errorMessage);
    }

}
