package indi.yuluo.auth.test;

import java.io.PipedReader;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author: yuluo
 * @date: 2023/5/20 15:50
 * @description: some desc
 */

@SpringBootTest
public class PasswordEncryptTest {

	@Resource
	private PasswordEncoder passwordEncoder;


	@Test
	void testPasswordEncrypt() {

		String s = "123456";
		System.out.println(passwordEncoder.encode(s));

	}

}
