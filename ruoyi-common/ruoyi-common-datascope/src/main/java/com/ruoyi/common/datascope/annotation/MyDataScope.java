package com.ruoyi.common.datascope.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyDataScope {
    public String alias() default "";
}
