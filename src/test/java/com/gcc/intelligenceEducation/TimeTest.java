package com.gcc.intelligenceEducation;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gcc.mapper.DwiUserVisitMapper;
import com.gcc.pojo.DwiUserVisit;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.filter.TypeExcludeFilters;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yp
 * @date: 2023/10/26
 */
@SpringBootTest
@TypeExcludeFilters({ListenerExcludeFilter.class})
public class TimeTest {

    @Resource
    private DwiUserVisitMapper dwiUserVisitMapper;

    @Test
    void testTime() {
        List<DwiUserVisit> dwiUserVisits = dwiUserVisitMapper.selectList(new LambdaQueryWrapper<DwiUserVisit>().last("where STR_TO_DATE( `Day`, '%Y-%m-%d' ) < STR_TO_DATE( '2023-10-24', '%Y-%m-%d' )  limit 1000 , 1000"));
        System.out.println(dwiUserVisits);
    }
}
