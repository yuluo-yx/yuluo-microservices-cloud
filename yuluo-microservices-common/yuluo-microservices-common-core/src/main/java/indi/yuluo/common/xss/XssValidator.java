package indi.yuluo.common.xss;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import indi.yuluo.common.utils.StringUtils;

/**
 * @author: yuluo
 * @date: 2023/5/17 19:16
 * @description: some desc
 */

public class XssValidator implements ConstraintValidator<Xss, String> {
	private static final String HTML_PATTERN = "<(\\S*?)[^>]*>.*?|<.*? />";

	@Override
	public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
		if (StringUtils.isBlank(value)) {
			return true;
		}
		return !containsHtml(value);
	}

	public static boolean containsHtml(String value) {
		Pattern pattern = Pattern.compile(HTML_PATTERN);
		Matcher matcher = pattern.matcher(value);
		return matcher.matches();
	}
}
