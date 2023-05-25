package indi.yuluo.common.utils;

import org.junit.jupiter.api.Test;

/**
 * @author: yuluo
 * @date: 2023/5/15 16:32
 * @description: some desc
 */

//@SpringBootTest
public class StringUtilsTest {

	private static final String testStr = "test-String";

	@Test
	void testIsNotEmpty() {

		boolean notEmpty = StringUtils.isNotEmpty(testStr);

		assert notEmpty;
	}

}
