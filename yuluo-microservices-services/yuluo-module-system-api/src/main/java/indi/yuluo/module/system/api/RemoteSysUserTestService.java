package indi.yuluo.module.system.api;

import indi.yuluo.common.constant.ServiceNameConstants;
import indi.yuluo.common.result.Result;
import indi.yuluo.module.system.api.factory.RemoteSysUserTestFallbackFactory;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author: yuluo
 * @date: 2023/5/16 20:05
 * @description: Remote System Service Interface
 */

@FeignClient(contextId = "remoteSysUserTestService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteSysUserTestFallbackFactory.class)
public interface RemoteSysUserTestService {

	/**
	 * 接口请求测试
	 * @return 测试字符串
	 */
	@GetMapping("/test/user")
	Result<String> testUser();

	/**
	 * 测试从 nacos config 中获取配置信息
	 * @return redis 密码数据
	 */
	@GetMapping("/test/nacos/config")
	Result<String> testConfig();

}
