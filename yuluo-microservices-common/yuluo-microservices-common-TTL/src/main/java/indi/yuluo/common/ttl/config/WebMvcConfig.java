package indi.yuluo.common.ttl.config;

import indi.yuluo.common.ttl.interceptor.HeaderInterceptor;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: yuluo
 * @date: 2023/5/22 19:21
 * @description: 拦截器配置
 */

public class WebMvcConfig implements WebMvcConfigurer {

	/** 不需要拦截地址 */
	public static final String[] excludeUrls = {"/token/auth/login", "/token/auth/logout", "/code"};

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(getHeaderInterceptor())
				.addPathPatterns("/**")
				.excludePathPatterns(excludeUrls)
				.order(-10);
	}

	/**
	 * 自定义请求头拦截器
	 */
	public HeaderInterceptor getHeaderInterceptor() {

		return new HeaderInterceptor();
	}

}
