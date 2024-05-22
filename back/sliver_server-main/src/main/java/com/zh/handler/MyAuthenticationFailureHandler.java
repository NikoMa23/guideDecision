package com.zh.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zh.domain.ResponseResult;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        System.out.println("Authentication failure handler is invoked!");
        /// 构建未授权的响应
        ResponseResult<String> unauthorizedResponse = new ResponseResult<>(
                ResponseResult.WrongUserOrPassword, "用户名或密码错误", null);

        // 将 ResponseResult 对象转换为 JSON 字符串
        String jsonResponse = new ObjectMapper().writeValueAsString(unauthorizedResponse);

        // 设置响应的内容类型为 JSON
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        // 将 JSON 写入响应
        response.getWriter().write(jsonResponse);
    }

}
