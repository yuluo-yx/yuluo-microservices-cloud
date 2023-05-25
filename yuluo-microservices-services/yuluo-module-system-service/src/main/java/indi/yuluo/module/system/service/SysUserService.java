package indi.yuluo.module.system.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import indi.yuluo.common.domain.system.SysUser;

/**
 * @author: yuluo
 * @date: 2023/5/22 15:06
 * @description: 系统用户服务层接口
 */

public interface SysUserService extends IService<SysUser> {

	/**
	 * 获取所有的用户信息
	 * @return Map<String, List<SysUser>>
	 */
	Map<String, List<SysUser>> getUserInfo();

	/**
	 * 根据用户名获取用户信息
	 * @param username 用户名
	 * @return SysUser 实体对象
	 */
	SysUser getUserInfoByUsername(String username);

	/**
	 * 根据邮箱获取用户信息
	 * @param email 邮箱
	 * @return SysUser 实体对象
	 */
	SysUser getUserInfoByEmail(String email);

	/**
	 * 根据用户id更新用户信息
	 * @param user 用户
	 * @return 更新标记
	 */
	Boolean updateUserInfoByUserId(SysUser user);

	/**
	 * 保存用户信息
	 * @param user 用户实体
	 * @return 保存标记
	 */
	Boolean saveUser(SysUser user);

	/**
	 * 删除用户信息
	 * @param userId 用户id
	 * @return 删除标记
	 */
	Boolean delUserInfoBuUserId(String userId);
}
