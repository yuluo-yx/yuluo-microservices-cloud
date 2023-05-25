package indi.yuluo.common.ttl.feign;

import java.util.Map;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import indi.yuluo.common.constant.SecurityConstants;
import indi.yuluo.common.utils.ServletUtils;
import indi.yuluo.common.utils.StringUtils;
import indi.yuluo.common.utils.ip.IpUtils;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

/**
 * @author: yuluo
 * @date: 2023/5/22 19:26
 * @description: feign 请求拦截器
 */

@Component
public class FeignRequestInterceptor implements RequestInterceptor {

	@Override
	public void apply(RequestTemplate requestTemplate) {

		HttpServletRequest httpServletRequest = ServletUtils.getRequest();
		if (StringUtils.isNotNull(httpServletRequest)) {
			Map<String, String> headers = ServletUtils.getHeaders(httpServletRequest);
			// 传递用户信息请求头，防止丢失
			String userId = headers.get(SecurityConstants.DETAILS_USER_ID);
			if (StringUtils.isNotEmpty(userId)) {
				requestTemplate.header(SecurityConstants.DETAILS_USER_ID, userId);
			}
			String userKey = headers.get(SecurityConstants.USER_KEY);
			if (StringUtils.isNotEmpty(userKey)) {
				requestTemplate.header(SecurityConstants.USER_KEY, userKey);
			}
			String userName = headers.get(SecurityConstants.DETAILS_USERNAME);
			if (StringUtils.isNotEmpty(userName)) {
				requestTemplate.header(SecurityConstants.DETAILS_USERNAME, userName);
			}
			String authentication = headers.get(SecurityConstants.AUTHORIZATION_HEADER);
			if (StringUtils.isNotEmpty(authentication)) {
				requestTemplate.header(SecurityConstants.AUTHORIZATION_HEADER, authentication);
			}

			// 配置客户端IP
			requestTemplate.header("X-Forwarded-For", IpUtils.getIpAddr());
		}
	}
}
