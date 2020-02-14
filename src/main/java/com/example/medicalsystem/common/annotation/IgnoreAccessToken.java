package com.example.medicalsystem.common.annotation;

import java.lang.annotation.*;

/**
 * 忽略token验证
 *     1. IgnoreAccessToken 放到方法,则该方法禁用用token验证,但如果有token会注入用户信息
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreAccessToken {
}
