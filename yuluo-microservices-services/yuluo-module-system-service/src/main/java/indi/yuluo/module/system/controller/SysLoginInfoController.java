package indi.yuluo.module.system.controller;

import java.util.List;
import java.util.Map;

import indi.yuluo.common.domain.system.SysLoginInfo;
import indi.yuluo.common.excel.util.ExcelUtils;
import indi.yuluo.common.log.annotation.Log;
import indi.yuluo.common.log.enums.BusinessType;
import indi.yuluo.common.result.Result;
import indi.yuluo.module.system.service.SysLoginInfoService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: yuluo
 * @date: 2023/5/22 15:13
 * @description: SysLoginInfoController 系统访问记录控制器
 */

@Slf4j
@RestController
@RequestMapping("/loginInfo")
public class SysLoginInfoController {

	@Resource
	private SysLoginInfoService loginInfoService;

	/**
	 * 获取系统访问日志
	 * @return List
	 */
	@GetMapping("/getLoginInfo")
	public Result<Map<String, List<SysLoginInfo>>> getLoginInfo() {

		return Result.success(loginInfoService.getLoginInfo());
	}

	/**
	 * 删除系统访问记录
	 * @return 删除标记
	 */
	@DeleteMapping("/delLoginInfo")
	@Log(title = "删除系统访问记录", businessType = BusinessType.DELETE, isSaveResponseData = false, isSaveRequestData = false)
	public Result<Boolean> delLoginInfo() {

		return Result.success(loginInfoService.delLoginInfo());
	}

	/**
	 * 查询指定用户登录记录
	 * @return List<SysLoginInfo>
	 */
	@GetMapping("/getLoginInfoByUsername/{username}")
	public Result<Map<String, List<SysLoginInfo>>> getLoginInfoByUserName(@PathVariable("username") String username) {

		return Result.success(loginInfoService.getLoginInfoByUsername(username));
	}

	/**
	 * 保存系统访问信息
	 * @param sysLoginInfo 信息实体
	 */
	@PostMapping("/saveLoginInfo")
	public void saveLoginInfo(@RequestBody SysLoginInfo sysLoginInfo) {

		loginInfoService.saveLoginInfo(sysLoginInfo);
	}


	/**
	 * 导出系统访问记录列表
	 */
	@Log(title = "导出系统访问日志", businessType = BusinessType.EXPORT)
	@PostMapping("/export")
	public void export(HttpServletResponse response) {

		List<SysLoginInfo> list = loginInfoService.selectLogininforList();

		ExcelUtils.exportExcel(list, "系统访问日志", SysLoginInfo.class, response);
	}

}
