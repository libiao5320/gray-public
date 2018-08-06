package com.unicom.annotion;

import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author
 * @create 2018-06-20 11:05
 **/
@Target(FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldDesc {

    String value() default"" ;

}
