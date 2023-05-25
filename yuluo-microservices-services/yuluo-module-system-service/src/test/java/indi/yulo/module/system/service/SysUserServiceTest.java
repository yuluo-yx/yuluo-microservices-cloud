package indi.yulo.module.system.service;

import indi.yuluo.common.domain.system.SysUser;
import indi.yuluo.module.system.service.SysUserService;
import indi.yuluo.module.system.service.impl.SysUserServiceImpl;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: yuluo
 * @date: 2023/5/22 21:27
 * @description: some desc
 */

@SpringBootTest(classes = SysUserServiceImpl.class)
public class SysUserServiceTest {

	@Resource
	private SysUserService userService;

	@Test
	void testGetUserInfoByUsername() {
		String username = "yuluo";
		SysUser userInfoByUsername = userService.getUserInfoByUsername(username);
		System.out.println(userInfoByUsername);
	}

}
