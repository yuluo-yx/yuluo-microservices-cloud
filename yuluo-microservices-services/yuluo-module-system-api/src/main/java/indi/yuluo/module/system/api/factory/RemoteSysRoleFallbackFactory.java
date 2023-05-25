package indi.yuluo.module.system.api.factory;

import java.util.List;
import java.util.Map;

import indi.yuluo.common.domain.system.SysRole;
import indi.yuluo.common.result.Result;
import indi.yuluo.module.system.api.RemoteSysRoleService;
import indi.yuluo.module.system.api.RemoteSysUserTestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author: yuluo
 * @date: 2023/5/16 20:04
 * @description: 系统权限服务降级处理
 */

@Component
public class RemoteSysRoleFallbackFactory implements FallbackFactory<RemoteSysRoleService> {

	private static final Logger log = LoggerFactory.getLogger(RemoteSysRoleFallbackFactory.class);

	@Override
	public RemoteSysRoleService create(Throwable throwable) {

		log.error("系统权限服务调用失败:{}", throwable.getMessage());

		return new RemoteSysRoleService() {

			@Override
			public Result<Map<String, List<SysRole>>> getRole() {

				return Result.failed("获取权限信息失败：" + throwable.getMessage());
			}

			@Override
			public Result<SysRole> getRoleByRoleId(String roelId) {

				return Result.failed("根据roleId获取权限信息失败：" + throwable.getMessage());
			}

			@Override
			public Result<Boolean> delRole(String roleId) {

				return Result.failed("根据权限信息id删除权限信息失败：" + throwable.getMessage());
			}

			@Override
			public Result<Boolean> updateRole(SysRole sysRole) {

				return Result.failed("更新权限信息失败：" + throwable.getMessage());
			}

			@Override
			public Result<Boolean> updateRoleTest() {

				return Result.failed("测试更新权限并写入操作日志失败：" + throwable.getMessage());
			}
		};
	}
}
