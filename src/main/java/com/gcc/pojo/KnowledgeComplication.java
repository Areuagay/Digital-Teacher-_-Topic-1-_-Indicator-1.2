package com.gcc.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author gcc
 * @since 2022-12-30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class KnowledgeComplication implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 学生id
     */
    @TableId(value = "student_id", type = IdType.AUTO)
    private String studentId;

    /**
     * 课程id
     */
    private String courseId;

    /**
     * 知识完成度得分
     */
    private String knowledgeScore;

    /**
     * 平均知识完成度得分
     */
    private String avgKnowledgeScore;

    //写库忽略该字段
    @TableField(exist = false)
    private String score;

    //写库忽略该字段
    @TableField(exist = false)
    private Float stuKnowledgeScore;

}
