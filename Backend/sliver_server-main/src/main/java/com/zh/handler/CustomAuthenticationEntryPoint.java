package com.zh.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zh.domain.ResponseResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint{



    /**
     * 认证失败处理类 返回未授权
     * 用来解决匿名用户访问无权限资源时的异常
     */
        @Override
        public void commence(HttpServletRequest request, HttpServletResponse response,
                             AuthenticationException e)
                throws IOException {
            /// 构建未授权的响应
            ResponseResult<String> unauthorizedResponse = new ResponseResult<>(
                    ResponseResult.Unauthenticated, "未授权", null);

            // 将 ResponseResult 对象转换为 JSON 字符串
            String jsonResponse = new ObjectMapper().writeValueAsString(unauthorizedResponse);

            // 设置响应的内容类型为 JSON
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding("UTF-8");

            // 将 JSON 写入响应
            response.getWriter().write(jsonResponse);
        }

}
