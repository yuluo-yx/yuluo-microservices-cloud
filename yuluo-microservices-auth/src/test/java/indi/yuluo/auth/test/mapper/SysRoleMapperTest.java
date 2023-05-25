package indi.yuluo.auth.test.mapper;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: yuluo
 * @date: 2023/5/21 12:31
 * @description: some desc
 */

@SpringBootTest
public class SysRoleMapperTest {

	@Resource
	private SysRoleMapper roleMapper;

	@Test
	void testFindRoleByRoleId() {

		System.out.println(roleMapper.selectById("123456"));
	}

}
