package com.gcc.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author gcc
 * @since 2023-01-03
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class StudyAbility implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 学生id
     */
    @TableId(value = "student_id")
    private String studentId;

    /**
     * 课程id
     */
    private String courseId;

    /**
     * 能力值得分
     */
    private String abilityScore;

    /**
     * 平均能力值得分
     */
    private String avgAbilityScore;



    public StudyAbility() {

    }
}
