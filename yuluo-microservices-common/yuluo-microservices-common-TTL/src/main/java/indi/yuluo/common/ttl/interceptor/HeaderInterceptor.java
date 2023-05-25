package indi.yuluo.common.ttl.interceptor;

import java.util.Enumeration;

import indi.yuluo.common.constant.SecurityConstants;
import indi.yuluo.common.ttl.context.UserContextHolder;
import indi.yuluo.common.utils.ServletUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

/**
 * @author: yuluo
 * @date: 2023/5/22 18:06
 * @description: 自定义请求头拦截器，将Header数据封装到线程变量中方便获取
 * 注意：此拦截器会同时验证当前用户有效期自动刷新有效期
 */

public class HeaderInterceptor implements AsyncHandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		if (!(handler instanceof HandlerMethod)) {
			return true;
		}

		// System.out.println("经过 HeaderInterceptor 的请求路径为：" + request.getRequestURI());

/*
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			System.out.println(headerNames.nextElement() + " = " + request.getHeader(headerNames.nextElement()));
		}
*/

		UserContextHolder.setUserId(ServletUtils.getHeader(request, SecurityConstants.DETAILS_USER_ID));
		UserContextHolder.setUserName(ServletUtils.getHeader(request, SecurityConstants.DETAILS_USERNAME));
		UserContextHolder.setUserKey(ServletUtils.getHeader(request, SecurityConstants.USER_KEY));
		UserContextHolder.setUserToken(ServletUtils.getHeader(request, SecurityConstants.AUTHORIZATION_HEADER));

		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		UserContextHolder.remove();
	}
}

