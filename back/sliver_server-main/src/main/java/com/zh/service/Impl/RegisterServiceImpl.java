package com.zh.service.Impl;

import com.zh.dao.AuthCodeDao;
import com.zh.dao.UserDao;
import com.zh.domain.AuthCode;
import com.zh.domain.ResponseResult;
import com.zh.domain.User;
import com.zh.service.RegisterService;
import com.zh.service.UserTokenService;
import com.zh.utils.LegalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserDao userDao;

    @Autowired
    AuthCodeDao authCodeDao;

    @Autowired
    UserTokenService userTokenService;

    @Autowired
    AuthCodeServiceImpl authCodeService;


    @Override
    public ResponseResult<Object> attemptRegister(User user) {
        if(Objects.isNull(user.getUsername())){
            //没有用户名  密码
            return new ResponseResult<>(ResponseResult.IllegalAction, "非法操作", null);
        }
        //接受用户名(手机号)
        //检查用户传入的号码是否正确
        ResponseResult<Object> verify = LegalUtils.verifyPhone(user.getUsername());
        if(!Objects.isNull(verify)){
            //不合法
            return verify;
        }
        //检查密码的合法性
        ResponseResult<Object> var = LegalUtils.verifyPassword(user.getPassword());
        if(!Objects.isNull(var)){
            return var;
        }
        //检测是否已被注册
        if(!Objects.isNull(userDao.getByUsername(user.getUsername()))){
            //已注册
            return new ResponseResult<>(ResponseResult.IllegalPhoneNum, "手机号已注册", null);
        }
        //向手机号码发送验证码AuthCode
        String authCode = "111";
        //把验证码存入数据库
        authCodeService.saveAuthCode(user.getUsername(),authCode);
        return new ResponseResult<>(ResponseResult.WaitForAuthCode,"等待验证码",null);
    }

    @Override
    public ResponseResult<Object> registerWithCode(User user) {
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
        //检查密码的合法性
        ResponseResult<Object> var = LegalUtils.verifyPassword(user.getPassword());
        if(!Objects.isNull(var)){
            return var;
        }

        //验证用户携带的authCode和数据库中的是否一致
        String phone = user.getUsername();
        AuthCode authCode = new AuthCode(phone,user.getAuthCode(),null);
        ResponseResult<Object> var2 = authCodeService.verifyAuthCode(authCode);
        if(!Objects.isNull(var2)){
            //验证码error
            return authCodeService.verifyAuthCode(authCode);
        }
        //注册
        return register(user);
    }

    @Override
    public ResponseResult<Object> register(User user) {
        //将手机号码设置为用户名
        user.setPhone(user.getUsername());
        user.setNickname("748.25day");
        //把用户存入用户表
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userDao.insert(user);
        System.out.println("新注册的用户为"+user);
        //获取token
        int userid = userDao.getByUsername(user.getUsername()).getId();
        String token = userTokenService.saveUserToken(userid);
        Map<String,String> map = new HashMap<>();
        map.put("token",token);

        //返回登录结果
        return new ResponseResult<>(ResponseResult.RegisterOk, "注册成功", map);

    }

}
