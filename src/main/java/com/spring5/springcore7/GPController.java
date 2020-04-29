package com.spring5.springcore7;

import java.lang.annotation.*;

/**
 * @author yinxf
 * @date 2020-04-29
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GPController {
    String value() default "";
}
