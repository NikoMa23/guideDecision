package com.zh.service.Impl;

import com.zh.dao.AuthCodeDao;
import com.zh.dao.UserDao;
import com.zh.domain.AuthCode;
import com.zh.domain.ResponseResult;
import com.zh.domain.User;
import com.zh.service.AuthCodeService;
import com.zh.service.LogoutService;
import com.zh.utils.LegalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LogoutServiceImpl implements LogoutService {

    @Autowired
    UserDao userDao;

    @Autowired
    AuthCodeService authCodeService;

    @Autowired
    AuthCodeDao authCodeDao;

    @Autowired
    PasswordEncoder passwordEncoder;


    public ResponseResult<Object> logout(User user) {

        userDao.deleteByUsername(user.getUsername());

        return new ResponseResult<>(ResponseResult.RegisterOk, "注销成功", null);
    }

    @Override
    public ResponseResult<Object> attemptLogout(User user) {
        //接受用户名(手机号)
        if(Objects.isNull(user.getUsername())||Objects.isNull(user.getPassword())){
            //没有用户名  密码
            return new ResponseResult<>(ResponseResult.IllegalAction, "非法操作", null);
        }
        //检查用户传入的号码是否正确
        ResponseResult<Object> verify = LegalUtils.verifyPhone(user.getUsername());
        if(!Objects.isNull(verify)){
            //不合法
            return verify;
        }
        //检查手机号码是否已被注册
        if(Objects.isNull(userDao.getByUsername(user.getUsername()))){
            //未被注册
            return new ResponseResult<>(ResponseResult.IllegalPhoneNum, "手机号未被注册", null);
        }
        //检查密码是否正确
        if(!passwordEncoder.matches(user.getPassword(),
                userDao.getByUsername(user.getUsername()).getPassword())){
            return new ResponseResult<>(ResponseResult.WrongUserOrPassword, "用户名或密码错误", null);
        }

        //向手机号码发送验证码AuthCode
        String authCode = "222";
        //把验证码存入数据库
        authCodeService.saveAuthCode(user.getUsername(),authCode);
        return new ResponseResult<>(ResponseResult.WaitForAuthCode,"等待验证码",null);
    }

    @Override
    public ResponseResult<Object> logoutWithCode(User user) {
        //是否合法输入
        if(Objects.isNull(user.getUsername())||Objects.isNull(user.getAuthCode())||Objects.isNull(user.getPassword())){
            //没有用户名  验证码  密码
            return new ResponseResult<>(ResponseResult.IllegalAction, "非法操作", null);
        }
        //检查用户传入的号码是否正确
        ResponseResult<Object> verify = LegalUtils.verifyPhone(user.getUsername());
        if(!Objects.isNull(verify)){
            //不合法
            return verify;
        }
        //检查手机号码是否已被注册
        if(Objects.isNull(userDao.getByUsername(user.getUsername()))){
            //未被注册
            return new ResponseResult<>(ResponseResult.IllegalPhoneNum, "手机号未被注册", null);
        }
        //检查密码是否正确
        if(!passwordEncoder.matches(user.getPassword(),
                userDao.getByUsername(user.getUsername()).getPassword())){
            return new ResponseResult<>(ResponseResult.WrongUserOrPassword, "用户名或密码错误", null);
        }

        //验证用户携带的authCode和数据库中的是否一致
        String phone = user.getUsername();
        AuthCode authCode = new AuthCode(phone,user.getAuthCode(),null);
        ResponseResult<Object> var = authCodeService.verifyAuthCode(authCode);
        if(!Objects.isNull(var)){
            //验证码error
            return authCodeService.verifyAuthCode(authCode);
        }
        //注销
        return logout(user);
    }
}
