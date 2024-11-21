package com.gcc.aspect;

import java.util.concurrent.TimeUnit;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 耗时打印aop
 */
@Aspect
@Component
@Order(value = 99)
public class TimeHandlerAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(TimeHandlerAspect.class);
    private static final long DEBUG_CONTROL_TIME = TimeUnit.MILLISECONDS.toNanos(100L); //100 milliseconds
    private static final long INFOCONTROL_TIME = TimeUnit.MILLISECONDS.toNanos(500L); //300 milliseconds
    private static final long WARN_CONTROL_TIME = TimeUnit.MILLISECONDS.toNanos(1000L); //1000 milliseconds


    @Around(value = "PointCutsForAspect.timeHandlerPointCut() && !PointCutsForAspect.excludePointCut()")
    public Object afterThrowing(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        LOGGER.debug("----- log aspect before -----");
        long start = System.nanoTime();
        Object result = proceedingJoinPoint.proceed();
        long end = System.nanoTime();
        long duration = end - start;
        long durationNanos = TimeUnit.NANOSECONDS.toNanos(duration);
        long durationMicroseconds = TimeUnit.NANOSECONDS.toMicros(duration);
        long durationMilliseconds = TimeUnit.NANOSECONDS.toMillis(duration);
        long durationSeconds = TimeUnit.NANOSECONDS.toSeconds(duration);
        if (duration < DEBUG_CONTROL_TIME && LOGGER.isDebugEnabled()) {
            LOGGER.debug("[time consuming]: {}.{} {}ns {}μs {}ms {}s",
                    proceedingJoinPoint.getTarget().getClass().getName(),
                    proceedingJoinPoint.getSignature().getName(),
                    durationNanos,
                    durationMicroseconds,
                    durationMilliseconds,
                    durationSeconds
                    );
        } else if (duration < INFOCONTROL_TIME && LOGGER.isInfoEnabled()) {
            LOGGER.info("[time consuming]: {}.{} {}ns {}μs {}ms {}s",
                    proceedingJoinPoint.getTarget().getClass().getName(),
                    proceedingJoinPoint.getSignature().getName(),
                    durationNanos,
                    durationMicroseconds,
                    durationMilliseconds,
                    durationSeconds
            );
        } else {
            LOGGER.warn("[time consuming]: {}.{} {}ns {}μs {}ms {}s",
                    proceedingJoinPoint.getTarget().getClass().getName(),
                    proceedingJoinPoint.getSignature().getName(),
                    durationNanos,
                    durationMicroseconds,
                    durationMilliseconds,
                    durationSeconds
            );
        }
        LOGGER.debug("----- log aspect end -----");
        return result;
    }
}
