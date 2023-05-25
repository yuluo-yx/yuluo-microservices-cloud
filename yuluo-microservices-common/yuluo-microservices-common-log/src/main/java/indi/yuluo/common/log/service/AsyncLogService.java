package indi.yuluo.common.log.service;

import indi.yuluo.common.domain.system.SysOperLog;
import indi.yuluo.module.system.api.RemoteSysOperLogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author: yuluo
 * @date: 2023/5/17 18:56
 * @description: 异步调用日志服务
 */

@Service
public class AsyncLogService {

	@Autowired
	private RemoteSysOperLogService remoteSysOperLogService;

	/**
	 * 保存系统日志记录
	 */
	@Async
	public void saveSysLog(SysOperLog sysOperLog) throws Exception {

		// System.out.println("需要保存到数据库中的操作日志信息为：" + sysOperLog.toString());
		System.out.println("#AsyncLogService 操作日志已经记录到数据库中，请在数据库中查看……");

		remoteSysOperLogService.saveOperLog(sysOperLog);
	}

}
