package indi.yuluo.module.system.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import indi.yuluo.common.domain.system.SysLoginInfo;
import indi.yuluo.common.domain.system.SysOperLog;
import indi.yuluo.common.utils.StringUtils;
import indi.yuluo.module.system.mapper.SysOperLogMapper;
import indi.yuluo.module.system.service.SysOperLogService;

import org.springframework.stereotype.Service;

/**
 * @author: yuluo
 * @date: 2023/5/22 15:20
 * @description: some desc
 */

@Service
public class SysOperLogServiceImpl extends ServiceImpl<SysOperLogMapper, SysOperLog> implements SysOperLogService {

	/**
	 * 获取系统日志
	 * @return Map<String, List < SysOperLog>>
	 */
	@Override
	public Map<String, List<SysOperLog>> getOperLog() {

		HashMap<String, List<SysOperLog>> map = new HashMap<>();

		map.put("loginInfoList", this.list(
				new LambdaQueryWrapper<SysOperLog>()
						.isNotNull(SysOperLog::getOperIp)));

		return map;
	}

	/**
	 * 删除系统日志
	 * @return 删除标记
	 */
	@Override
	public Boolean delOperlLog() {

		return this.remove(
				new LambdaQueryWrapper<SysOperLog>()
						.isNotNull(SysOperLog::getOperId)
		);
	}

	/**
	 * 添加系统操作日志
	 * @param sysOperLog 日志实体
	 * @return 插入标记
	 */
	@Override
	public Boolean saveOperLog(SysOperLog sysOperLog) {

		return this.save(sysOperLog);
	}

	/**
	 * 查询系统操作日志集合
	 *
	 * @return 操作日志集合
	 */
	@Override
	public List<SysOperLog> selectOperLogList() {

		return baseMapper.selectList(new LambdaQueryWrapper<SysOperLog>()
				.orderByDesc(SysOperLog::getOperId));
	}
}
