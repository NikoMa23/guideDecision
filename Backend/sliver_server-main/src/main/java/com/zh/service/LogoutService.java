package com.zh.service;


import com.zh.domain.ResponseResult;
import com.zh.domain.User;

public interface LogoutService {
    ResponseResult<Object> logout(User user);

    ResponseResult<Object> attemptLogout(User user);

    ResponseResult<Object> logoutWithCode(User user);
}
