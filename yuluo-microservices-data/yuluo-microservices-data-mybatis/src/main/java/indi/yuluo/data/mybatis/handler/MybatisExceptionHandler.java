package indi.yuluo.data.mybatis.handler;

import indi.yuluo.common.result.Result;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.MyBatisSystemException;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author: yuluo
 * @date: 2023/5/18 9:16
 * @description: Mybatis异常处理器
 */

@Slf4j
@RestControllerAdvice
public class MybatisExceptionHandler {

	/**
	 * 主键或UNIQUE索引，数据重复异常
	 */
	@ExceptionHandler(DuplicateKeyException.class)
	public Result<Void> handleDuplicateKeyException(DuplicateKeyException e, HttpServletRequest request) {
		String requestURI = request.getRequestURI();
		log.error("请求地址'{}',数据库中已存在记录'{}'", requestURI, e.getMessage());
		return Result.failed("数据库中已存在该记录，请联系管理员确认");
	}

	/**
	 * Mybatis系统异常 通用处理
	 */
	@ExceptionHandler(MyBatisSystemException.class)
	public Result<Void> handleCannotFindDataSourceException(MyBatisSystemException e, HttpServletRequest request) {
		String requestURI = request.getRequestURI();
		String message = e.getMessage();
		if (message.contains("CannotFindDataSourceException")) {
			log.error("请求地址'{}', 未找到数据源", requestURI);
			return Result.failed("未找到数据源，请联系管理员确认");
		}
		log.error("请求地址'{}', Mybatis系统异常", requestURI, e);
		return Result.failed(message);
	}

}
