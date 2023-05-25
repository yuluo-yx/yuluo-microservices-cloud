package indi.yuluo.visual;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: yuluo
 * @date: 2023/5/24 13:58
 * @description: YuluoMicroServiceVisual
 */

@EnableAdminServer
@SpringBootApplication
public class YuluoMicroServiceVisualApplication {

	public static void main(String[] args) {

		SpringApplication.run(YuluoMicroServiceVisualApplication.class);
		System.out.println("admin 监控启动……");
	}

}
