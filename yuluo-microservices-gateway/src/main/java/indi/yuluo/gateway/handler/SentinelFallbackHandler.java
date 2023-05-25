package indi.yuluo.gateway.handler;

import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import indi.yuluo.common.utils.ServletUtils;
import reactor.core.publisher.Mono;

import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;

/**
 * @author: yuluo
 * @date: 2023/5/15 19:48
 * @description: some desc
 */

public class SentinelFallbackHandler implements WebExceptionHandler {
	private Mono<Void> writeResponse(ServerResponse response, ServerWebExchange exchange) {
		return ServletUtils.webFluxResponseWriter(exchange.getResponse(), "请求超过最大数，请稍候再试");
	}

	@Override
	public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
		if (exchange.getResponse().isCommitted()) {
			return Mono.error(ex);
		}
		if (!BlockException.isBlockException(ex)) {
			return Mono.error(ex);
		}
		return handleBlockedRequest(exchange, ex).flatMap(response -> writeResponse(response, exchange));
	}

	private Mono<ServerResponse> handleBlockedRequest(ServerWebExchange exchange, Throwable throwable) {
		return GatewayCallbackManager.getBlockHandler().handleRequest(exchange, throwable);
	}
}
