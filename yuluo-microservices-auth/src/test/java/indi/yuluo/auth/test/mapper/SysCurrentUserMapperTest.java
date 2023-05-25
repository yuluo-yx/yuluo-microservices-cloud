package indi.yuluo.auth.test.mapper;

import java.util.Optional;

import indi.yuluo.auth.mapper.SysCurrentUserMapper;
import indi.yuluo.auth.module.CurrentUser;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: yuluo
 * @date: 2023/5/20 18:09
 * @description: some desc
 */

@SpringBootTest
public class SysCurrentUserMapperTest {

	@Resource
	private SysCurrentUserMapper sysCurrentUserMapper;

	@Test
	void testSelectOne() {
		Optional<CurrentUser> yuluo = sysCurrentUserMapper.selectOne("yuluo");
		System.out.println(yuluo);
	}

}
