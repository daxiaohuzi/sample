package com.library.Code;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 接收数据
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})

public @interface ReceiveData {
}
