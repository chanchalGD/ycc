package com.ycc.admin.model.bo;

import com.ycc.admin.model.entity.UserAdmin;
import com.ycc.admin.model.entity.UserResource;
import com.ycc.common.hint.UserAdminStatus;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chan
 */
@Data
public class UserAdminDetails implements UserDetails {

    private UserAdmin userAdmin;
    private List<UserResource> userResourceList;
    public UserAdminDetails(UserAdmin userAdmin,List<UserResource> userResourceList) {
        this.userAdmin = userAdmin;
        this.userResourceList = userResourceList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userResourceList.stream()
                .map(role ->new SimpleGrantedAuthority(role.getId()+":"+role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return userAdmin.getPassWord();
    }

    @Override
    public String getUsername() {
        return userAdmin.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return userAdmin.getStatus().equals(UserAdminStatus.NO_EXIST.getStatus());
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
