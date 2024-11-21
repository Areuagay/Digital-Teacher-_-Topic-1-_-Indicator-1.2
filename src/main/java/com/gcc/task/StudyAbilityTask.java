package com.gcc.task;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gcc.mapper.AbilityMapper;
import com.gcc.mapper.StudentCourseRelationshipMapper;
import com.gcc.mapper.StudyAbilityMapper;
import com.gcc.pojo.Ability;
import com.gcc.pojo.StudentCourseRelationship;
import com.gcc.pojo.StudyAbility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.List;

@Slf4j
public class StudyAbilityTask {

    @Resource
    private StudyAbilityMapper studyAbilityMapper;

    @Resource
    private StudentCourseRelationshipMapper studentCourseRelationshipMapper;

    @Resource
    private AbilityMapper abilityMapper;

    private int count = 0;

    @DS("slave_2")
    @Scheduled(cron = "${scheduled.onceADayAtMidday}")
    void test() {

        int batchSize = 3000;
        int size = count * 40 + 40;
        for (int i = count * 40; i < size; i++) {
            int finalI = i;

            try {
                List<Ability> ability = abilityMapper.selectList(new LambdaQueryWrapper<Ability>().last("limit " + finalI * batchSize + "," + batchSize));//finalI * batchSize   batchSize
                if (ability != null) findStudentCourseRelationshipData(ability);


            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        count++;
        log.info("-------This was {} times-------", count++);
    }

    @DS("master")
    void findStudentCourseRelationshipData(List<Ability> abilities) {
        for (Ability s : abilities
        ) {
            LambdaQueryWrapper<StudentCourseRelationship> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(StudentCourseRelationship::getStudentId, s.getUserId());

            wrapper.like(s.getCourseId() != null, StudentCourseRelationship::getCourseId, s.getCourseId());


            try {
                StudentCourseRelationship relationship = studentCourseRelationshipMapper.selectOne(wrapper);
                if (relationship != null)
                    insertStudyAbility(relationship, s, avgAbility(s, abilities));
                else log.info("------StudentCourseRelationship为空-----");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    String avgAbility(Ability ability, List<Ability> abilities) {
        double abilityNum = 0D;

        int count = 0;

        for (Ability a : abilities
        ) {
            if (a.getCourseId().equals(ability.getCourseId())) {
                abilityNum += a.getAbility();
                count++;
            }
        }

        return new DecimalFormat("0.0000000000").format(abilityNum / count);

    }

    @DS("master")
    void insertStudyAbility(StudentCourseRelationship studentCourseRelationship, Ability ability, String avgAbility) {
        StudyAbility studyAbility = new StudyAbility();
        studyAbility.setCourseId(studentCourseRelationship.getCourseId());
        studyAbility.setStudentId(ability.getUserId());
        studyAbility.setAbilityScore(new DecimalFormat("0.0000000000").format(ability.getAbility())
        );
        studyAbility.setAvgAbilityScore(avgAbility);

        if (!insert(studyAbility)) {
            updateData(studyAbility);
            log.info("------- 更新 1 条数据 ---------");
//                        studyAbilityMapper.update()
        }
    }


    @DS("master")
    Boolean insert(StudyAbility studyAbility) {
        LambdaQueryWrapper<StudyAbility> wrapper1 = new LambdaQueryWrapper<>();
        wrapper1.eq(StudyAbility::getStudentId, studyAbility.getStudentId());
        //       wrapper1.eq(StudyAbility::getCourseId, studyAbility.getCourseId());
        if (studyAbilityMapper.selectOne(wrapper1) == null) {
            studyAbilityMapper.insert(studyAbility);
            log.info("------- 插入 1 条数据 ---------");
            return true;
        }

        return false;
    }

    @DS("master")
    void updateData(StudyAbility studyAbility) {
        LambdaQueryWrapper<StudyAbility> wrapper1 = new LambdaQueryWrapper<>();
        wrapper1.eq(StudyAbility::getStudentId, studyAbility.getStudentId());
        wrapper1.eq(StudyAbility::getCourseId, studyAbility.getCourseId());
        studyAbilityMapper.update(studyAbility, wrapper1);
    }

}
