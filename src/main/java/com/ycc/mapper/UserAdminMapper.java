package com.ycc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ycc.model.entity.UserAdmin;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserAdminMapper extends BaseMapper<UserAdmin> {

    /**
     * 根据手机号查询用户
     **/
    @Select("select * from ycc_admin where phone = #{phone} and status= #{status}")
    UserAdmin getUserByPhone(@Param("phone") String phone, @Param("status") Integer status);

    /**
     * 根据邮箱查询用户
     * **/
    @Select("select * from ycc_admin where phone = #{email} and status= #{status}")
    UserAdmin getUserByEmail(@Param("email") String email, @Param("status") Integer status);

}
