package indi.yuluo.module.system.controller;

import indi.yuluo.common.result.Result;
import indi.yuluo.module.system.service.SysEmailService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: yuluo
 * @date: 2023/5/24 10:49
 * @description: SysEmailController
 */

@Slf4j
@RestController
@RequestMapping("/email")
public class SysEmailController {

	@Resource
	private SysEmailService emailService;

	/**
	 * 获取邮箱验证码
	 * @param email 邮箱号码
	 * @return 验证码 code
	 */
	@GetMapping("/getEmailCode/{email}")
	public Result<String> getCode(@PathVariable("email") String email) {

		return Result.success(emailService.getCode(email));
	}

	/**
	 * 校验邮箱验证码
	 * @param email 邮箱
	 * @param code 验证码
	 * @return 校验标记
	 */
	@PostMapping("/checkEmailCode/{email}/{code}")
	public Result<Boolean> checkEmailCode(
			@PathVariable("email") String email,
			@PathVariable("code") String code
	) {

		return Result.success(emailService.check(email, code));
	}

}
