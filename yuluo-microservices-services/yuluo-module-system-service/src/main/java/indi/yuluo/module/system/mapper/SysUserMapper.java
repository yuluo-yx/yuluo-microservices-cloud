package indi.yuluo.module.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import indi.yuluo.common.domain.system.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: yuluo
 * @date: 2023/5/22 15:09
 * @description: SysUserMapper
 */

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
}
