package indi.yuluo.module.system;

import indi.yuluo.common.annotation.EnableYuluoFeignClients;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: yuluo
 * @date: 2023/5/15 16:28
 * @description: some desc
 */

@SpringBootApplication
@EnableYuluoFeignClients
public class YuluoMicroServiceModuleSystemApplication {

	public static void main(String[] args) {

		SpringApplication.run(YuluoMicroServiceModuleSystemApplication.class, args);
		System.out.println(" 系统服务启动…… ");

	}

}
