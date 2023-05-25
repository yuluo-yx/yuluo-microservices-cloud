package indi.yuluo.common.exception;

import java.io.Serial;

/**
 * @author: yuluo
 * @date: 2023/5/15 18:29
 * @description: 验证码错误异常类
 */

public class CaptchaException extends RuntimeException {
	@Serial
	private static final long serialVersionUID = 1L;

	public CaptchaException(String msg) {
		super(msg);
	}
}
