package com.zh.config;

import com.zh.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor //空参有参的构造方法
public class MyUserDetails implements UserDetails {

    private User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {//账户是否到期
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {//是否锁定
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {//账户凭证是否到期
        return true;
    }

    @Override
    public boolean isEnabled() {//是否可用
        return true;
    }
}
