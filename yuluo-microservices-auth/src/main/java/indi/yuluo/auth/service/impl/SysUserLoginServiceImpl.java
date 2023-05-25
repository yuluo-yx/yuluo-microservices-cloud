package indi.yuluo.auth.service.impl;

import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import indi.yuluo.auth.mapper.SysCurrentUserMapper;
import indi.yuluo.auth.module.CurrentUser;
import indi.yuluo.auth.service.SysUserLoginService;
import indi.yuluo.common.constant.CacheConstants;
import indi.yuluo.common.constant.Constants;
import indi.yuluo.common.domain.system.SysLoginInfo;
import indi.yuluo.common.domain.system.SysRole;
import indi.yuluo.common.domain.system.SysUser;
import indi.yuluo.common.exception.ServiceException;
import indi.yuluo.common.redis.service.RedisService;
import indi.yuluo.common.result.Result;
import indi.yuluo.common.ttl.util.CurrentUserUtils;
import indi.yuluo.common.utils.CacheUtils;
import indi.yuluo.common.utils.JwtUtils;
import indi.yuluo.common.utils.ServletUtils;
import indi.yuluo.common.utils.StringUtils;
import indi.yuluo.common.utils.ip.AddressUtils;
import indi.yuluo.common.utils.ip.IpUtils;
import indi.yuluo.module.system.api.RemoteSysLoginInfoService;
import indi.yuluo.module.system.api.RemoteSysRoleService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author: yuluo
 * @date: 2023/5/19 21:32
 * @description: 用户登录服务实现类
 */

@Slf4j
@Service
public class SysUserLoginServiceImpl extends ServiceImpl<SysCurrentUserMapper, CurrentUser> implements SysUserLoginService {

	@Resource
	private AuthenticationManager authenticationManager;

	@Resource
	private PasswordEncoder passwordEncoder;

	@Resource
	private RemoteSysRoleService remoteSysroleService;

	@Resource
	private RemoteSysLoginInfoService remoteSysLoginInfoService;

	@Resource
	private RedisService redisService;

	private final static long expireTime = CacheConstants.EXPIRATION;


	/**
	 * 登录方法
	 * @param username 用户名
	 * @param password 密码
	 * @return CurrentUser
	 */
	@Override
	public CurrentUser login(String username, String password) {

		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						username,
						password
				)
		);

		LambdaQueryWrapper<CurrentUser> wrapper = new LambdaQueryWrapper<>();
		wrapper.eq(CurrentUser::getUserName, username);
		CurrentUser currentUser = this.getOne(wrapper);

		Result<SysRole> result = remoteSysroleService.getRoleByRoleId(currentUser.getRoleId() + "");
		currentUser.setRoles(result.getData());

		// 先判断用户是否被禁用
		if (Objects.equals(1 + "", currentUser.getStatus())) {
			throw new ServiceException("用户已经被禁用，请与管理员联系");
		}

		// 根据 用户id 将 loginUser 缓存到redis中
		redisService.setCacheObject(CacheUtils.getUserCacheKey(username), currentUser, expireTime, TimeUnit.MINUTES);

		// 记录系统访问日志
		this.recordLogininfor(username, Constants.LOGIN_SUCCESS, "登录成功");

		return currentUser;
	}

	/**
	 * 登出方法
	 * 从redis中删除用户缓存信息并且记录用户退出日志
	 */
	@Override
	public void logout(HttpServletRequest request) {

		String token = CurrentUserUtils.getToken(request);

		if (StringUtils.isNotEmpty(token)) {

			String username = JwtUtils.extractUsername(token);

			// 删除用户缓存记录
			redisService.deleteObject(CacheUtils.getUserCacheKey(username));

			this.recordLogininfor(username, Constants.LOGOUT, "退出成功");

		}
	}

	/**
	 * 注册方法
	 * @param username 用户名
	 * @param password 密码
	 */
	@Override
	public void register(String username, String password) {

		var user = SysUser.builder()
				.userName(username)
				.password(passwordEncoder.encode(password))
				.build();

		this.save((CurrentUser) user);

		// 记录系统访问日志
		this.recordLogininfor(username, Constants.REGISTER, "注册成功");

	}

	/**
	 * 记录登录信息
	 *
	 * @param username 用户名
	 * @param status 状态
	 * @param message 消息内容
	 */
	private void recordLogininfor(String username, String status, String message) {

		HttpServletRequest request = ServletUtils.getRequest();
		assert request != null;
		final UserAgent userAgent = UserAgentUtil.parse(request.getHeader("User-Agent"));

		// 获取客户端操作系统
		String os = userAgent.getOs().getName();
		// 获取客户端浏览器
		String browser = userAgent.getBrowser().getName();

		// 获取 ip
		String ip = IpUtils.getIpAddr();

		// 获取ip地址
		String address = AddressUtils.getRealAddressByIP(ip);

		SysLoginInfo logininfor = new SysLoginInfo();
		logininfor.setUserName(username);
		logininfor.setIpaddr(ip);
		logininfor.setMsg(message);
		logininfor.setBrowser(browser);
		logininfor.setOs(os);
		logininfor.setLoginLocation(address);
		logininfor.setLoginTime(new Date());

		// 日志状态
		if (StringUtils.equalsAny(status, Constants.LOGIN_SUCCESS, Constants.LOGOUT, Constants.REGISTER)) {
			logininfor.setStatus(Constants.LOGIN_SUCCESS_STATUS);
		}
		else if (Constants.LOGIN_FAIL.equals(status)) {
			logininfor.setStatus(Constants.LOGIN_FAIL_STATUS);
		}

		remoteSysLoginInfoService.saveLoginInfo(logininfor);

	}

}
