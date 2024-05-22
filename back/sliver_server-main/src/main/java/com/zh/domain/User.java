package com.zh.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private Integer id;
    private String username;
    private String nickname;
    private String password;
    private String email;
    private String phone;
    @TableField(exist = false)
    private String authCode;
    @TableField(exist = false)
    private String headPortraitUrl;
}
