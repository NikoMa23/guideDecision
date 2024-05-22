package com.zh.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.zh.dao.UserDao;
import com.zh.domain.ResponseResult;
import com.zh.domain.User;
import com.zh.service.Impl.UserServiceImpl;
import com.zh.utils.JsonUtils;
import com.zh.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @PostMapping("/user/details")
    public ResponseResult<Object> getUserDetail(HttpServletRequest request){
        //获取请求中的json数据
        JsonNode jsonNode = JsonUtils.parseRequest(request);
        if (jsonNode == null) return new ResponseResult<>(ResponseResult.Error, "json格式出错", null);
        String userId = null;
        try {
            userId = jsonNode.get("userId").asText();
        }catch (Exception ignored){}
        String token = request.getHeader("token");
        //解析token
        Claims claims;
        try {
            claims = JwtUtils.parseJwtToken(token);
        } catch (ExpiredJwtException e) {
            return new ResponseResult<>(ResponseResult.TokenOutdated, "token已过期", null);
        }
        String userId2 = claims.getId();
        return userService.getUserDetails(userId,userId2);
    }

    @PostMapping("/user/changePassword")
    public ResponseResult<Object> changePassword(HttpServletRequest request){
        String token = request.getHeader("token");
        //获取请求中的json数据
        JsonNode jsonNode = JsonUtils.parseRequest(request);
        if (jsonNode == null) return new ResponseResult<>(ResponseResult.Error, "json格式出错", null);
        String username = null;
        String password = null;
        String changedPassword = null;
        try {
            username = jsonNode.get("username").asText();
            password = jsonNode.get("password").asText();
            changedPassword = jsonNode.get("changedPassword").asText();
        }catch (Exception e){
            return new ResponseResult<>(ResponseResult.Error, "json格式出错", null);
        }
        //解析token
        Claims claims;
        try {
            claims = JwtUtils.parseJwtToken(token);
        } catch (ExpiredJwtException e) {
            return new ResponseResult<>(ResponseResult.TokenOutdated, "token已过期", null);
        }
        int userId = Integer.parseInt(claims.getId());
        return userService.changePassword(username,userId,password,changedPassword);
    }

    @PostMapping("/user/changeNickname")
    public ResponseResult<Object> changeNickname(HttpServletRequest request){
        String token = request.getHeader("token");
        //获取请求中的json数据
        JsonNode jsonNode = JsonUtils.parseRequest(request);
        if (jsonNode == null) return new ResponseResult<>(ResponseResult.Error, "json格式出错", null);
        String username = null;
        String changedNickname =null;
        try {
            username = jsonNode.get("username").asText();
            changedNickname = jsonNode.get("changedNickname").asText();
        }catch (Exception e){
            return new ResponseResult<>(ResponseResult.Error, "json格式出错", null);
        }
        //解析token
        Claims claims;
        try {
            claims = JwtUtils.parseJwtToken(token);
        } catch (ExpiredJwtException e) {
            return new ResponseResult<>(ResponseResult.TokenOutdated, "token已过期", null);
        }
        String userId = claims.getId();
        return userService.changeNickname(username,userId,changedNickname);
    }

    @PostMapping("/user/feedback")
    public ResponseResult<Object> feedback(HttpServletRequest request){
        //获取请求信息
        String token = request.getHeader("token");
        //解析token
        Claims claims;
        try {
            claims = JwtUtils.parseJwtToken(token);
        } catch (ExpiredJwtException e) {
            return new ResponseResult<>(ResponseResult.TokenOutdated, "token已过期", null);
        }
        String userId = claims.getId();
        //获取请求中的json数据
        JsonNode jsonNode = JsonUtils.parseRequest(request);
        String feedback = null;
        if (jsonNode == null) return new ResponseResult<>(ResponseResult.Error, "json格式出错", null);
        try {
            feedback = jsonNode.get("feedback").asText();
        }catch (Exception e){
            return new ResponseResult<>(ResponseResult.Error, "json格式出错", null);
        }

        return userService.feedback(userId,feedback);
    }

    @PostMapping("/user/uploadHeadPortrait")
    public ResponseResult<Object> uploadHeadPortrait(HttpServletRequest request, @RequestParam("file") MultipartFile file){
        if (file.isEmpty()) {
            return new ResponseResult<>(ResponseResult.Error, "上传图片为空", null);
        }
        //获取请求信息
        String token = request.getHeader("token");
        Claims claims;
        try {
            claims = JwtUtils.parseJwtToken(token);
        } catch (ExpiredJwtException e){
            return new ResponseResult<>(ResponseResult.TokenOutdated,"token已过期",null);
        }
        String userId = claims.getId();
        return userService.uploadHeadPortrait(userId,file);
    }

}
