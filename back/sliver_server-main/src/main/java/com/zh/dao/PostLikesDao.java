package com.zh.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zh.domain.PostLikes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PostLikesDao extends BaseMapper<PostLikes> {

    @Select("SELECT * FROM post_likes WHERE user_id = #{userId} AND post_id = #{postId}")
    List<PostLikes> selectPostLikesByUserIdAndPostId(int userId,int postId);

    @Select("SELECT COUNT(*) FROM post_likes WHERE post_id = #{postId}")
    int selectCountByPostId(int postId);
}
