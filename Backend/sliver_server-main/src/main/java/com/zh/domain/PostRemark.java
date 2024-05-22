package com.zh.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRemark {
    @TableId(type = IdType.AUTO)
    Integer id;
    Integer userId;
    Integer postId;
    String contentText;
    Timestamp date;
}
