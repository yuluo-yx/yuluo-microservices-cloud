package indi.yuluo.module.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import indi.yuluo.common.domain.system.SysLoginInfo;
import indi.yuluo.common.utils.StringUtils;
import indi.yuluo.module.system.mapper.SysLoginInfoMapper;
import indi.yuluo.module.system.service.SysLoginInfoService;

import org.springframework.stereotype.Service;

/**
 * @author: yuluo
 * @date: 2023/5/22 15:19
 * @description: SysLoginInfoServiceImpl
 */

@Service
public class SysLoginInfoServiceImpl extends ServiceImpl<SysLoginInfoMapper, SysLoginInfo> implements SysLoginInfoService {

	/**
	 * 删除系统访问记录
	 * @return 删除标记
	 */
	@Override
	public Boolean delLoginInfo() {

		return this.remove(
				new LambdaQueryWrapper<SysLoginInfo>()
						.isNotNull(SysLoginInfo::getInfoId)
		);
	}

	/**
	 * 获取系统访问日志
	 * @return List
	 */
	@Override
	public Map<String, List<SysLoginInfo>> getLoginInfo() {

		HashMap<String, List<SysLoginInfo>> map = new HashMap<>();

		map.put("loginInfoList", this.list(
				new LambdaQueryWrapper<SysLoginInfo>()
						.isNotNull(SysLoginInfo::getInfoId)));

		return map;
	}

	/**
	 * 查询指定用户登录记录
	 * @return List<SysLoginInfo>
	 */
	@Override
	public Map<String, List<SysLoginInfo>> getLoginInfoByUsername(String username) {

		HashMap<String, List<SysLoginInfo>> map = new HashMap<>();

		LambdaQueryWrapper<SysLoginInfo> sysLoginInfoLambdaQueryWrapper = new LambdaQueryWrapper<>();
		sysLoginInfoLambdaQueryWrapper.eq(SysLoginInfo::getUserName, username);
		map.put("loginInfoList", this.list(sysLoginInfoLambdaQueryWrapper));

		return map;
	}

	/**
	 * 保存系统访问信息
	 * @param sysLoginInfo 信息实体
	 */
	@Override
	public void saveLoginInfo(SysLoginInfo sysLoginInfo) {

		this.save(sysLoginInfo);
	}

	/**
	 * 查询系统登录日志集合
	 *
	 * @return 登录记录集合
	 */
	@Override
	public List<SysLoginInfo> selectLogininforList() {

		return baseMapper.selectList(new LambdaQueryWrapper<SysLoginInfo>()
				.orderByDesc(SysLoginInfo::getInfoId));
	}
}
