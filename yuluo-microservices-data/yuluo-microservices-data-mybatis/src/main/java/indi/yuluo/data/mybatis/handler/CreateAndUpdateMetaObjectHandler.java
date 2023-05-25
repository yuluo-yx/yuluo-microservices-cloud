package indi.yuluo.data.mybatis.handler;

import java.util.Date;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import indi.yuluo.common.domain.base.BaseEntity;
import indi.yuluo.common.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

/**
 * @author: yuluo
 * @date: 2023/5/18 9:21
 * @description: MP注入处理器
 */

@Slf4j
public class CreateAndUpdateMetaObjectHandler implements MetaObjectHandler {

	@Override
	public void insertFill(MetaObject metaObject) {
		try {
			if (ObjectUtil.isNotNull(metaObject) && metaObject.getOriginalObject() instanceof BaseEntity baseEntity) {
				Date current = ObjectUtil.isNotNull(baseEntity.getCreateTime())
						? baseEntity.getCreateTime() : new Date();
				baseEntity.setCreateTime(current);
				baseEntity.setUpdateTime(current);
			}
		}
		catch (Exception e) {
			throw new ServiceException("自动注入异常 => " + e.getMessage());
		}
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		try {
			if (ObjectUtil.isNotNull(metaObject) && metaObject.getOriginalObject() instanceof BaseEntity) {
				BaseEntity baseEntity = (BaseEntity) metaObject.getOriginalObject();
				Date current = new Date();
				// 更新时间填充(不管为不为空)
				baseEntity.setUpdateTime(current);
			}
		}
		catch (Exception e) {
			throw new ServiceException("自动注入异常 => " + e.getMessage());
		}
	}



}
