package com.example.medicalsystem.common.annotation;

import java.lang.annotation.*;

/**
 * token验证
 *     1. AccessToken 放到类上优先级高于方法,如果放到类上则类的所有方法都需要token验证
 *     2. AccessToken 放到方法,则该方法启用token验证
 * </p>
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AccessToken {
}
