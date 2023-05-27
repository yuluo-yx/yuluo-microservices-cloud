package indi.yuluo.common.domain.system;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author: yuluo
 * @date: 2023/5/22 15:16
 * @description: 系统访问记录表 sys_login_info
 */

@Data
@NoArgsConstructor
@ExcelIgnoreUnannotated
@TableName("sys_login_info")
public class SysLoginInfo implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@ExcelProperty("序号")
	@TableId(value = "info_id")
	private Long infoId;

	/**
	 * 用户账号
	 */
	@ExcelProperty(value = "用户账号")
	private String userName;

	/**
	 * 状态 0成功 1失败
	 */
	@ExcelProperty(value = "状态")
	private String status;

	/**
	 * 地址
	 */
	@ExcelProperty(value = "IP地址")
	private String ipaddr;

	/**
	 * 登录地点
	 */
	@ExcelProperty(value = "登录地点")
	private String loginLocation;

	/**
	 * 浏览器类型
	 */
	@ExcelProperty(value = "浏览器类型")
	private String browser;

	/**
	 * 操作系统
	 */
	@ExcelProperty(value = "操作系统")
	private String os;

	/**
	 * 描述
	 */
	@ExcelProperty(value = "描述")
	private String msg;

	/**
	 * 访问时间
	 */
	@ExcelProperty(value = "访问时间")
	private Date loginTime;

}

