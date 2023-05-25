package indi.yuluo.gateway.config;

import indi.yuluo.gateway.handler.SentinelFallbackHandler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * @author: yuluo
 * @date: 2023/5/15 19:44
 * @description: 网关限流配置
 */

@Configuration
public class GatewayConfig {
	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	public SentinelFallbackHandler sentinelGatewayExceptionHandler() {
		return new SentinelFallbackHandler();
	}
}
