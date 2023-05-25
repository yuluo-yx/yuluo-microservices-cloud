package indi.yuluo.module.system.controller;

import java.util.List;
import java.util.Map;

import indi.yuluo.common.domain.system.SysUser;
import indi.yuluo.common.log.annotation.Log;
import indi.yuluo.common.log.enums.BusinessType;
import indi.yuluo.common.result.Result;
import indi.yuluo.module.system.service.SysUserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: yuluo
 * @date: 2023/5/22 15:05
 * @description: some desc
 */

@Slf4j
@RestController
@RequestMapping("/user")
public class SysUserController {

	@Resource
	private SysUserService userService;

	/**
	 * 获取所有的用户信息
	 * @return Map<String, List<SysUser>>
	 */
	@GetMapping("/getUserInfo")
	public Result<Map<String, List<SysUser>>> getUserInfo() {

		return Result.success(userService.getUserInfo());
	}

	/**
	 * 根据用户名获取用户信息
	 * @param username 用户名
	 * @return SysUser 实体对象
	 */
	@GetMapping("/getUserInfoByUsername/{username}")
	public Result<SysUser> getUserInfoByUsername(@PathVariable("username") String username) {

		return Result.success(userService.getUserInfoByUsername(username));
	}

	/**
	 * 根据邮箱获取用户信息
	 * @param email 邮箱
	 * @return SysUser 实体对象
	 */
	@GetMapping("/getUserInfoByEmail/{email}")
	public Result<SysUser> getUserInfoByEmail(@PathVariable("email") String email) {

		return Result.success(userService.getUserInfoByEmail(email));
	}

	/**
	 * 根据用户id更新用户信息
	 * @param user 用户id
	 * @return 更新标记
	 */
	@PutMapping("/updateUserInfo/")
	@Log(title = "根据用户id更新用户信息", businessType = BusinessType.UPDATE)
	public Result<Boolean> updateUserInfoByUserId(@RequestBody SysUser user) {

		return Result.success(userService.updateUserInfoByUserId(user));
	}

	/**
	 * 保存用户信息
	 * @param user 用户实体
	 * @return 保存标记
	 */
	@PostMapping("/saveUserInfo")
	public Result<Boolean> saveUser(@RequestBody SysUser user) {

		return Result.success(userService.saveUser(user));
	}

	/**
	 * 删除用户信息
	 * @param userId 用户id
	 * @return 删除标记
	 */
	@Log(title = "删除用户信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/delUserInfo/{userId}")
	public Result<Boolean> delUserInfoByUSerId(@PathVariable("userId") String userId) {

		return Result.success(userService.delUserInfoBuUserId(userId));
	}

}
