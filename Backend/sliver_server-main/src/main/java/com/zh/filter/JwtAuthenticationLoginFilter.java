package com.zh.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zh.dao.UserDao;
import com.zh.domain.ResponseResult;
import com.zh.domain.User;
import com.zh.utils.JwtUtils;
import io.jsonwebtoken.Claims;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class JwtAuthenticationLoginFilter extends OncePerRequestFilter {

//    @Autowired
//    AuthenticationManager authenticationManager;

    @Autowired
    UserDao userDao;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException, ServletException
    {

        System.out.println(JwtAuthenticationLoginFilter.class.toString()+"is  invoked");
        //获取token
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            filterChain.doFilter(request,response); //过滤链继续执行
            return; // 结束运行doFilterInternal
        }
        //解析token
        String user_id;
        Claims claims = null;
        try{
            claims = JwtUtils.parseJwtToken(token); //不需要判断JWT是否过期,会在解析token时抛出ExpiredJwtException,
        }catch (ExpiredJwtException e ){
            System.out.println("捕获到token过期异常");
            /// 构建未授权的响应
            ResponseResult<String> unauthorizedResponse = new ResponseResult<>(
                    ResponseResult.TokenOutdated, "token已过期", null);

            // 将 ResponseResult 对象转换为 JSON 字符串
            String jsonResponse = new ObjectMapper().writeValueAsString(unauthorizedResponse);

            // 设置响应的内容类型为 JSON
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding("UTF-8");

            // 将 JSON 写入响应
            response.getWriter().write(jsonResponse);
            e.printStackTrace();
            return;// 结束运行
        }

        user_id = claims.getId();

        //获取用户信息,
        User user = userDao.getById(Integer.parseInt(user_id));
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(), user.getPassword(),null);//使用三个参数来封装,会认为已认证

        //或者调用AuthenticationManager来进行认证,会导致循环调用,不需要再认证,JWT本身就是安全的
        //Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        //将用户信息封装成Authentication存入SecurityContextHolder,这样再这一次的访问中,就是被授权的状态
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        filterChain.doFilter(request,response);//处理结束放行
    }

}
