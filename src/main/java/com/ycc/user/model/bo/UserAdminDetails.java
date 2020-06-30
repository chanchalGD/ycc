package com.ycc.user.model.bo;

import com.ycc.user.model.entity.UserAdmin;
import com.ycc.user.model.entity.UserResource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
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
