package com.gcc.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class DwiCourse implements Serializable {

    private static final long serialVersionUID = 1L;


    @TableId(value = "Id")
    private String id;


    private String name;

    private String hours;

    private String courseId;

    private String studentId;

    private String coursecode;

    private String courseName;

    private String wordcloud;

}
