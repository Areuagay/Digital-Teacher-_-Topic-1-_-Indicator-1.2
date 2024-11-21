package com.gcc.aspect;

import org.aspectj.lang.annotation.Pointcut;

/**
 * hzm
 */

public class PointCutsForAspect {

    @Pointcut(value = "execution(* com.gcc.controller..*.*(..))")
    public void timeHandlerPointCut() {
    }

//    @Pointcut(value = "execution(* com.ceair.pss.shopping.lowprice.service.LowPriceServiceImpl.lowPrice(..))")
//    public void exceptionPointCut() {
//    }

    /**
     * no aop around config class
     * 在测试的时候发现，time Handler不能切到config下的类，如果切到，会报错
     * 勿删
     */
    @Pointcut(value = "execution(* com.gcc..config..*.*(..))")
    public void excludePointCut() {

    }
}
