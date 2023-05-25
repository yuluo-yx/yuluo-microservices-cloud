package indi.yuluo.module.system.api.factory;

import indi.yuluo.common.result.Result;
import indi.yuluo.module.system.api.RemoteSysUserTestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author: yuluo
 * @date: 2023/5/16 20:04
 * @description: 系统测试控制器服务降级处理
 */

@Component
public class RemoteSysUserTestFallbackFactory implements FallbackFactory<RemoteSysUserTestService> {

	private static final Logger log = LoggerFactory.getLogger(RemoteSysUserTestFallbackFactory.class);

	@Override
	public RemoteSysUserTestService create(Throwable throwable) {

		log.error("系统测试服务调用失败:{}", throwable.getMessage());

		return new RemoteSysUserTestService() {
			@Override
			public Result<String> testUser() {
				return Result.failed("获取用户信息失败：" + throwable.getMessage());
			}

			@Override
			public Result<String> testConfig() {
				return Result.failed("拉取配置失败：" + throwable.getMessage());
			}

		};
	}
}
