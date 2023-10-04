package indi.yuluo.gateway.config;

import indi.yuluo.gateway.handler.ValidateCodeHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

/**
 * @author: yuluo
 * @date: 2023/5/19 17:19
 * @description: 路由配置信息
 */

@Configuration
public class RouterFunctionConfiguration {

	@Autowired
	private ValidateCodeHandler validateCodeHandler;

	/**
	 * 请求此接口获取验证码
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Bean
	public RouterFunction routerFunction() {
		return RouterFunctions.route(
				RequestPredicates
						.GET("/code")
						.and(RequestPredicates
						.accept(MediaType.TEXT_PLAIN)),
				validateCodeHandler);
	}

}
