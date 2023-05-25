package indi.yuluo.common.domain.system;

import java.io.Serial;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import indi.yuluo.common.Enum.SensitiveStrategy;
import indi.yuluo.common.annotation.Sensitive;
import indi.yuluo.common.domain.base.BaseEntity;
import indi.yuluo.common.xss.Xss;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author: yuluo
 * @date: 2023/5/17 19:37
 * @description: 用户对象 sys_user
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_user")
@EqualsAndHashCode(callSuper = true)
public class SysUser extends BaseEntity {

	@Serial
	private static final long serialVersionUID = 1L;

	/** 用户ID */
	@TableId(value = "user_id")
	private Long userId;

	/** 用户账号 */
	@NotBlank(message = "用户名不能为空")
	@Min(value = 3, message = "用户名长度不能小于3")
	@Max(value = 20, message = "用户名长度不能大于20")
	private String userName;

	/** 用户昵称 */
	@NotBlank(message = "用户昵称不能为空")
	@Min(value = 3, message = "用户昵称长度不能小于3")
	@Max(value = 20, message = "用户昵称长度不能大于20")
	private String nickName;

	/** 用户邮箱 */
	@Email
	@Size(max = 50, message = "邮箱长度不能超过50个字符")
	@Sensitive(strategy = SensitiveStrategy.EMAIL)
	private String email;

	/** 手机号码 */
	@Sensitive(strategy = SensitiveStrategy.PHONE)
	private String phonenumber;

	/** 用户性别 */
	private String sex;

	/** 用户头像 */
	private String avatar;

	/** 密码 */
	@NotBlank(message = "用户密码不能为空")
	private String password;

	/** 帐号状态（0正常 1停用） */
	private String status;

	/** 删除标志（0代表存在 1代表删除） */
	@TableLogic
	private String delFlag;

	/** 最后登录IP */
	private String loginIp;

	/** 最后登录时间 */
	private Date loginDate;

	/** 角色对象 */
	@TableField(exist = false)
	private SysRole roles;

	/** 角色组 */
	@TableField(exist = false)
	private Long[] roleIds;

	/** 角色ID */
	private Long roleId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public boolean isAdmin() {
		return isAdmin(this.userId);
	}

	public static boolean isAdmin(Long userId) {
		return userId != null && 1L == userId;
	}

	@Xss(message = "用户昵称不能包含脚本字符")
	@Size(min = 0, max = 30, message = "用户昵称长度不能超过30个字符")
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Xss(message = "用户账号不能包含脚本字符")
	@NotBlank(message = "用户账号不能为空")
	@Size(min = 0, max = 30, message = "用户账号长度不能超过30个字符")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Email(message = "邮箱格式不正确")
	@Size(min = 0, max = 50, message = "邮箱长度不能超过50个字符")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Size(min = 0, max = 11, message = "手机号码长度不能超过11个字符")
	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	@JsonIgnore
	@JsonProperty
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public SysRole getRoles() {
		return roles;
	}

	public void setRoles(SysRole roles) {
		this.roles = roles;
	}

	public Long[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Long[] roleIds) {
		this.roleIds = roleIds;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("userId", getUserId())
				.append("userName", getUserName())
				.append("nickName", getNickName())
				.append("email", getEmail())
				.append("phonenumber", getPhonenumber())
				.append("sex", getSex())
				.append("avatar", getAvatar())
				.append("password", getPassword())
				.append("status", getStatus())
				.append("delFlag", getDelFlag())
				.append("loginIp", getLoginIp())
				.append("loginDate", getLoginDate())
				.append("createTime", getCreateTime())
				.append("updateTime", getUpdateTime())
				.toString();
	}
}

