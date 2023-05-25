package indi.yuluo.module.system.api;

import java.util.List;
import java.util.Map;

import indi.yuluo.common.constant.ServiceNameConstants;
import indi.yuluo.common.domain.system.SysUser;
import indi.yuluo.common.result.Result;
import indi.yuluo.module.system.api.factory.RemoteSysUserFallbackFactory;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author: yuluo
 * @date: 2023/5/22 16:42
 * @description: RemoteSysUserService
 */

@FeignClient(contextId = "remoteSysUserService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteSysUserFallbackFactory.class)
public interface RemoteSysUserService {

	/**
	 * 获取所有的用户信息
	 * @return Map<String, List<SysUser>>
	 */
	@GetMapping("/user/getUserInfo")
	Result<Map<String, List<SysUser>>> getUserInfo();

	/**
	 * 根据用户名获取用户信息
	 * @param username 用户名
	 * @return SysUser 实体对象
	 */
	@GetMapping("/user/getUserInfoByUsername/{username}")
	Result<SysUser> getUserInfoByUsername(@PathVariable("username") String username);

	/**
	 * 根据邮箱获取用户信息
	 * @param email 邮箱
	 * @return SysUser 实体对象
	 */
	@GetMapping("/user/getUserInfoByEmail/{email}")
	Result<SysUser> getUserInfoByEmail(@PathVariable("email") String email);

	/**
	 * 根据用户id更新用户信息
	 * @param user 用户id
	 * @return 更新标记
	 */
	@PutMapping("/user/updateUserInfo/")
	Result<Boolean> updateUserInfoByUserId(@RequestBody SysUser user);

	/**
	 * 保存用户信息
	 * @param user 用户实体
	 * @return 保存标记
	 */
	@PostMapping("/user/saveUserInfo")
	Result<Boolean> saveUser(@RequestBody SysUser user);

	/**
	 * 删除用户信息
	 * @param userId 用户id
	 * @return 删除标记
	 */
	@DeleteMapping("/user/delUserInfo/{userId}")
	Result<Boolean> delUserInfoByUSerId(@PathVariable("userId") String userId);

}
