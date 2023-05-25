package indi.yuluo.gateway.service;

import java.io.IOException;

import indi.yuluo.common.exception.CaptchaException;
import indi.yuluo.common.result.Result;

/**
 * @author: yuluo
 * @date: 2023/5/15 18:20
 * @description: some desc
 */
public interface ValidateCodeService {

	/**
	 * 生成验证码
	 */
	Result<Object> createCaptcha() throws IOException, CaptchaException;

	/**
	 * 校验验证码
	 */
	void checkCaptcha(String key, String value) throws CaptchaException;

}
