package com.example.mapper;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.dto.StudentDto;
import com.example.entity.Account;
import com.example.entity.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StudentMapper extends BaseMapper<Student> {
    @Insert("insert into student (Name, Age) values (#{name}, #{age})")
    int insertNameandAge(StudentDto student);

}
