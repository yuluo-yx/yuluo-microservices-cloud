package indi.yuluo.module.system.api;

import java.util.List;
import java.util.Map;

import indi.yuluo.common.constant.ServiceNameConstants;
import indi.yuluo.common.domain.system.SysRole;
import indi.yuluo.common.result.Result;
import indi.yuluo.module.system.api.factory.RemoteSysRoleFallbackFactory;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author: yuluo
 * @date: 2023/5/22 16:42
 * @description: RemoteSysRoleService
 */

@FeignClient(contextId = "remoteSysRoleService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteSysRoleFallbackFactory.class)
public interface RemoteSysRoleService {

	/**
	 * 获取所有的权限信息
	 * @return Map<String, List < SysRole>>
	 */
	@GetMapping("/role/getRole")
	Result<Map<String, List<SysRole>>> getRole();

	/**
	 * 根据roleId获取权限信息
	 * @return SysRole
	 */
	@GetMapping("/role/getRoleByRoleId/{roleId}")
	Result<SysRole> getRoleByRoleId(@PathVariable("roleId") String roelId);

	/**
	 * 删除 role
	 * @param roleId roleId
	 * @return 删除标记
	 */
	@DeleteMapping("/role/delRole/{roleId}")
	Result<Boolean> delRole(@PathVariable("roleId") String roleId);

	/**
	 * 更新权限
	 * @param sysRole 权限对象
	 * @return 更细标记
	 */
	@PutMapping("/role/updateRole")
	Result<Boolean> updateRole(@RequestBody SysRole sysRole);

	/**
	 * 测试更新权限并写入操作日志
	 * @return 更细标记
	 */
	@PutMapping("/role/updateTest")
	Result<Boolean> updateRoleTest();

}
