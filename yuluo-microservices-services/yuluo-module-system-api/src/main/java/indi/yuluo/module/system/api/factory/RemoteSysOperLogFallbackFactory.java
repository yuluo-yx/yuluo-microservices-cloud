package indi.yuluo.module.system.api.factory;

import java.util.List;
import java.util.Map;

import indi.yuluo.common.domain.system.SysOperLog;
import indi.yuluo.common.result.Result;
import indi.yuluo.module.system.api.RemoteSysOperLogService;
import indi.yuluo.module.system.api.RemoteSysUserTestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author: yuluo
 * @date: 2023/5/16 20:04
 * @description: 系统日志控制器服务降级处理
 */

@Component
public class RemoteSysOperLogFallbackFactory implements FallbackFactory<RemoteSysOperLogService> {

	private static final Logger log = LoggerFactory.getLogger(RemoteSysOperLogFallbackFactory.class);

	@Override
	public RemoteSysOperLogService create(Throwable throwable) {

		log.error("系统日志服务调用失败:{}", throwable.getMessage());

		return new RemoteSysOperLogService() {

			@Override
			public Result<Map<String, List<SysOperLog>>> getOperLog() {

				return Result.failed("获取系统日志信息失败：" + throwable.getMessage());
			}

			@Override
			public Result<Boolean> delOperLog() {

				return Result.failed("删除系统日志信息失败：" + throwable.getMessage());
			}

			@Override
			public Result<Boolean> saveOperLog(SysOperLog sysOperLog) {

				return Result.failed("插入系统日志信息失败：" + throwable.getMessage());
			}
		};
	}
}
