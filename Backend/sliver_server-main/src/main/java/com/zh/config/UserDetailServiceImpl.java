package com.zh.config;

import com.zh.dao.UserDao;
import com.zh.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    //UserDetailService用于提供对用户的认证,返回UserDetails

    @Autowired
    private UserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询用户信息,
        //或者根据邮箱来进行验证
        User user = userDao.getByUsername(username);
        //如果没有查询到用户就抛出异常
        if(Objects.isNull(user)){
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        //TODO查询对应的权限信息

        //把数据封装成UserDetails返回
        return new MyUserDetails(user);
    }
}
