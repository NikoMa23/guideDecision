package com.zh.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.zh.domain.ResponseResult;
import com.zh.service.Impl.PostServiceImpl;
import com.zh.utils.JsonUtils;
import com.zh.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class PostController {

    @Autowired
    PostServiceImpl postService;

    @PostMapping("/post/uploadImages")
    ResponseResult<Object> uploadPostImages(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseResult<>(ResponseResult.Error, "上传图片为空", null);
        }
        //获取请求信息
        String token = request.getHeader("token");
        Claims claims;
        try {
            claims = JwtUtils.parseJwtToken(token);
        } catch (ExpiredJwtException e) {
            return new ResponseResult<>(ResponseResult.TokenOutdated, "token已过期", null);
        }
        int userId = Integer.parseInt(claims.getId());
        return postService.uploadPostContentImages(userId, file);
    }

    @PostMapping("/post/uploadPost")
    ResponseResult<Object> uploadPost(HttpServletRequest request) {
        //获取请求中的json数据
        JsonNode jsonNode = JsonUtils.parseRequest(request);
        if (jsonNode == null) return new ResponseResult<>(ResponseResult.Error, "json格式出错", null);
        String title = null;
        String contentText = null;
        try {
            title = jsonNode.get("title").asText();
            contentText = jsonNode.get("contentText").asText();
        } catch (Exception e) {
            return new ResponseResult<>(ResponseResult.Error, "json格式出错", null);
        }

        //获取请求信息
        String token = request.getHeader("token");
        Claims claims;
        try {
            claims = JwtUtils.parseJwtToken(token);
        } catch (ExpiredJwtException e) {
            return new ResponseResult<>(ResponseResult.TokenOutdated, "token已过期", null);
        }
        int userId = Integer.parseInt(claims.getId());
        return postService.acceptPost(userId, title, contentText);
    }

    @PostMapping("/post/getUserPost")
    ResponseResult<Object> getUserPost(HttpServletRequest request, @RequestHeader("token") String token) {
        //获取请求中的json数据
        JsonNode jsonNode = JsonUtils.parseRequest(request);
        if (jsonNode == null) return new ResponseResult<>(ResponseResult.Error, "json格式出错", null);
        try {
            int userId = Integer.parseInt(jsonNode.get("userId").asText()); //没有解析到值
            return postService.getPostByUserId(userId);
        } catch (Exception e) {
            System.out.println("没有解析到userId");
        }
        //获取请求信息
        Claims claims;
        try {
            claims = JwtUtils.parseJwtToken(token);
        } catch (ExpiredJwtException e) {
            return new ResponseResult<>(ResponseResult.TokenOutdated, "token已过期", null);
        }
        int userId2 = Integer.parseInt(claims.getId());
        return postService.getPostByUserId(userId2);
    }

    @PostMapping("/post/deleteUploadedImage")
    ResponseResult<Object> deleteUploadedImage(HttpServletRequest request, @RequestHeader("token") String token) {
        //解析token
        Claims claims;
        try {
            claims = JwtUtils.parseJwtToken(token);
        } catch (ExpiredJwtException e) {
            return new ResponseResult<>(ResponseResult.TokenOutdated, "token已过期", null);
        }
        int userId = Integer.parseInt(claims.getId());
        //获取请求中的json数据
        JsonNode jsonNode = JsonUtils.parseRequest(request);
        if (jsonNode == null) return new ResponseResult<>(ResponseResult.Error, "json格式出错", null);
        int imageOrder;
        try {
            imageOrder = Integer.parseInt(jsonNode.get("imageOrder").asText());
        }catch (Exception e){
            return new ResponseResult<>(ResponseResult.Error, "json格式出错", null);
        }

        return postService.deleteUploadedImage(imageOrder, userId);
    }

    @PostMapping("/post/uploadRemark")
    ResponseResult<Object> uploadRemark(HttpServletRequest request, @RequestHeader("token") String token) {
        //获取请求中的json数据
        JsonNode jsonNode = JsonUtils.parseRequest(request);
        if (jsonNode == null) return new ResponseResult<>(ResponseResult.Error, "json格式出错", null);
        int postId;
        String contentText;
        try {
            postId = Integer.parseInt(jsonNode.get("postId").asText());
            contentText = jsonNode.get("contentText").asText();
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
        return postService.remark(postId, contentText, userId);
    }

    @PostMapping("/post/getRemark")
    ResponseResult<Object> getRemark(HttpServletRequest request) {
        //获取请求中的json数据
        JsonNode jsonNode = JsonUtils.parseRequest(request);
        if (jsonNode == null) return new ResponseResult<>(ResponseResult.Error, "json格式出错", null);
        int postId;
        try {
            postId = Integer.parseInt(jsonNode.get("postId").asText());
        }catch (Exception e){
            return new ResponseResult<>(ResponseResult.Error, "json格式出错", null);
        }
        return postService.getPostRemark(postId);
    }

    @PostMapping("/post/like")
    ResponseResult<Object> postLike(HttpServletRequest request, @RequestHeader("token") String token) {
        //获取请求中的json数据
        JsonNode jsonNode = JsonUtils.parseRequest(request);
        if (jsonNode == null) return new ResponseResult<>(ResponseResult.Error, "json格式出错", null);
        int postId;
        try {
            postId = Integer.parseInt(jsonNode.get("postId").asText());
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
        return postService.postLike(userId, postId);
    }

    @PostMapping("/post/noLike")
    ResponseResult<Object> postNoLike(HttpServletRequest request, @RequestHeader("token") String token) {
        //获取请求中的json数据
        JsonNode jsonNode = JsonUtils.parseRequest(request);
        if (jsonNode == null) return new ResponseResult<>(ResponseResult.Error, "json格式出错", null);
        int postId;
        try {
            postId = Integer.parseInt(jsonNode.get("postId").asText());
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
        return postService.postNoLike(userId, postId);
    }

    @GetMapping("/post/uploadedImages")
    ResponseResult<Object> getUploadedImages(@RequestHeader("token") String token) {
        //获取请求信息
        Claims claims;
        try {
            claims = JwtUtils.parseJwtToken(token);
        } catch (ExpiredJwtException e) {
            return new ResponseResult<>(ResponseResult.TokenOutdated, "token已过期", null);
        }
        int userId = Integer.parseInt(claims.getId());
        return postService.getUploadedContentImages(userId);
    }

    @PostMapping("/post/userCollect")
    ResponseResult<Object> userCollect(HttpServletRequest request, @RequestHeader("token") String token) {
        //获取请求中的json数据
        JsonNode jsonNode = JsonUtils.parseRequest(request);
        if (jsonNode == null) return new ResponseResult<>(ResponseResult.Error, "json格式出错", null);
        int postId;
        try {
            postId = Integer.parseInt(jsonNode.get("postId").asText());
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
        return postService.postCollect(postId, userId);
    }

    @GetMapping("/post/getUserCollect")
    ResponseResult<Object> getUserCollect(@RequestHeader("token") String token) {
        //解析token
        Claims claims;
        try {
            claims = JwtUtils.parseJwtToken(token);
        } catch (ExpiredJwtException e) {
            return new ResponseResult<>(ResponseResult.TokenOutdated, "token已过期", null);
        }
        int userId = Integer.parseInt(claims.getId());
        return postService.getUserPostCollects(userId);
    }
}
