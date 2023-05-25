package indi.yuluo.gateway.handler;

import indi.yuluo.common.utils.ServletUtils;
import reactor.core.publisher.Mono;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;

/**
 * @author: yuluo
 * @date: 2023/5/15 20:00
 * @description: 网关统一异常处理
 */

@Order(-1)
@Configuration
public class GatewayExceptionHandler implements ErrorWebExceptionHandler {
	private static final Logger log = LoggerFactory.getLogger(GatewayExceptionHandler.class);

	@Override
	public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
		ServerHttpResponse response = exchange.getResponse();

		if (exchange.getResponse().isCommitted()) {
			return Mono.error(ex);
		}

		String msg;

		if (ex instanceof NotFoundException) {
			msg = "服务未找到";
		}
		else if (ex instanceof ResponseStatusException responseStatusException) {
			msg = responseStatusException.getMessage();
		}
		else {
			msg = "内部服务器错误";
		}

		log.error("[网关异常处理]请求路径:{},异常信息:{}", exchange.getRequest().getPath(), ex.getMessage());

		return ServletUtils.webFluxResponseWriter(response, msg);
	}
}
