package com.gcc.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class WordcloudJudgment implements Serializable {

    private static final long serialVersionUID = 1L;


    private String id;

    private String courseId;

    private String wordcloud;
}
