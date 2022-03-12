package com.company;

import java.lang.reflect.Method;

/**
 * 注解解释器
 */
public class UseCaseTracker {
    public static void trackUseCase(Class<?> cl) {
        for (Method m : cl.getDeclaredMethods()) { //遍历Class所有方法
            UseCase uc = m.getAnnotation(UseCase.class);
            if(uc == null) {

            }
        }

    }
}
