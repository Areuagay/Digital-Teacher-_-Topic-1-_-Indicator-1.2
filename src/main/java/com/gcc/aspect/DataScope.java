package com.gcc.aspect;

import java.lang.annotation.*;

/**
 * 数据权限过滤注解
 * 
 * @author
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataScope
{
    /**
     * 表的别名
     */
    public String tableAlias() default "";
}
