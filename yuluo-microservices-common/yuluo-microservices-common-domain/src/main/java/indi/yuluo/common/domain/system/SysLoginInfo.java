package indi.yuluo.common.domain.system;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

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
@TableName("sys_login_info")
public class SysLoginInfo implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId(value = "info_id")
	private Long infoId;

	/**
	 * 用户账号
	 */
	private String userName;

	/**
	 * 状态 0成功 1失败
	 */
	private String status;

	/**
	 * 地址
	 */
	private String ipaddr;

	/**
	 * 登录地点
	 */
	private String loginLocation;

	/**
	 * 浏览器类型
	 */
	private String browser;

	/**
	 * 操作系统
	 */
	private String os;

	/**
	 * 描述
	 */
	private String msg;

	/**
	 * 访问时间
	 */
	private Date loginTime;

}


