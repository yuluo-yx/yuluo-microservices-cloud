package indi.yuluo.common.utils;

import indi.yuluo.common.constant.CacheConstants;

/**
 * @author: yuluo
 * @date: 2023/5/20 14:45
 * @description: some desc
 */

public class CacheUtils {

	private CacheUtils() {
	}

	/**
	 * 登录账户密码错误次数缓存键名
	 *
	 * @param username 用户名
	 * @return 缓存键key
	 */
	public static String getLoginErrorCacheKey(String username) {
		return CacheConstants.PWD_ERR_CNT_KEY + username;
	}

	/**
	 * 登录账户密码错误次数缓存键名
	 *
	 * @param captchaText 缓存文本名
	 * @return 缓存键key
	 */
	public static String getCaptchaCacheKey(String captchaText) {
		return CacheConstants.PWD_ERR_CNT_KEY + captchaText;
	}

	/**
	 * 登录用户缓存键名
	 *
	 * @param username 用户名
	 * @return 缓存键key
	 */
	public static String getUserCacheKey(String username) {
		return CacheConstants.LOGIN_TOKEN_KEY + username;
	}

}
