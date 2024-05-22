package com.zh.service.Impl;

import com.zh.dao.User_TokenDao;
import com.zh.domain.User_Token;
import com.zh.service.UserTokenService;
import com.zh.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserTokenServiceImpl implements UserTokenService {

    @Autowired
    User_TokenDao userTokenDao;

    @Override
    public String saveUserToken(int user_id) {
        //没有必要更新token
        String jwt = JwtUtils.generateJwtToken(Integer.toString(user_id));
        User_Token userToken = new User_Token(user_id, jwt);
        userTokenDao.insert(userToken);
        return jwt;
    }

    @Override
    public void saveUserToken(User_Token userToken) {
            userTokenDao.insert(userToken);
    }
}
