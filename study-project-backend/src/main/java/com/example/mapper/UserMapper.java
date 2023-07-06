package com.example.mapper;

import com.example.entity.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from user where username = #{text} or mail = #{text}")
    Account findAccountByNameOrEmail(String text);

    @Insert("insert into user (username,password,mail) values(#{username},#{password},#{mail})")
    int createAccount(String username,String password,String mail);

    @Update("update user set password = #{password} where mail = #{mail}")
    int resetPasswordByEmail(String password,String mail);
}
