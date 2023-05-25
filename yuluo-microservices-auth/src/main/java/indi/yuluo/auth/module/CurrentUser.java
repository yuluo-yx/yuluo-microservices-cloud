package indi.yuluo.auth.module;

import java.util.Collection;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableName;
import indi.yuluo.common.domain.system.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author: yuluo
 * @date: 2023/5/19 21:13
 * @description: Spring Security 的 用户对象信息
 */

@Data
@TableName("sys_user")
@EqualsAndHashCode(callSuper = true)
public class CurrentUser extends SysUser implements UserDetails {

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		// System.out.println("currentUser Auth：" + this.getRoles());

		return List.of(new SimpleGrantedAuthority(this.getRoles().getRoleName()));
	}

	@Override
	public String getUsername() {
		return this.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}



}
