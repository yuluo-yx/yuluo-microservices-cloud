package indi.yuluo.module.system.api.factory;

import java.util.List;
import java.util.Map;

import indi.yuluo.common.domain.system.SysLoginInfo;
import indi.yuluo.common.result.Result;
import indi.yuluo.module.system.api.RemoteSysLoginInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author: yuluo
 * @date: 2023/5/22 16:44
 * @description: 系统访问控制器服务降级处理
 */

@Component
public class RemoteSysLoginInfoFallbackFactory implements FallbackFactory<RemoteSysLoginInfoService> {

	private static final Logger log = LoggerFactory.getLogger(RemoteSysLoginInfoFallbackFactory.class);

	@Override
	public RemoteSysLoginInfoService create(Throwable throwable) {

		log.error("系统权限服务调用失败:{}", throwable.getMessage());

		return new RemoteSysLoginInfoService() {

			@Override
			public Result<Map<String, List<SysLoginInfo>>> getLoginInfo() {

				return Result.failed("获取系统访问信息失败：" + throwable.getMessage());
			}

			@Override
			public Result<Boolean> delLoginInfo() {

				return Result.failed("删除系统访问日志信息失败：" + throwable.getMessage());
			}

			@Override
			public Result<Map<String, List<SysLoginInfo>>> getLoginInfoByUserName(String username) {

				return Result.failed("根据用户名获取系统访问日志信息失败：" + throwable.getMessage());
			}

			@Override
			public void saveLoginInfo(SysLoginInfo sysLoginInfo) {}

		};
	}
}

