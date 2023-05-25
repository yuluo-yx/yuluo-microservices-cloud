package indi.yuluo.gateway.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * @author: yuluo
 * @date: 2023/5/15 19:36
 * @description: 验证码配置
 */

@Configuration
@RefreshScope
@ConfigurationProperties(prefix = "security.captcha")
public class CaptchaProperties {

	/**
	 * 验证码开关
	 */
	private Boolean enabled;

	/**
	 * 验证码类型（math 数组计算 char 字符）
	 */
	private String type;

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}

