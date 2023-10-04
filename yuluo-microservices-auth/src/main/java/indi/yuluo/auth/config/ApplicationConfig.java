package indi.yuluo.auth.config;

import java.util.Optional;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import indi.yuluo.auth.mapper.SysCurrentUserMapper;
import indi.yuluo.auth.module.CurrentUser;
import indi.yuluo.common.domain.system.SysRole;
import indi.yuluo.common.result.Result;
import indi.yuluo.module.system.api.RemoteSysRoleService;
import jakarta.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author: yuluo
 * @date: 2023/5/19 17:46
 * @description: Application Configuration
 */

@Configuration
public class ApplicationConfig {

    @Resource
    private SysCurrentUserMapper userMapper;

    @Resource
    private RemoteSysRoleService remoteSysRoleService;

    @Bean
    public UserDetailsService userDetailsService() {

        return username -> {

            CurrentUser user = userMapper.selectOne(new LambdaQueryWrapper<CurrentUser>()
                    .eq(CurrentUser::getUserName, username));
            Result<SysRole> result = remoteSysRoleService.getRoleByRoleId(user.getRoleId() + "");
            user.setRoles(result.getData());
            Optional<CurrentUser> currentUser = Optional.of(user);

            return currentUser.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        };
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider authProvider= new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
