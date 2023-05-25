package indi.yuluo.module.system.service.impl;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import indi.yuluo.common.domain.system.SysRole;
import indi.yuluo.module.system.mapper.SysRoleMapper;
import indi.yuluo.module.system.service.SysRoleService;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author: yuluo
 * @date: 2023/5/22 15:21
 * @description: SysRoleServiceImpl
 */

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

	/**
	 * 获取所有的权限信息
	 * @return Map<String, List<SysRole>>
	 */
	@Override
	public Map<String, List<SysRole>> getRole() {
		return null;
	}

	/**
	 * 删除 role
	 * @param roleId roleId
	 * @return 删除标记
	 */
	@Override
	public Boolean delRoleByRoleId(String roleId) {

		return this.removeById(roleId);
	}

	/**
	 * 更新权限
	 * @param sysRole 权限对象
	 * @return 更细标记
	 */
	@Override
	public Boolean updateRoleByRoleId(SysRole sysRole) {

		return this.updateById(sysRole);
	}

	/**
	 * 根据roleId获取权限信息
	 * @return SysRole
	 */
	@Override
	public SysRole getRoleByRoleId(String roelId) {

		return this.getById(roelId);
	}
}
