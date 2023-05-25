package indi.yuluo.module.system.api;

import java.util.List;
import java.util.Map;

import indi.yuluo.common.constant.ServiceNameConstants;
import indi.yuluo.common.domain.system.SysLoginInfo;
import indi.yuluo.common.result.Result;
import indi.yuluo.module.system.api.factory.RemoteSysLoginInfoFallbackFactory;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author: yuluo
 * @date: 2023/5/22 16:41
 * @description: RemoteSysLoginInfoService
 */

@FeignClient(contextId = "remoteSysLoginInfoService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteSysLoginInfoFallbackFactory.class)
public interface RemoteSysLoginInfoService {

	/**
	 * 获取系统访问日志
	 * @return List
	 */
	@GetMapping("/loginInfo/getLoginInfo")
	Result<Map<String, List<SysLoginInfo>>> getLoginInfo();

	/**
	 * 删除系统访问记录
	 * @return 删除标记
	 */
	@DeleteMapping("/loginInfo/delLoginInfo")
	Result<Boolean> delLoginInfo();

	/**
	 * 查询指定用户登录记录
	 * @return List<SysLoginInfo>
	 */
	@GetMapping("/loginInfo/getLoginInfoByUsername/{username}")
	Result<Map<String, List<SysLoginInfo>>> getLoginInfoByUserName(@PathVariable("username") String username);

	@PostMapping("/loginInfo/saveLoginInfo")
	void saveLoginInfo(@RequestBody SysLoginInfo sysLoginInfo);

}
