package indi.yuluo.auth.service;

import java.util.Optional;

import com.baomidou.mybatisplus.extension.service.IService;
import indi.yuluo.auth.module.CurrentUser;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author: yuluo
 * @date: 2023/5/19 21:32
 * @description: 用户登录接口
 */

public interface SysUserLoginService extends IService<CurrentUser> {

	/**
	 * 用户登录方法
	 * @param username 用户名
	 * @param password 密码
	 * @return 当前登录用户
	 */
	CurrentUser login(String username, String password);

	/**
	 * 登出方法
	 */
	void logout(HttpServletRequest request);

	/**
	 * 注册方法
	 * @param username 用户名
	 * @param password 密码
	 */
	void register(String username, String password);
}
