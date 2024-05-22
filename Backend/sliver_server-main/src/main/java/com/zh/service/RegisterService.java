package com.zh.service;

import com.zh.domain.AuthCode;
import com.zh.domain.ResponseResult;
import com.zh.domain.User;

public interface RegisterService {
    ResponseResult<Object> register(User user);

    ResponseResult<Object> attemptRegister(User user);

    ResponseResult<Object> registerWithCode(User user);
}
