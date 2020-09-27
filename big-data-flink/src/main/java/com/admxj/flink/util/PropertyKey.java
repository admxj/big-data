package com.admxj.flink.util;

import java.lang.annotation.*;

/**
 * @author admxj
 * @version Id: PropertyKey, v 0.1 2020/9/26 1:31 上午 admxj Exp $
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PropertyKey {

    String value() default "";
}
