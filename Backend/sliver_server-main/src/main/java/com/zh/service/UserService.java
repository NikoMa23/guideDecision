package com.zh.service;

import com.zh.domain.ResponseResult;
import com.zh.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    ResponseResult<Object> changePassword(String username, int userId, String password, String changedPassword);

    ResponseResult<Object> getUserDetails(String userId, String userId2);


    ResponseResult<Object> changeNickname(String username, String user_id, String changedNickname);

    ResponseResult<Object> feedback(String userId, String feedback);


    ResponseResult<Object> uploadHeadPortrait(String userId, MultipartFile file);
}
