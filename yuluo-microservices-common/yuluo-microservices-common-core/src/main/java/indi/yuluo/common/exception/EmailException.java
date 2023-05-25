package indi.yuluo.common.exception;

import java.io.Serial;

/**
 * @author: yuluo
 * @date: 2023/5/24 11:13
 * @description: some desc
 */

public class EmailException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 1L;

	public EmailException(String msg) {
		super(msg);
	}
}

