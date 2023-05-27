package indi.yuluo.module.system.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import indi.yuluo.common.domain.system.SysOperLog;

/**
 * @author: yuluo
 * @date: 2023/5/22 15:20
 * @description: SysOperLogService
 */

public interface SysOperLogService extends IService<SysOperLog> {

	/**
	 * 获取系统日志
	 * @return Map<String, List<SysOperLog>>
	 */
	Map<String, List<SysOperLog>> getOperLog();

	/**
	 * 删除系统日志
	 * @return 删除标记
	 */
	Boolean delOperlLog();

	/**
	 * 添加系统操作日志
	 * @param sysOperLog 日志实体
	 * @return 插入标记
	 */
	Boolean saveOperLog(SysOperLog sysOperLog);

	/**
	 * 导出操作日志记录列表
	 */
	List<SysOperLog> selectOperLogList();
}
