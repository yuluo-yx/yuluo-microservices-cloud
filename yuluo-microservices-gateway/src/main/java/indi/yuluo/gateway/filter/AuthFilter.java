package indi.yuluo.gateway.filter;

import java.util.List;
import java.util.Map;
import java.util.Set;

import indi.yuluo.common.constant.CacheConstants;
import indi.yuluo.common.constant.HttpStatus;
import indi.yuluo.common.constant.SecurityConstants;
import indi.yuluo.common.constant.TokenConstants;
import indi.yuluo.common.redis.service.RedisService;
import indi.yuluo.common.utils.JwtUtils;
import indi.yuluo.common.utils.ServletUtils;
import indi.yuluo.common.utils.StringUtils;
import indi.yuluo.gateway.config.properties.IgnoreWhiteProperties;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

/**
 * @author: yuluo
 * @date: 2023/5/15 20:03
 * @description: 网关鉴权
 */

@Component
public class AuthFilter implements GlobalFilter, Ordered {

	private static final Logger log = LoggerFactory.getLogger(AuthFilter.class);

	/**
	 * 排除过滤的 uri 地址，nacos 自行添加
	 */
	@Autowired
	private IgnoreWhiteProperties ignoreWhite;

	@Autowired
	private RedisService redisService;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

		ServerHttpRequest request = exchange.getRequest();
		ServerHttpRequest.Builder mutate = request.mutate();

		String url = request.getURI().getPath();

		// 跳过不需要验证的路径
		if (StringUtils.matches(url, ignoreWhite.getWhites())) {

			return chain.filter(exchange);
		}

		// 从 request 中获取 token
		String token = getToken(request);

		if (StringUtils.isEmpty(token)) {
			return unauthorizedResponse(exchange, "令牌 Token 不能为空");
		}

		// Token 解析
		Claims claims = JwtUtils.parseToken(token);
		if (claims == null) {
			return unauthorizedResponse(exchange, "令牌已过期或验证不正确！");
		}

		String userKey = JwtUtils.getUserKey(claims);
		boolean isLogin = redisService.hasKey(getTokenKey(userKey));
		if (!isLogin) {
			return unauthorizedResponse(exchange, "登录状态已过期");
		}

		String userid = JwtUtils.getUserId(claims);
		String username = JwtUtils.getUserName(claims);
		if (StringUtils.isEmpty(userid) || StringUtils.isEmpty(username)) {
			return unauthorizedResponse(exchange, "令牌验证失败");
		}

		System.out.println("设置用户信息到请求时的参数值：userKey = " + userKey + " - userId = " +  userid + " - username = " + username );

		// 设置用户信息到请求
		addHeader(mutate, SecurityConstants.USER_KEY, userKey);
		addHeader(mutate, SecurityConstants.DETAILS_USER_ID, userid);
		addHeader(mutate, SecurityConstants.DETAILS_USERNAME, username);

		// 内部请求来源参数清除
		removeHeader(mutate, SecurityConstants.FROM_SOURCE);
		return chain.filter(exchange.mutate().request(mutate.build()).build());
	}

	private void addHeader(ServerHttpRequest.Builder mutate, String name, Object value) {
		if (value == null) {
			return;
		}
		String valueStr = value.toString();
		String valueEncode = ServletUtils.urlEncode(valueStr);
		mutate.header(name, valueEncode);
	}

	private void removeHeader(ServerHttpRequest.Builder mutate, String name) {
		mutate.headers(httpHeaders -> httpHeaders.remove(name)).build();
	}

	private Mono<Void> unauthorizedResponse(ServerWebExchange exchange, String msg) {
		log.error("[鉴权异常处理]请求路径：{}, 提示信息：{}", exchange.getRequest().getPath(), msg);
		return ServletUtils.webFluxResponseWriter(exchange.getResponse(), msg, HttpStatus.UNAUTHORIZED);
	}

	/**
	 * 获取缓存key
	 */
	private String getTokenKey(String token) {

		return CacheConstants.LOGIN_TOKEN_KEY + token;
	}

	/**
	 * 获取请求token
	 */
	private String getToken(ServerHttpRequest request) {

		String token = request.getHeaders().getFirst(TokenConstants.AUTHENTICATION);
		// 如果前端设置了令牌前缀，则裁剪掉前缀
		if (StringUtils.isNotEmpty(token)) {
			assert token != null;
			if (token.startsWith(TokenConstants.PREFIX)) {
				token = token.replaceFirst(TokenConstants.PREFIX, StringUtils.EMPTY);
			}
		}
		return token;
	}

	@Override
	public int getOrder() {
		return -200;
	}

	/**
	 * 获取 request 所有参数
	 * @param request
	 */
	private void getAllRequestParams(ServerHttpRequest request) {
		System.out.println("request 参数如下：");
		HttpHeaders headers = request.getHeaders();
		Set<Map.Entry<String, List<String>>> entries = headers.entrySet();
		for (Map.Entry<String, List<String>> entry : entries) {
			List<String> value = entry.getValue();
			for (String s : value) {
				System.out.println(s);
			}
		}
	}

}
