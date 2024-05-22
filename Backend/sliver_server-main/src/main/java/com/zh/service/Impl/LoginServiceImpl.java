package com.zh.service.Impl;

import com.zh.domain.ResponseResult;
import com.zh.domain.User;
import com.zh.config.MyUserDetails;
import com.zh.service.LoginService;
import com.zh.service.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserTokenService userTokenService;

    @Override
    public ResponseResult<Object> login(User user) {
        //AuthenticationManager authenticate 用户认证
        UsernamePasswordAuthenticationToken AuthenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authentication = null;
        try { //捕获认证失败异常 BadCredentialsException
            authentication = authenticationManager.authenticate(AuthenticationToken);
        } catch (Exception e) {
            System.out.println(user);
            e.printStackTrace();
        }

        //如果认证没通过,给出相应的提示
        if (Objects.isNull(authentication)) {
            return new ResponseResult<>(ResponseResult.WrongUserOrPassword, "用户名或密码错误", null);
        }
        //如果认证通过了,使用userid生成一个token,并保存
        MyUserDetails loginUser = (MyUserDetails) authentication.getPrincipal();
        int userid = loginUser.getUser().getId();
        String token = userTokenService.saveUserToken(userid);
        Map<String,String> map = new HashMap<>();
        map.put("token",token);

        //把用户存入SecurityContextHolder? 不需要,调用其他接口都用JWT

        //返回登录结果
        return new ResponseResult<>(ResponseResult.LoginOk, "登录成功", map);
    }
}
