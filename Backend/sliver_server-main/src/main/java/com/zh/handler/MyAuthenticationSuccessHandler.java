package com.zh.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zh.config.MyUserDetails;
import com.zh.domain.ResponseResult;
import com.zh.service.UserTokenService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Autowired
    UserTokenService userTokenService;
    /**
     * 认证成功后,具体执行的代码
     * @param request
     * @param response
     * @param authentication 认证成功后的用户信息,包含登录用户的个人信息和权限列表
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException, ServletException {
        MyUserDetails loginUser = (MyUserDetails) authentication.getPrincipal();
        int userid = loginUser.getUser().getId();
        String token = userTokenService.saveUserToken(userid);
        Map<String,String> map = new HashMap<>();
        map.put("token",token);
        ResponseResult<Object> unauthorizedResponse = new ResponseResult<>(
                ResponseResult.LoginOk, "登录成功", map);

        // 将 ResponseResult 对象转换为 JSON 字符串
        String jsonResponse = new ObjectMapper().writeValueAsString(unauthorizedResponse);

        // 设置响应的内容类型为 JSON
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        // 将 JSON 写入响应
        response.getWriter().write(jsonResponse);
    }
}
