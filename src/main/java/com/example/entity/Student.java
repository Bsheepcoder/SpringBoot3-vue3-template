package com.example.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
@TableName("STUDENT_DEMO")
public class Student {
    @TableId(value = "ID", type = IdType.ASSIGN_ID)
    private Long id;

    @TableField("NAME")
    private String name;

    @TableField("AGE")
    private String age;

    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableField(value = "DELETED_FLAG", fill = FieldFill.INSERT)
    @TableLogic
    private Integer deletedFlag;
    /**
     * 业务主键
     * @return String
     */
    public String getBusinessPrimaryKey(){
        return this.age+this.name;
    }

}