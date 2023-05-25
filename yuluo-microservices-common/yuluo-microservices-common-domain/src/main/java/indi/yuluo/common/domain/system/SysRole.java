package indi.yuluo.common.domain.system;

import java.io.Serial;
import java.util.Set;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import indi.yuluo.common.domain.base.BaseEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author: yuluo
 * @date: 2023/5/17 19:39
 * @description: 角色表 sys_role
 */

@TableName("sys_role")
public class SysRole extends BaseEntity {

	@Serial
	private static final long serialVersionUID = 1L;

	/** 角色ID */
	@TableId(value = "role_id")
	private Long roleId;

	/** 角色权限 */
	@NotBlank(message = "角色名称不能为空")
	@Size(min = 0, max = 30, message = "角色名称长度不能超过30个字符")
	private String roleName;

	/** 角色权限 */
	@NotBlank(message = "权限字符不能为空")
	@Size(min = 0, max = 100, message = "权限字符长度不能超过100个字符")
	private String roleKey;

	/** 角色排序 */
	@NotNull(message = "显示顺序不能为空")
	private Integer roleSort;

	/** 角色状态（0正常 1停用） */
	private String status;

	/** 删除标志（0代表存在 2代表删除） */
	@TableLogic
	private String delFlag;

	/** 用户是否存在此角色标识 默认不存在 */
	@TableField(exist = false)
	private boolean flag = false;

	/** 角色菜单权限 */
	@TableField(exist = false)
	private Set<String> permissions;

	public SysRole() {}

	public SysRole(Long roleId) {
		this.roleId = roleId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public boolean isAdmin() {
		return isAdmin(this.roleId);
	}

	public static boolean isAdmin(Long roleId) {
		return roleId != null && 1L == roleId;
	}

	@NotBlank(message = "角色名称不能为空")
	@Size(min = 0, max = 30, message = "角色名称长度不能超过30个字符")
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@NotBlank(message = "权限字符不能为空")
	@Size(min = 0, max = 100, message = "权限字符长度不能超过100个字符")
	public String getRoleKey() {
		return roleKey;
	}

	public void setRoleKey(String roleKey) {
		this.roleKey = roleKey;
	}

	@NotNull(message = "显示顺序不能为空")
	public Integer getRoleSort() {
		return roleSort;
	}

	public void setRoleSort(Integer roleSort) {
		this.roleSort = roleSort;
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

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public Set<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<String> permissions) {
		this.permissions = permissions;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("roleId", getRoleId())
				.append("roleName", getRoleName())
				.append("roleKey", getRoleKey())
				.append("roleSort", getRoleSort())
				.append("status", getStatus())
				.append("delFlag", getDelFlag())
				.append("createTime", getCreateTime())
				.append("updateTime", getUpdateTime())
				.toString();
	}
}
