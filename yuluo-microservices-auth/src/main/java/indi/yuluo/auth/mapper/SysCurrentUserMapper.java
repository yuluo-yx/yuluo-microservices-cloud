package indi.yuluo.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import indi.yuluo.auth.module.CurrentUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: yuluo
 * @date: 2023/5/20 13:09
 * @description: 用户 mapper
 */

@Mapper
public interface SysCurrentUserMapper extends BaseMapper<CurrentUser> {
}
