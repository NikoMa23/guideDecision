package com.zh.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User_Token {
    Integer userId;
    String token;
}
