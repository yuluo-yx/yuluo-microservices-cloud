package indi.yuluo.common.Enum;

import org.junit.jupiter.api.Test;

/**
 * @author: yuluo
 * @date: 2023/5/25 10:37
 * @description: some desc
 */

public class SensitiveStrategyTest {

	@Test
	void testEmailSensitive() {

		String email = "780879337@qq.com";
		String email1 = "yuluo829@aliyun.com";

		System.out.println(email1.replaceAll("(\\S{3})\\S{2}(\\S*)", "$1****$2"));

	}

}
