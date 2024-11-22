package com.gcc.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class studyCharacterScore {

    @TableField(value = "student_id")
    private String studentId;

    @TableField(value = "course_id")
    private String courseId;

    private String characterScore;

    private String avgCharacterScore;


    public studyCharacterScore(String studentId, String courseId, String characterScore, String avgCharacterScore) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.characterScore = characterScore;
        this.avgCharacterScore = avgCharacterScore;
    }


}
