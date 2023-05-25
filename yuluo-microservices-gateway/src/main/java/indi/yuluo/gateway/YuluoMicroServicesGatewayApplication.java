package indi.yuluo.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author: yuluo
 * @date: 2023/5/15 16:25
 * @description: Yuluo microservices gateway application
 */

@SpringBootApplication
@EnableDiscoveryClient
public class YuluoMicroServicesGatewayApplication {

	public static void main(String[] args) {

		SpringApplication.run(YuluoMicroServicesGatewayApplication.class, args);
		System.out.println(" 网关模块启动成功 …… ");
	}

}
