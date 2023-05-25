package indi.yuluo.module.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import indi.yuluo.common.domain.system.SysUser;
import indi.yuluo.module.system.mapper.SysRoleMapper;
import indi.yuluo.module.system.mapper.SysUserMapper;
import indi.yuluo.module.system.service.SysUserService;
import jakarta.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * @author: yuluo
 * @date: 2023/5/22 15:08
 * @description: 用户服务层接口实现
 */

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

	@Resource
	private SysRoleMapper sysRoleMapper;

	/**
	 * 获取所有的用户信息
	 * @return Map<String, List<SysUser>>
	 */
	@Override
	public Map<String, List<SysUser>> getUserInfo() {

		HashMap<String, List<SysUser>> map = new HashMap<>();

		map.put("loginInfoList", this.list(
				new LambdaQueryWrapper<SysUser>()
						.isNotNull(SysUser::getUserId)));

		return map;
	}

	/**
	 * 根据用户名获取用户信息
	 * @param username 用户名
	 * @return SysUser 实体对象
	 */
	@Override
	public SysUser getUserInfoByUsername(String username) {

		SysUser user = this.getOne(
				new LambdaQueryWrapper<SysUser>()
						.eq(SysUser::getUserName, username)
		);

		user.setRoles(sysRoleMapper.selectById(user.getRoleId()));

		return user;
	}

	/**
	 * 根据邮箱获取用户信息
	 * @param email 邮箱
	 * @return SysUser 实体对象
	 */
	@Override
	public SysUser getUserInfoByEmail(String email) {

		SysUser user = this.getOne(
				new LambdaQueryWrapper<SysUser>()
						.eq(SysUser::getEmail, email)
		);

		user.setRoles(sysRoleMapper.selectById(user.getRoleId()));

		return user;
	}

	/**
	 * 根据用户id更新用户信息
	 * @param user 用户
	 * @return 更新标记
	 */
	@Override
	public Boolean updateUserInfoByUserId(SysUser user) {

		return this.updateById(user);
	}

	/**
	 * 保存用户信息
	 * @param user 用户实体
	 * @return 保存标记
	 */
	@Override
	public Boolean saveUser(SysUser user) {

		return this.save(user);
	}

	/**
	 * 删除用户信息
	 * @param userId 用户id
	 * @return 删除标记
	 */
	@Override
	public Boolean delUserInfoBuUserId(String userId) {

		return this.removeById(userId);
	}
}
