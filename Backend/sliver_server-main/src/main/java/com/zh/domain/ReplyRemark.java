package com.zh.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplyRemark {
    Integer id;
    String userNickname;
    String userHeadPortraitUrl;
    String contentText;
    Timestamp date;

}
