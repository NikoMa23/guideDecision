package com.zh.service;

import com.zh.domain.ResponseResult;
import com.zh.domain.User;
import com.zh.domain.User_Token;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;

public interface LoginService {
    ResponseResult<Object> login (User user);

}
