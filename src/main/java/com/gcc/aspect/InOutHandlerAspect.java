package com.gcc.aspect;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.alibaba.fastjson.JSON;

/**
 * 耗时打印aop
 */
@Aspect
@Component
@Order(value = 1)
public class InOutHandlerAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(InOutHandlerAspect.class);
    private static final long DEBUG_CONTROL_TIME = TimeUnit.MILLISECONDS.toNanos(100L); //100 milliseconds
    private static final long INFOCONTROL_TIME = TimeUnit.MILLISECONDS.toNanos(500L); //300 milliseconds
    private static final long WARN_CONTROL_TIME = TimeUnit.MILLISECONDS.toNanos(1000L); //1000 milliseconds


    @Around(value = "PointCutsForAspect.timeHandlerPointCut() && !PointCutsForAspect.excludePointCut()")
    public Object afterThrowing(ProceedingJoinPoint joinPoint) throws Throwable {
        LOGGER.debug("----- Request Paramters -----");
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();

        long startTime = System.currentTimeMillis();
        LOGGER.info("[start time]: {}", startTime);
        HttpServletRequest request = getRequest();
        LOGGER.info("[request path]: {}", request.getRequestURL());
        StringBuilder params = new StringBuilder();
        // arguments values
        Object[] argValues = joinPoint.getArgs();
        // arguments names
        String[] argNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        if (argValues != null) {
            for (int i = 0; i < argValues.length; i++) {
                if (i != 0) params.append(", ");
                params.append(argNames[i]).append(":").append(argValues[i]);
            }
        }
        LOGGER.info("[request parameters]: [{}]", params + "");
        Object proceed = joinPoint.proceed();
        LOGGER.info("[response data]: [{}]", JSON.toJSON(proceed));
//        LOGGER.info("[execute time] :{}", (System.currentTimeMillis() - startTime));
        LOGGER.debug("----- log aspect end -----");
        return proceed;
    }

    /**
     * 获取HttpServletRequest
     *
     * @return
     */
    private HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        assert servletRequestAttributes != null;
        return servletRequestAttributes.getRequest();
    }
}
