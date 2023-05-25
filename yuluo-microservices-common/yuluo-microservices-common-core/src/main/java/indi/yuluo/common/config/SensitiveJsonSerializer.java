package indi.yuluo.common.config;


import java.io.IOException;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import indi.yuluo.common.Enum.SensitiveStrategy;
import indi.yuluo.common.annotation.Sensitive;

/**
 * @author: yuluo
 * @date: 2023/3/27 17:49
 * @description:  序列化注解自定义实现
 * JsonSerializer<String>：指定String 类型，serialize()方法用于将修改后的数据载入
 */

public class SensitiveJsonSerializer extends JsonSerializer<String> implements ContextualSerializer {

	private SensitiveStrategy strategy;

	@Override
	public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		gen.writeString(strategy.desensitizer().apply(value));
	}

	/**
	 * 获取属性上的注解属性
	 */
	@Override
	public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) throws JsonMappingException {

		Sensitive annotation = property.getAnnotation(Sensitive.class);
		if (Objects.nonNull(annotation) && Objects.equals(String.class, property.getType().getRawClass())) {

			this.strategy = annotation.strategy();
			return this;
		}
		return prov.findValueSerializer(property.getType(), property);

	}
}
