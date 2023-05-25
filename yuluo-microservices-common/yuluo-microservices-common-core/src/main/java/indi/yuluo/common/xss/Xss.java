package indi.yuluo.common.xss;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.Retention;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * @author: yuluo
 * @date: 2023/5/17 19:15
 * @description: 自定义xss校验注解
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(value = { ElementType.METHOD, ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.PARAMETER })
@Constraint(validatedBy = { XssValidator.class })
public @interface Xss
{
	String message()

			default "不允许任何脚本运行";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
