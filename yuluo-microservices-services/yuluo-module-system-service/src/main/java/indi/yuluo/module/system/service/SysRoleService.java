package indi.yuluo.module.system.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import indi.yuluo.common.domain.system.SysRole;

import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author: yuluo
 * @date: 2023/5/22 15:21
 * @description: SysRoleService
 */

public interface SysRoleService extends IService<SysRole> {

	/**
	 * 获取所有的权限信息
	 * @return Map<String, List<SysRole>>
	 */
	Map<String, List<SysRole>> getRole();

	/**
	 * 删除 role
	 * @param roleId roleId
	 * @return 删除标记
	 */
	Boolean delRoleByRoleId(String roleId);

	/**
	 * 更新权限
	 * @param sysRole 权限对象
	 * @return 更细标记
	 */
	Boolean updateRoleByRoleId(SysRole sysRole);

	/**
	 * 根据roleId获取权限信息
	 * @return SysRole
	 */
	SysRole getRoleByRoleId(String roelId);
}
