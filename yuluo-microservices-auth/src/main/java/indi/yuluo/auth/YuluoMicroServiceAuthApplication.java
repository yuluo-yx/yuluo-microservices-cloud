package indi.yuluo.auth;

import indi.yuluo.common.annotation.EnableYuluoFeignClients;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: yuluo
 * @date: 2023/5/15 15:59
 * @description: yuluo microservice auth application
 */

@SpringBootApplication
@EnableYuluoFeignClients
public class YuluoMicroServiceAuthApplication {

	public static void main(String[] args) {

		SpringApplication.run(YuluoMicroServiceAuthApplication.class, args);
		System.out.println(" 认证授权中心启动成功…… ");
	}

}
