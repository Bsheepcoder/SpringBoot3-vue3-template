package com.example.mapper;


import com.example.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from user where username = #{text} or mail = #{text}")
    Account findAccountByNameOrEmail(String text);

}
