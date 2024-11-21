package com.gcc.intelligenceEducation;

import com.gcc.mapper.StudentCourseScoreMapper;
import com.gcc.pojo.StudentCourseScore;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.filter.TypeExcludeFilters;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yp
 * @date: 2023/11/5
 */
@TypeExcludeFilters({ListenerExcludeFilter.class})
@SpringBootTest
public class StudentCourseScoreTest {

    @Resource
    private StudentCourseScoreMapper studentCourseScoreMapper;

    @Test
    void testGetList() {
        List<StudentCourseScore> studentCourseScoreList = studentCourseScoreMapper.selectFromDwi();
        studentCourseScoreMapper.batchInsert(studentCourseScoreList);
    }
}
