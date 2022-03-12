package com.company;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解
 */
@Target(ElementType.METHOD) //定义注解使用范围
@Retention(RetentionPolicy.RUNTIME) //定义注解使用级别 在源码中(SOURCE)、类文件中(CLASS)、运行时(RUNTIME)
public @interface Test {
}
