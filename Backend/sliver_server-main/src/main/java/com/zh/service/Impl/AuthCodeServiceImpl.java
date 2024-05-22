package com.zh.service.Impl;

import com.zh.dao.AuthCodeDao;
import com.zh.domain.AuthCode;
import com.zh.domain.ResponseResult;
import com.zh.service.AuthCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
public class AuthCodeServiceImpl implements AuthCodeService {

    @Autowired
    AuthCodeDao authCodeDao;

    private static final long EXPIRATION_TIME =  10*60*1000; //验证码10分钟过期

    @Override
    public ResponseResult<Object> verifyAuthCode(AuthCode authCode) {

        //传入的验证码对象是否为空
        if(Objects.isNull(authCode)){
            return new ResponseResult<>(ResponseResult.IllegalAction,"你在干嘛?",null);
        }
//        AuthCode authCodeInSql = authCodeDao.getByPhone(authCode.getPhone());
        AuthCode authCodeInSql = authCodeDao.getByPhone(authCode.getPhone());
        //是否发送验证码
        if(Objects.isNull(authCodeInSql)){
            return new ResponseResult<>(ResponseResult.IllegalAction,"未发送验证码",null);
        }
        //验证码是否错误
        if(!Objects.equals(authCode.getAuthCode(), authCodeInSql.getAuthCode())){
            return new ResponseResult<>(ResponseResult.WrongAuthCode,"验证码错误",null);
        }
        //是否过期
        if(new Date(Long.parseLong(authCodeInSql.getExpirationTime())).before(new Date())){
            return new ResponseResult<>(ResponseResult.AuthCodeOutdated,"验证码超时",null);
        }
        //验证通过,删除验证码
        authCodeDao.deleteByPhoneNum(authCode);
        return null;
    }

    @Override
    public void saveAuthCode(AuthCode authCode) {
        String phone = authCode.getPhone();
        if(Objects.isNull(authCodeDao.getByPhone(phone))){
            authCodeDao.insert(authCode);
        }else{
            authCodeDao.update(authCode);
        }
    }

    @Override
    public void saveAuthCode(String phoneNumber,String authCode) {
        long expirationTime = System.currentTimeMillis() + EXPIRATION_TIME;
        String expirationTime2 = Long.toString(expirationTime);
        if(Objects.isNull(authCodeDao.getByPhone(phoneNumber))){
            authCodeDao.insert(new AuthCode(phoneNumber,authCode,expirationTime2));
        }else{
            authCodeDao.update(new AuthCode(phoneNumber,authCode,expirationTime2));
        }
    }
}
