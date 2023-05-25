package indi.yuluo.common.log.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import indi.yuluo.common.log.enums.BusinessType;
import indi.yuluo.common.log.enums.OperatorType;

/**
 * @author: yuluo
 * @date: 2023/5/17 18:55
 * @description: 自定义操作日志记录注解
 */

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
	/**
	 * 模块
	 */
	String title() default "";

	/**
	 * 功能
	 */
	BusinessType businessType() default BusinessType.OTHER;

	/**
	 * 操作人类别
	 */
	OperatorType operatorType() default OperatorType.MANAGE;

	/**
	 * 是否保存请求的参数
	 */
	boolean isSaveRequestData() default true;

	/**
	 * 是否保存响应的参数
	 */
	boolean isSaveResponseData() default true;

	/**
	 * 排除指定的请求参数
	 */
	String[] excludeParamNames() default {};
}
