package com.ycc.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ycc.common.exception.ApiException;
import com.ycc.common.hint.CommonPrompts;
import com.ycc.common.hint.QueryElement;
import com.ycc.common.hint.UserAdminStatus;
import com.ycc.common.utils.Assert;
import com.ycc.security.utils.JwtTokenUtils;
import com.ycc.admin.mapper.UserAdminLoginLogMapper;
import com.ycc.admin.mapper.UserAdminMapper;
import com.ycc.admin.model.bo.UserAdminDetails;
import com.ycc.admin.model.dto.UserRegisterDto;
import com.ycc.admin.model.entity.UserAdmin;
import com.ycc.admin.model.entity.UserAdminLoginLog;
import com.ycc.admin.model.entity.UserResource;
import com.ycc.admin.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

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

    @Autowired
    private UserAdminLoginLogMapper userAdminLoginLogMapper;

    @Override
    public final UserAdmin getUserByIdNum(Integer id) {
        return null;
    }

    @Override
    public UserAdmin findByUserName(String userName) {
        // 访问数据库，根据用户名取出用户的实体类
        return null;
    }

    /**
     * 用户登陆
     */
    @Override
    public String login(String username, String password) {
        String token = null;
        UserDetails userDetails = loadUserByUsername(username);
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new ApiException("密码不正确");
        }
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        token = JwtTokenUtils.generateToken(authentication);
        insertLoginLog(username);
        return token;
    }

    /**
     * 用户注册
     */
    @Override
    public UserAdmin userRegister(UserRegisterDto userRegisterDto) {
        UserAdmin userAdmin = new UserAdmin();
        BeanUtils.copyProperties(userRegisterDto, userAdmin);
        userAdmin.setCreateTime(new Date());
        userAdmin.setStatus(UserAdminStatus.NORMAL.getStatus());
        // 检查手机
        registerElementCheck(QueryElement.YCC_ADMIN_PHONE, userAdmin.getPhone());
        // 检查邮箱
        registerElementCheck(QueryElement.YCC_ADMIN_EMAIL, userAdmin.getEmail());
        String encodePassword = passwordEncoder.encode(userAdmin.getPassWord());
        userAdmin.setPassWord(encodePassword);
        userAdminMapper.insert(userAdmin);
        return userAdmin;
    }

    /**
     * 注册判断
     **/
    private void registerElementCheck(String element, Object elementValue) {
        String errorMessage = matchErrorMessage(element);
        int count = userAdminMapper.selectCount(new QueryWrapper<UserAdmin>().eq(element, elementValue).
                and(w -> w.eq(QueryElement.YCC_ADMIN_STATUS, UserAdminStatus.NORMAL.getStatus())));
        // 如果存在（不为0）则抛出error异常信息
        Assert.isNotZero(count, errorMessage);
    }

    private String matchErrorMessage(String element) {
        // 可以搞个缓存
        String errorMessage = "";
        if (QueryElement.YCC_ADMIN_PHONE.equals(element)) {
            errorMessage = CommonPrompts.REGISTER_PHONE_USED;
        } else if (QueryElement.YCC_ADMIN_EMAIL.equals(element)) {
            errorMessage = CommonPrompts.REGISTER_EMAIL_USED;
        }
        return errorMessage;
    }

    /**
     * 根据用户名加载用户信息。
     **/
    @Override
    public UserDetails loadUserByUsername(String userName) {
        //获取用户信息
        UserAdmin userAdmin = getAdminByUsername(userName);
        if (userAdmin != null) {
            List<UserResource> resourceList = getResourceList(userAdmin.getId());
            return new UserAdminDetails(userAdmin, resourceList);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

    /**
     * 根据用户名查出用户信息。
     **/
    @Override
    public UserAdmin getAdminByUsername(String userName) {
        return userAdminMapper.selectOne(new QueryWrapper<UserAdmin>().eq(QueryElement.YCC_ADMIN_USERNAME, userName).
                and(w -> w.eq(QueryElement.YCC_ADMIN_STATUS, UserAdminStatus.NORMAL.getStatus())));
    }

    /**
     * 根据用户id查出该用户拥有的资源。
     **/
    @Override
    public List<UserResource> getResourceList(Long adminId) {
        return userAdminMapper.getResourceListByUserAdminId(adminId);
    }

    /**
     * 根据用户名查出用户，记录登陆日志。
     **/
    @Override
    public void insertLoginLog(String userName) {
        try {
            // 根据用户名查找出用户
            UserAdmin userAdmin = getAdminByUsername(userName);
            Assert.isNull(userAdmin, CommonPrompts.LOGIN_LOG_FAILED);
            UserAdminLoginLog userAdminLoginLog = new UserAdminLoginLog();
            userAdminLoginLog.setAdminId(userAdmin.getId());
            userAdminLoginLog.setCreateTime(new Date());
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = requestAttributes.getRequest();
            userAdminLoginLog.setIp(request.getRemoteHost());
            userAdminLoginLogMapper.insert(userAdminLoginLog);
        } catch (Exception e) {
            throw new ApiException(CommonPrompts.LOGIN_LOG_FAILED, e);
        }

    }


}
