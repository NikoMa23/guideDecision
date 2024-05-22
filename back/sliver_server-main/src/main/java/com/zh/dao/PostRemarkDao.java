package com.zh.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zh.domain.PostRemark;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PostRemarkDao extends BaseMapper<PostRemark> {
    @Select("select * from post_remark where post_id = #{postId}")
    List<PostRemark> selectByPostId(int postId);
}
