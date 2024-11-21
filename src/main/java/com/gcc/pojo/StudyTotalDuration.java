package com.gcc.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2022-12-30
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class StudyTotalDuration implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "student_id")
    private String studentId;

//    @TableId(value = "total_hours")
    private String totalHours;


//    @TableId(value = "total_minutes")
    private String totalMinutes;

}
