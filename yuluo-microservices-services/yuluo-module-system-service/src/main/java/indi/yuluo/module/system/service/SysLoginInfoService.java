package indi.yuluo.module.system.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import indi.yuluo.common.domain.system.SysLoginInfo;

/**
 * @author: yuluo
 * @date: 2023/5/22 15:18
 * @description: some desc
 */

public interface SysLoginInfoService extends IService<SysLoginInfo> {

	/**
	 * 删除系统访问记录
	 * @return 删除标记
	 */
	Boolean delLoginInfo();

	/**
	 * 获取系统访问日志
	 * @return List
	 */
	Map<String, List<SysLoginInfo>> getLoginInfo();

	/**
	 * 查询指定用户登录记录
	 * @return List<SysLoginInfo>
	 */
	Map<String, List<SysLoginInfo>> getLoginInfoByUsername(String username);

	/**
	 * 保存系统访问信息
	 * @param sysLoginInfo 信息实体
	 */
	void saveLoginInfo(SysLoginInfo sysLoginInfo);

	/**
	 * 导出系统访问记录列表
	 */
	List<SysLoginInfo> selectLogininforList();
}
