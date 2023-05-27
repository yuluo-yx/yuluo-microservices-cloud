package indi.yuluo.module.system.controller;

import java.util.List;
import java.util.Map;

import indi.yuluo.common.domain.system.SysOperLog;
import indi.yuluo.common.excel.util.ExcelUtils;
import indi.yuluo.common.log.annotation.Log;
import indi.yuluo.common.log.enums.BusinessType;
import indi.yuluo.common.result.Result;
import indi.yuluo.module.system.service.SysOperLogService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: yuluo
 * @date: 2023/5/22 15:10
 * @description: 系统日志控制器
 */

@Slf4j
@RestController
@RequestMapping("/operlog")
public class SysOperLogController {

	@Resource
	private SysOperLogService operLogService;

	/**
	 * 获取系统日志
	 * @return Map<String, List < SysOperLog>>
	 */
	@GetMapping("/getOperLog")
	public Result<Map<String, List<SysOperLog>>> getOperLog() {

		return Result.success(operLogService.getOperLog());
	}

	/**
	 * 删除系统日志
	 * @return 删除标记
	 */
	@DeleteMapping("/delOperLog")
	@Log(title = "删除系统日志", businessType = BusinessType.DELETE, isSaveRequestData = false, isSaveResponseData = false)
	public Result<Boolean> delOperLog() {

		return Result.success(operLogService.delOperlLog());
	}

	/**
	 * 添加系统日志
	 * @return 删除标记
	 */
	@PostMapping("/saveLog")
	public Result<Boolean> saveOperLog(@RequestBody SysOperLog sysOperLog) {

		return Result.success(operLogService.saveOperLog(sysOperLog));
	}

	/**
	 * 导出操作日志记录列表
	 */
	@Log(title = "导出系统操作日志", businessType = BusinessType.EXPORT)
	@PostMapping("/export")
	public void export(HttpServletResponse response) {

		List<SysOperLog> list = operLogService.selectOperLogList();
		ExcelUtils.exportExcel(list, "操作日志", SysOperLog.class, response);
	}

}
