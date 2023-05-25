package indi.yuluo.gateway.filter;

import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import indi.yuluo.common.utils.ServletUtils;
import indi.yuluo.common.utils.StringUtils;
import indi.yuluo.gateway.config.properties.CaptchaProperties;
import indi.yuluo.gateway.service.ValidateCodeService;
import reactor.core.publisher.Flux;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

/**
 * @author: yuluo
 * @date: 2023/5/15 20:13
 * @description: 验证码过滤器
 */

@Component
public class ValidateCodeFilter extends AbstractGatewayFilterFactory<Object> {

	private final static String[] VALIDATE_URL = new String[] {"/auth/login", "/auth/register"};

	@Autowired
	private ValidateCodeService validateCodeService;

	@Autowired
	private CaptchaProperties captchaProperties;

	private static final String CODE = "code";

	private static final String UUID = "uuid";

	@Override
	public GatewayFilter apply(Object config) {

		return (exchange, chain) -> {

			ServerHttpRequest request = exchange.getRequest();

			// 非登录/注册请求或验证码关闭，不处理
			if (
					!StringUtils.containsAnyIgnoreCase(request.getURI()
					.getPath(), VALIDATE_URL)
					||
					!captchaProperties.getEnabled()
			) {
				return chain.filter(exchange);
			}

			try {
				String rspStr = resolveBodyFromRequest(request);
				JSONObject obj = JSON.parseObject(rspStr);
				validateCodeService.checkCaptcha(obj.getString(CODE), obj.getString(UUID));
			}
			catch (Exception e) {
				return ServletUtils.webFluxResponseWriter(exchange.getResponse(), e.getMessage());
			}
			return chain.filter(exchange);
		};
	}

	private String resolveBodyFromRequest(ServerHttpRequest serverHttpRequest) {

		// 获取请求体
		Flux<DataBuffer> body = serverHttpRequest.getBody();
		AtomicReference<String> bodyRef = new AtomicReference<>();

		body.subscribe(buffer -> {
			CharBuffer charBuffer = StandardCharsets.UTF_8.decode(buffer.asByteBuffer());
			DataBufferUtils.release(buffer);
			bodyRef.set(charBuffer.toString());
		});

		return bodyRef.get();
	}
}
