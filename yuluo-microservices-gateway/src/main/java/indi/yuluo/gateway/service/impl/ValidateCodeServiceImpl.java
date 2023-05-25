package indi.yuluo.gateway.service.impl;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import cn.hutool.log.Log;
import indi.yuluo.common.constant.CacheConstants;
import indi.yuluo.common.constant.Constants;
import indi.yuluo.common.exception.CaptchaException;
import indi.yuluo.common.redis.service.RedisService;
import indi.yuluo.common.result.Result;
import indi.yuluo.common.utils.Base64Utils;
import indi.yuluo.common.utils.StringUtils;
import indi.yuluo.common.utils.uuid.IDUtils;
import indi.yuluo.gateway.config.properties.CaptchaProperties;
import indi.yuluo.gateway.service.ValidateCodeService;
import jakarta.annotation.Resource;
import com.google.code.kaptcha.Producer;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FastByteArrayOutputStream;

/**
 * @author: yuluo
 * @date: 2023/5/15 19:27
 * @description: 验证码实现处理
 */

@Slf4j
@Service
public class ValidateCodeServiceImpl implements ValidateCodeService {
	@Resource(name = "captchaProducer")
	private Producer captchaProducer;

	@Resource(name = "captchaProducerMath")
	private Producer captchaProducerMath;

	@Autowired
	private RedisService redisService;

	@Autowired
	private CaptchaProperties captchaProperties;

	/**
	 * 生成验证码
	 */
	@Override
	public Result<Object> createCaptcha() throws IOException, CaptchaException {

		boolean captchaEnabled = captchaProperties.getEnabled();

		if (!captchaEnabled) {
			return Result.success();
		}

		// 保存验证码信息
		String uuid = IDUtils.simpleUUID();
		String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + uuid;

		String capStr, code = null;
		BufferedImage image = null;

		String captchaType = captchaProperties.getType();
		// 生成验证码
		if ("math".equals(captchaType)) {
			String capText = captchaProducerMath.createText();
			capStr = capText.substring(0, capText.lastIndexOf("@"));
			code = capText.substring(capText.lastIndexOf("@") + 1);
			image = captchaProducerMath.createImage(capStr);
		}
		else if ("char".equals(captchaType)) {
			capStr = code = captchaProducer.createText();
			image = captchaProducer.createImage(capStr);
		}

		// 将验证码存储在redis中，3 分钟有效时间
		redisService.setCacheObject(verifyKey, code, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);

		// 转换流信息写出
		FastByteArrayOutputStream os = new FastByteArrayOutputStream();
		try {
			assert image != null;
			ImageIO.write(image, "jpg", os);
		}
		catch (IOException e) {
			log.error(e.getMessage());
			return Result.failed(e.getMessage());
		}

		HashMap<String, String> info = new HashMap<>();

		info.put("uuid", uuid);
		info.put("img", Base64Utils.encode(os.toByteArray()));

		// 返回
		return Result.success(info);
	}

	/**
	 * 校验验证码
	 */
	@Override
	public void checkCaptcha(String code, String uuid) throws CaptchaException {

		if (StringUtils.isEmpty(code)) {
			throw new CaptchaException("验证码不能为空");
		}
		if (StringUtils.isEmpty(uuid)) {
			throw new CaptchaException("验证码已失效");
		}

		String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + uuid;

//		System.out.println("验证码的redis查询 key 为：" + verifyKey);

		String captcha = redisService.getCacheObject(verifyKey);
		// System.out.println("从 redis 中获取验证码参数为：" + captcha);

		// System.out.println("前端传递来的code为：" + code);

		redisService.deleteObject(verifyKey);

		if (!code.equalsIgnoreCase(captcha)) {

			throw new CaptchaException("验证码错误");
		}
	}
}
