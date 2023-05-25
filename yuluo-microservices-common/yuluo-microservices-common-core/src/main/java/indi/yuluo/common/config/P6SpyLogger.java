package indi.yuluo.common.config;

import java.time.LocalDateTime;

import com.p6spy.engine.spy.appender.MessageFormattingStrategy;

/**
 * @author: yuluo
 * @date: 2023/5/25 11:05
 * @description: 用来自己配置自定义日志文件打印，替换如下配置即可
 * logMessageFormat=com.baomidou.mybatisplus.extension.p6spy.P6SpyLogger
 */

public class P6SpyLogger implements MessageFormattingStrategy {

	/**
	 * 日志格式
	 * @param connectionId 连接id
	 * @param now 当前时间
	 * @param elapsed 耗时多久
	 * @param category 类别
	 * @param prepared mybatis带占位符的sql
	 * @param sql 占位符换成参数的sql
	 * @param url sql连接的 url
	 * @return 自定义格式日志
	 */
	@Override
	public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {

		return !"".equals(sql.trim()) ? "P6SpyLogger " + LocalDateTime.now() + " | elapsed " + elapsed + "ms | category " + category + " | connection " + connectionId + " | url " + url + " | sql \n" + sql : "";
	}
}
