package com.zh.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zh.domain.UserPostCollect;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserPostCollectDao extends BaseMapper<UserPostCollect> {
    @Select("select * from user_post_collect where user_id = #{userId}")
    List<UserPostCollect> selectByUserId(int userId);
}
