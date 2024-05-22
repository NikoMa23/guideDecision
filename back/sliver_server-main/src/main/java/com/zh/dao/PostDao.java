package com.zh.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zh.domain.Post;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PostDao extends BaseMapper<Post>{

    @Select("select * from post where user_id = #{userId}")
    List<Post> getPostByUserId(int userId);


    @Select("SELECT * FROM post ORDER BY RAND() LIMIT #{num}")
    List<Post> getPostRandom(int num);
}
