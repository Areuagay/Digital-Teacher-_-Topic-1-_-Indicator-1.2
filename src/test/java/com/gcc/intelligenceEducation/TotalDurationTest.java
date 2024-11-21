package com.gcc.intelligenceEducation;

import com.alibaba.fastjson.JSON;
import com.gcc.common.Result;
import com.gcc.controller.StudyTotalDurationController;
import com.gcc.controller.UserWordcloud1Controller;
import com.gcc.pojo.StudyTotalDuration;
import com.gcc.service.KafkaService;
import com.gcc.task.TimeTask;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.filter.TypeExcludeFilters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.annotation.Resource;
import java.util.List;

@EnableAsync
@SpringBootTest
@TypeExcludeFilters({ListenerExcludeFilter.class})
@ComponentScan(excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {TimeTask.class, ListenerExcludeFilter.class,KafkaService.class,})})
public class TotalDurationTest {
    @Resource
    UserWordcloud1Controller wordcloud1Controller;

    @Autowired
    StudyTotalDurationController studyTotalDurationController;


    @Test
    public void test() {
        //System.out.println(wordcloud1Controller.getWordCloudByUserId("1411001253257"));
        for (int i=0;i<20;i++) {
            Result<List<StudyTotalDuration>> totalDurationByUserId = studyTotalDurationController.getTotalDurationByUserId("2144001253960");
            System.out.println("\n\n\n" + JSON.toJSON(totalDurationByUserId) +i+" times"+ "\n\n");
        }
        try {
            System.out.println("main is sleeping");
            Thread.sleep(60000);
            System.out.println("main dead");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
