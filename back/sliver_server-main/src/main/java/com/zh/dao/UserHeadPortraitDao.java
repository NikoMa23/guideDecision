package com.zh.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zh.domain.UserHeadPortrait;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserHeadPortraitDao extends BaseMapper<UserHeadPortrait> {

    @Select("SELECT * FROM user_head_portrait WHERE user_id = #{userId}")
    List<UserHeadPortrait> selectByUser_idUserHeadPortraitList(int userId);
}
