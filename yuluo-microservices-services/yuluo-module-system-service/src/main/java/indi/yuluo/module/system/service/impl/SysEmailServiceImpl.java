package indi.yuluo.module.system.service.impl;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import cn.hutool.core.util.RandomUtil;
import indi.yuluo.common.constant.CacheConstants;
import indi.yuluo.common.constant.Constants;
import indi.yuluo.common.exception.CaptchaException;
import indi.yuluo.common.exception.EmailException;
import indi.yuluo.common.mail.config.properties.MailProperties;
import indi.yuluo.common.mail.utils.MailUtils;
import indi.yuluo.common.redis.service.RedisService;
import indi.yuluo.module.system.service.SysEmailService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import org.springframework.stereotype.Service;

/**
 * @author: yuluo
 * @date: 2023/5/24 10:56
 * @description: SysEmailServiceImpl
 */

@Slf4j
@Service
public class SysEmailServiceImpl implements SysEmailService {

	@Resource
	private MailProperties mailProperties;

	@Resource
	private RedisService redisService;

	@Resource
	private TemplateEngine templateEngine;

	/**
	 * 获取邮箱验证码
	 * @param email 邮箱号码
	 * @return 验证码 code
	 */
	@Override
	public String getCode(String email) {

		if (!mailProperties.getEnabled()) {

			throw new EmailException("当前系统没有开启邮箱功能！");
		}

		String key = CacheConstants.CAPTCHA_CODE_KEY + email;
		String code = RandomUtil.randomNumbers(4);

		try {
			// 文本形式发送短信验证码
			// MailUtils.sendText(email, "登录验证码", "您本次验证码为：" + code + "，有效性为" + Constants.CAPTCHA_EXPIRATION + "分钟，请尽快填写。");

			// HTML形式发送邮件验证码
			Context context = new Context();
			//设置模板中的变量
			context.setVariable("verifyCode", code);
			// 第一个参数为模板的名称
			String process = templateEngine.process("email.html", context); //这里不用写全路径
			MailUtils.sendHtml(email, "Yuluo Microservice 邮箱登录验证码", process);

		} catch (Exception e) {

			log.error("验证码短信发送异常 => {}", e.getMessage());
			throw new EmailException(e.getMessage());
		}

		// 缓存邮箱验证码
		redisService.setCacheObject(key, code, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);

		return code;
	}

	/**
	 * 校验邮箱验证码
	 * @param email 邮箱
	 * @param code 验证码
	 * @return 校验标记
	 */
	@Override
	public Boolean check(String email, String code) {

		String key = CacheConstants.CAPTCHA_CODE_KEY + email;
		String redisCode = redisService.getCacheObject(key);

		System.out.println("redisCode:" + redisCode + " code:" + code);

		if (!Objects.equals(code, redisCode)) {

			throw new CaptchaException("邮箱验证码校验失败！");
		}

		redisService.deleteObject(key);

		return true;
	}
}
