package indi.yuluo.gateway.config.properties;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * @author: yuluo
 * @date: 2023/5/18 8:38
 * @description: 放行白名单配置
 */

@Data
@NoArgsConstructor
@Configuration
@RefreshScope
@ConfigurationProperties(prefix = "security.ignore")
public class IgnoreWhiteProperties {

	/**
	 * 放行白名单配置，网关不校验此处的白名单
	 */
	private List<String> whites = new ArrayList<>();

}
