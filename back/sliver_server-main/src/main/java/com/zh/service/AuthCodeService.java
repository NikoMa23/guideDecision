package com.zh.service;

import com.zh.domain.AuthCode;
import com.zh.domain.ResponseResult;
import com.zh.domain.User_Token;

public interface AuthCodeService {
    void saveAuthCode(AuthCode authCode);

    ResponseResult<Object> verifyAuthCode(AuthCode authCode);

    void saveAuthCode(String phoneNumber,String authCode);
}
