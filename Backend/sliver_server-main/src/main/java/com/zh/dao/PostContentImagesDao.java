package com.zh.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zh.domain.PostContentImages;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PostContentImagesDao extends BaseMapper<PostContentImages> {

    @Select("select * from post_content_images where content_id = #{contentId}")
    List<PostContentImages> selectByContentId(int contentId);
}
