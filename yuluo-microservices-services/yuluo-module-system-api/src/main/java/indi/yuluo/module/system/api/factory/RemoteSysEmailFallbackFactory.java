package indi.yuluo.module.system.api.factory;

import indi.yuluo.common.result.Result;
import indi.yuluo.module.system.api.RemoteSysEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author: yuluo
 * @date: 2023/5/24 13:12
 * @description: some desc
 */

@Component
public class RemoteSysEmailFallbackFactory implements FallbackFactory<RemoteSysEmailService> {

	private static final Logger log = LoggerFactory.getLogger(RemoteSysLoginInfoFallbackFactory.class);

	@Override
	public RemoteSysEmailService create(Throwable throwable) {

		log.error("系统邮箱服务调用失败:{}", throwable.getMessage());

		return new RemoteSysEmailService() {

			@Override
			public Result<String> getCode(String email) {

				return Result.failed("获取邮箱验证码信息失败：" + throwable.getMessage());
			}

			@Override
			public Result<Boolean> checkEmailCode(String email, String code) {

				return Result.failed("校验邮箱验证码失败：" + throwable.getMessage());
			}
		};
	}
}


