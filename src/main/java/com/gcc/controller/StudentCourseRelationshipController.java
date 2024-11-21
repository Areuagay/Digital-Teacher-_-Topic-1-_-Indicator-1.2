package com.gcc.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gcc.common.Result;
import com.gcc.pojo.StudentCourseRelationship;
import com.gcc.service.StudentCourseRelationshipService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author gcc
 * @since 2023-10-25
 */
@RestController
@RequestMapping("/getStudentCourseRelationship")
public class StudentCourseRelationshipController {
    @Resource
    private StudentCourseRelationshipService studentCourseRelationshipService;

    @GetMapping("/{user_id}")
    public Result<List<StudentCourseRelationship>> getPersonInfoByUserId(@PathVariable String user_id) {
        LambdaQueryWrapper<StudentCourseRelationship> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StudentCourseRelationship::getStudentId, user_id);
        ;
        List<StudentCourseRelationship> list = studentCourseRelationshipService.list(wrapper);
//        List<StudentCourseRelationshipVO> studentCourseRelationshipVOList = new ArrayList<>();
//        for (StudentCourseRelationship studentCourseRelationship : list) {
//            StudentCourseRelationshipVO studentCourseRelationshipVO = new StudentCourseRelationshipVO();
//            studentCourseRelationshipVO.setCourseId(studentCourseRelationship.getCourseId());
//            studentCourseRelationshipVO.setCourseName(studentCourseRelationship.getCourseName());
//            studentCourseRelationshipVO.setStudentId(studentCourseRelationship.getStudentId());
//            studentCourseRelationshipVOList.add(studentCourseRelationshipVO);
//        }
        return Result.success(list);
    }

}

