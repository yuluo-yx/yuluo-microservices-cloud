package indi.yuluo.gateway.Nacos;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: yuluo
 * @date: 2023/5/16 11:37
 * @description: 检查是否能从 Nacos 中获取通用配置
 */

@SpringBootTest
public class NacosConfigTest {

	@Value("${spring.data.redis.password}")
	private String redisPassword;

	@Test
	void testGetServiceInfoFromNacos() {

		System.out.println(redisPassword);
	}

}
