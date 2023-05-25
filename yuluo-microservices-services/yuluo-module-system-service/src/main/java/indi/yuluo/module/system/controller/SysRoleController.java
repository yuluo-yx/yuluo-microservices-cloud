package indi.yuluo.module.system.controller;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import indi.yuluo.common.constant.SecurityConstants;
import indi.yuluo.common.domain.system.SysRole;
import indi.yuluo.common.log.annotation.Log;
import indi.yuluo.common.log.enums.BusinessType;
import indi.yuluo.common.result.Result;
import indi.yuluo.module.system.service.SysRoleService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: yuluo
 * @date: 2023/5/22 15:10
 * @description: 系统用户权限控制器
 */

@Slf4j
@RestController
@RequestMapping("/role")
public class SysRoleController {

	@Resource
	private SysRoleService roleService;

	/**
	 * 获取所有的权限信息
	 * @return Map<String, List<SysRole>>
	 */
	@GetMapping("/getRole")
	public Result<Map<String, List<SysRole>>> getRole() {

		return Result.success(roleService.getRole());
	}

	/**
	 * 根据roleId获取权限信息
	 * @return SysRole
	 */
	@GetMapping("/getRoleByRoleId/{roleId}")
	public Result<SysRole> getRoleByRoleId(@PathVariable("roleId") String roelId) {

		return Result.success(roleService.getRoleByRoleId(roelId));
	}


	/**
	 * 删除 role
	 * @param roleId roleId
	 * @return 删除标记
	 */
	@DeleteMapping("/delRole/{roleId}")
	@Log(title = "删除权限信息", businessType = BusinessType.DELETE, isSaveResponseData = false)
	public Result<Boolean> delRole(@PathVariable("roleId") String roleId) {

		return Result.success(roleService.delRoleByRoleId(roleId));
	}

	/**
	 * 更新权限
	 * @param sysRole 权限对象
	 * @return 更细标记
	 */
	@PutMapping("/updateRole")
	@Log(title = "更新权限", businessType = BusinessType.UPDATE, isSaveResponseData = false)
	public Result<Boolean> updateRole(@RequestBody SysRole sysRole)  {

		return Result.success(roleService.updateRoleByRoleId(sysRole));
	}

	/**
	 * 测试更新权限并写入操作日志
	 * @return 更细标记
	 */
	@PutMapping("/updateTest")
	@Log(title = "更新权限测试操作", businessType = BusinessType.UPDATE)
	public Result<Boolean> updateRoleTest()  {

		// 创建测试权限对象
		SysRole sysRole = new SysRole();
		sysRole.setRoleId(123457L);
		sysRole.setRoleKey("system::test");
		sysRole.setRoleName("test");
		sysRole.setRoleSort(2);
		sysRole.setStatus(0 + "");
		sysRole.setDelFlag(0 + "");

		roleService.save(sysRole);

		// 更新测试权限对象
		sysRole.setRoleName("test-update");
		sysRole.setRoleKey("system::test-update");

		return Result.success(roleService.updateById(sysRole));
	}

}
