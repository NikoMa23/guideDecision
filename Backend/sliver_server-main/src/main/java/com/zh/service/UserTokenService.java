package com.zh.service;

import com.zh.domain.User_Token;

public interface UserTokenService {
    void saveUserToken(User_Token userToken);
    String saveUserToken(int user_id);
}
