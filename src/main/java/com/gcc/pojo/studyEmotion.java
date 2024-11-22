package com.gcc.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class studyEmotion {

    @TableField(value = "student_id")
    private String studentId;

    @TableField(value = "course_id")
    private String courseId;

    @TableField(value = "emotionScore")
    private String emotionScore;

    @TableField(value = "avgEmotionScore")
    private String avgEmotionScore;


    public studyEmotion(String studentId, String courseId, String emotionScore, String avgEmotionScore) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.emotionScore = emotionScore;
        this.avgEmotionScore = avgEmotionScore;
    }

}
