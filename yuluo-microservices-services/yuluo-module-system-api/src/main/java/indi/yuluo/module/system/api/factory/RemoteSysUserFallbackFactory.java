package indi.yuluo.module.system.api.factory;

import java.util.List;
import java.util.Map;

import indi.yuluo.common.domain.system.SysUser;
import indi.yuluo.common.result.Result;
import indi.yuluo.module.system.api.RemoteSysUserService;
import indi.yuluo.module.system.api.RemoteSysUserTestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author: yuluo
 * @date: 2023/5/16 20:04
 * @description: 系统用户控制器服务降级处理
 */

@Component
public class RemoteSysUserFallbackFactory implements FallbackFactory<RemoteSysUserService> {

	private static final Logger log = LoggerFactory.getLogger(RemoteSysUserTestFallbackFactory.class);

	@Override
	public RemoteSysUserService create(Throwable throwable) {

		log.error("系统用户服务调用失败:{}", throwable.getMessage());

		return new RemoteSysUserService() {

			@Override
			public Result<Map<String, List<SysUser>>> getUserInfo() {

				return Result.failed("获取用户信息失败：" + throwable.getMessage());
			}

			@Override
			public Result<SysUser> getUserInfoByUsername(String username) {

				return Result.failed("根据用户名获取用户信息失败：" + throwable.getMessage());
			}

			@Override
			public Result<SysUser> getUserInfoByEmail(String email) {

				return Result.failed("根据邮箱获取用户信息失败：" + throwable.getMessage());
			}

			@Override
			public Result<Boolean> updateUserInfoByUserId(SysUser user) {

				return Result.failed("根据用户id更新用户信息失败：" + throwable.getMessage());
			}

			@Override
			public Result<Boolean> saveUser(SysUser user) {

				return Result.failed("保存用户信息失败：" + throwable.getMessage());
			}

			@Override
			public Result<Boolean> delUserInfoByUSerId(String userId) {

				return Result.failed("根据用户id删除用户信息失败：" + throwable.getMessage());
			}
		};
	}
}
