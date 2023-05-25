package indi.yuluo.common.Enum;

import java.util.function.Function;

/**
 * @author: yuluo
 * @date: 2023/3/27 17:48
 * @description: 脱敏策略，枚举类，针对不同的数据定制特定的策略
 */

public enum SensitiveStrategy {

	/**
	 * 用户名
	 */
	USERNAME(s -> s.replaceAll("(\\S)\\S(\\S*)", "$1*$2")),

	/**
	 * 身份证
	 */
	ID_CARD(s -> s.replaceAll("(\\d{4})\\d{10}(\\w{4})", "$1****$2")),

	/**
	 * 手机号
	 */
	PASSWORD(s -> s.replaceAll("(\\S{3})\\S{2}(\\S*)\\S{2}", "$1****$2****")),

	/**
	 * 电话号码脱敏
	 */
	PHONE(s -> s.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2")),

	/**
	 * 地址
	 */
	ADDRESS(s -> s.replaceAll("(\\S{3})\\S{2}(\\S*)\\S{2}", "$1****$2****")),

	/**
	 * 邮箱地址
	 */
	EMAIL(s -> s.replaceAll("(\\S{3})\\S{2}(\\S*)", "$1****$2"));


	private final Function<String, String> desensitizer;

	SensitiveStrategy(Function<String, String> desensitizer) {
		this.desensitizer = desensitizer;
	}

	public Function<String, String> desensitizer() {
		return desensitizer;
	}
}
