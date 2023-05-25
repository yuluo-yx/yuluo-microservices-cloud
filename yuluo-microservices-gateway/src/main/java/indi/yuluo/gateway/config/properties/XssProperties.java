package indi.yuluo.gateway.config.properties;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * @author: yuluo
 * @date: 2023/5/15 20:07
 * @description: XSS跨站脚本配置
 */

@Configuration
@RefreshScope
@ConfigurationProperties(prefix = "security.xss")
public class XssProperties {

	/**
	 * Xss开关
	 */
	private Boolean enabled;

	/**
	 * 排除路径
	 */
	private List<String> excludeUrls = new ArrayList<>();

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public List<String> getExcludeUrls() {
		return excludeUrls;
	}

	public void setExcludeUrls(List<String> excludeUrls) {
		this.excludeUrls = excludeUrls;
	}
}
