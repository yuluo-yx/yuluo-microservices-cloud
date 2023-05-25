package indi.yuluo.module.system.service;

/**
 * @author: yuluo
 * @date: 2023/5/24 10:56
 * @description: SysEmailService
 */

public interface SysEmailService {

	/**
	 * 获取邮箱验证码
	 * @param email 邮箱号码
	 * @return 验证码 code
	 */
	String getCode(String email);

	/**
	 * 校验邮箱验证码
	 * @param email 邮箱
	 * @param code 验证码
	 * @return 校验标记
	 */
	Boolean check(String email, String code);
}
