package indi.yuluo.common.utils.ip;

import java.io.File;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import indi.yuluo.common.exception.ServiceException;
import indi.yuluo.common.utils.file.FileUtils;
import lombok.extern.slf4j.Slf4j;

import org.lionsoul.ip2region.xdb.Searcher;

/**
 * @author: yuluo
 * @date: 2023/5/23 21:30
 * @description: 根据ip地址定位工具类，离线方式
 * 参考地址：<a href="https://gitee.com/lionsoul/ip2region/tree/master/binding/java">集成 ip2region 实现离线IP地址定位库</a>
 *
 */

@Slf4j
public class RegionUtils {

	private static final Searcher SEARCHER;

	static {
		String fileName = "/ip2region.xdb";
		File existFile = FileUtils.file(FileUtil.getTmpDir() + File.separator + fileName);
		if (!FileUtils.exist(existFile)) {
			ClassPathResource fileStream = new ClassPathResource(fileName);
			if (ObjectUtil.isEmpty(fileStream.getStream())) {
				throw new ServiceException("RegionUtils初始化失败，原因：IP地址库数据不存在！");
			}
			FileUtils.writeFromStream(fileStream.getStream(), existFile);
		}

		String dbPath = existFile.getPath();

		// 1、从 dbPath 加载整个 xdb 到内存。
		byte[] cBuff;
		try {
			cBuff = Searcher.loadContentFromFile(dbPath);
		} catch (Exception e) {
			throw new ServiceException("RegionUtils初始化失败，原因：从ip2region.xdb文件加载内容失败！" + e.getMessage());
		}
		// 2、使用上述的 cBuff 创建一个完全基于内存的查询对象。
		try {
			SEARCHER = Searcher.newWithBuffer(cBuff);
		} catch (Exception e) {
			throw new ServiceException("RegionUtils初始化失败，原因：" + e.getMessage());
		}
	}

	/**
	 * 根据IP地址离线获取城市
	 */
	public static String getCityInfo(String ip) {
		try {
			ip = ip.trim();
			// 3、执行查询
			String region = SEARCHER.search(ip);
			return region.replace("0|", "").replace("|0", "");
		} catch (Exception e) {
			log.error("IP地址离线获取城市异常 {}", ip);
			return "未知";
		}
	}
}

