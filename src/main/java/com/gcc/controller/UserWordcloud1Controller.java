package com.gcc.controller;


import com.gcc.common.Result;
import com.gcc.pojo.UserWordcloud1;
import com.gcc.service.UserWordcloud1Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author gcc
 * @since 2022-12-30
 */
@RestController
@RequestMapping("/GetWordCloudByUserId")
public class UserWordcloud1Controller {
    @Resource
    private UserWordcloud1Service uwcs;

    @GetMapping("/{student_id}")
    public Result<List<UserWordcloud1>> getWordCloudByUserId(@PathVariable String student_id) {
        List<UserWordcloud1> wordCloudByUserId = uwcs.getWordCloudByUserId(student_id);
        if (wordCloudByUserId.isEmpty()) {
            List<UserWordcloud1> u = new ArrayList<>();
            UserWordcloud1 uu = new UserWordcloud1(student_id, "赞赏, 尊敬, 赞许, 爱戴, 文化, 教育, 政策, 经济, 财产, 产业, 学习, 政策 , 生命安全, 企业, 交通, 社会, 道德, 国家, 人类, 个体, 个人, 人群, 社会, 公民, 成人, 伙伴, 家庭, 朋友, 同事,  教育, 学生, 工人, 领导, 艺术家, 创作, 旅行, 科学, 哲学, 健康, 心理, 个性, 开放, 成长, 学习起步, 还需努力, 浅尝辄止, 明显不足, 待合格, 课程起步, 浅层投入, 高冷, 点到为止, 树懒, 新手, 状态不佳, 知识初识", "");
            u.add(uu);
            return Result.success(u);
        } else {
            return Result.success(wordCloudByUserId);
        }
    }

}

