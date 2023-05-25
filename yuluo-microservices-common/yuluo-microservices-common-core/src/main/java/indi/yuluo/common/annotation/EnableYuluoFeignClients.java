package indi.yuluo.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author: yuluo
 * @date: 2023/5/22 19:53
 * @description: 自定义feign注解，添加basePackages路径
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableFeignClients
public @interface EnableYuluoFeignClients {
	String[] value() default {};

	String[] basePackages() default { "indi.yuluo" };

	Class<?>[] basePackageClasses() default {};

	Class<?>[] defaultConfiguration() default {};

	Class<?>[] clients() default {};
}
