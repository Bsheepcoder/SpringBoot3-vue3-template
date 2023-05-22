package com.example.service;

import com.example.dto.StudentDto;
import com.example.entity.Student;
import com.example.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {
    @Autowired
    StudentMapper studentMapper;


    @Transactional(rollbackFor = Exception.class)
    public int insertNameAndAge(StudentDto student) throws Exception{
        System.out.println("student");
        System.out.println(student);
        System.out.println(student.getAge());
        System.out.println(student.getName());
        studentMapper.insertNameandAge(student);
        return 1;
    }

}
