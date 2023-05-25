package indi.yuluo.module.system.controller;

import indi.yuluo.common.domain.system.SysUser;
import indi.yuluo.common.result.Result;
import indi.yuluo.module.system.service.SysUserTestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: yuluo
 * @date: 2023/5/15 20:52
 * @description: some desc
 */

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/test")
public class SysUserTestController {

	private final SysUserTestService sysUserTestService;

	@Value("${spring.data.redis.password}")
	private String redisPassword;

	/**
	 * 接口请求测试
	 * @return 测试字符串
	 */
	@GetMapping("/user")
	public Result<String> testUser() {
		return Result.success(sysUserTestService.testUser());
	}

	/**
	 * 测试从 nacos config 中获取配置信息
	 * @return redis 密码数据
	 */
	@GetMapping("/nacos/config")
	public Result<String> testConfig() {
		return Result.success(redisPassword);
	}

	/**
	 * 数据脱敏测试
	 * @return SysUser
	 */
	@GetMapping("/testSensitive")
	public Result<SysUser> testSensitive() {

		var user = SysUser.builder()
				.userId(1L)
				.email("780879337@qq.com")
				.password("123456")
				.phonenumber("18198086793")
				.nickName("yuluo")
				.build();

		return Result.success(user);
	}

}
