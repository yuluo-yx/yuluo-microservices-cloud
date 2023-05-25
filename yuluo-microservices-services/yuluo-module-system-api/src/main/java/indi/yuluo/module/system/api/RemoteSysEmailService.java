package indi.yuluo.module.system.api;

import indi.yuluo.common.constant.ServiceNameConstants;
import indi.yuluo.common.result.Result;
import indi.yuluo.module.system.api.factory.RemoteSysEmailFallbackFactory;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author: yuluo
 * @date: 2023/5/24 13:11
 * @description: RemoteSysEmailService
 */

@FeignClient(contextId = "remoteSysEmailService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteSysEmailFallbackFactory.class)
public interface RemoteSysEmailService {

	/**
	 * 获取邮箱验证码
	 * @param email 邮箱号码
	 * @return 验证码 code
	 */
	@GetMapping("/email/getEmailCode/{email}")
	Result<String> getCode(@PathVariable("email") String email);

	/**
	 * 校验邮箱验证码
	 * @param email 邮箱
	 * @param code 验证码
	 * @return 校验标记
	 */
	@PostMapping("/checkEmailCode/{email}/{code}")
	Result<Boolean> checkEmailCode(
			@PathVariable("email") String email,
			@PathVariable("code") String code
	);
}
