package com.example.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.poi.excel.ExcelReader;
import com.example.dto.StudentDto;
import com.example.entity.Student;
import com.example.exception.BusinessException;
import com.example.service.StudentService;
import io.swagger.annotations.ApiOperation;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@ResponseBody
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/test")
    public void test1(HttpServletRequest req){
        String id = req.getParameter("id");

        System.out.println(id);
    }

    /**
     * ProductId数据文件上传
     * @param file
     * @return ResponseResult<Void>
     */
    @PostMapping("/upload")
    @ApiOperation(tags = "IgnoreProductId 页面操作", value = "ProductId数据文件上传")
    public String uploadProductId(MultipartFile file) throws Exception {
        System.out.println("1");

        if (ObjectUtil.isNull(file)) {
            throw new BusinessException("请检查Excel是否有数据");
        }

        // 文件名的获取 并判断是否是excel
        String originalFilename = file.getOriginalFilename();
        if (StringUtils.isBlank(originalFilename)) {
            throw new BusinessException("上传的文件名为空");
        }
        String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        if (!"xls".equals(fileSuffix) && !"xlsx".equals(fileSuffix)) {
            throw new BusinessException("文件格式不正确,请检查后重新上传!");
        }
        // 设置表头别名
        Map<String, String> headerAlis = new HashMap<>(16);
        String[] studentColumns = {"Name#name", "Age#age"};
        for (String stuExcelExportColumn : studentColumns) {
            String[] split = stuExcelExportColumn.split("#");
            headerAlis.put(split[0], split[1]);
        }
        // 将excel中的数据读取成list
        ExcelReader reader = new ExcelReader(file.getInputStream(), 0);
        reader.setHeaderAlias(headerAlis);
        List<StudentDto> studentDtos = reader.readAll(StudentDto.class);


        List<Student> students = BeanUtil.copyToList(studentDtos, Student.class);

        for (int i = 0; i < students.size(); i++) {
            studentService.insertNameAndAge(studentDtos.get(i));
            System.out.println(students);
        }
        return "OK";
    }
}