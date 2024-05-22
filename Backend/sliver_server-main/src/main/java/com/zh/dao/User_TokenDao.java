package com.zh.dao;

import com.zh.domain.User_Token;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface User_TokenDao {

    @Insert("insert into User_Token (user_id, token) values (#{userId},#{token})")
    void insert(User_Token userToken);

    @Update("update User_Token set token = #{token} where user_id = #{userId}")
    void update(User_Token userToken);
    @Select("select token from User_Token where user_id = #{user_id}")
    String getById(int user_id);
}
