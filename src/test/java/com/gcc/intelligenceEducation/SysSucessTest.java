package com.gcc.intelligenceEducation;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gcc.pojo.Ability;
import com.gcc.service.AbilityService;
import com.gcc.service.DwiStudentCourseService;
import com.gcc.service.StudentTotalProgressService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.filter.TypeExcludeFilters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@SpringBootTest
@Transactional
@TypeExcludeFilters({ListenerExcludeFilter.class})
public class SysSucessTest {
    @Resource
    private AbilityService abilityService;
    @Resource
    private DwiStudentCourseService dwiStudentCourseService;

    @Resource
    private StudentTotalProgressService studentTotalProgressService;
    @Test
    public void main() {
        System.out.println(abilityService.count(new QueryWrapper<>())+"--------");
        System.out.println(dwiStudentCourseService.count(new QueryWrapper<>())+"-------");
        System.out.println(studentTotalProgressService.count(new QueryWrapper<>()));
    }
}
