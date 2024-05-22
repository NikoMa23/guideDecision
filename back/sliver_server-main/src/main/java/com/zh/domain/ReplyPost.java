package com.zh.domain;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class ReplyPost {
    Integer PostId;
    String title;
    Integer userId;
    Integer likes;
    String userNickname;
    String userHeadPortraitUrl;
    String contentText;
    List<String> ImagesUrls;
    Timestamp date;

}
