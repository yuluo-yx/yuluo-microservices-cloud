package indi.yuluo.common.result;

import java.io.Serializable;
import java.util.Objects;

import indi.yuluo.common.Enum.IResult;
import indi.yuluo.common.Enum.impl.ResultEnum;
import lombok.Data;

/**
 * @author: yuluo
 * @date: 2023/5/15 18:21
 * @description: 统一返回数据结果
 */

@Data
public class Result<T> implements Serializable {

	// 编码
	private Integer code;

	// 错误信息
	private String message;

	// 数据
	private T data;

	public Result() {
	}

	public Result(Integer code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Result<?> result = (Result<?>) o;
		return Objects.equals(code, result.code) && Objects.equals(message, result.message) && Objects.equals(data, result.data);
	}

	@Override
	public int hashCode() {
		return Objects.hash(code, message, data);
	}

	@Override
	public String toString() {
		return "Result{" +
				"code=" + code +
				", message='" + message + '\'' +
				", data=" + data +
				'}';
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	/**
	 * 只返回成功代码和描述，不返回其他数据
	 */
	public static <T> Result<T> success() {
		return new Result<>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), null);
	}

	/**
	 * 返回成功代码和描述，以及自定义数据
	 */
	public static <T> Result<T> success(T data) {
		return new Result<>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), data);
	}

	/**
	 * 返回成功代码和自定义的String类型的信息描述和数据
	 */
	public static <T> Result<T> success(String message, T data) {
		return new Result<>(ResultEnum.SUCCESS.getCode(), message, data);
	}

	/**
	 * 返回失败的代码和描述信息，不带数据
	 */
	public static Result<?> failed() {
		return new Result<>(ResultEnum.COMMON_FAILED.getCode(), ResultEnum.COMMON_FAILED.getMessage(), null);
	}


	/**
	 * 返回失败的代码和自定义的String描述信息，不带数据
	 */
	public static <T> Result<T> failed(String message) {
		return new Result<>(ResultEnum.COMMON_FAILED.getCode(), message, null);
	}

	public static <T> Result<T> failed(int code ,String message) {
		return new Result<>(code, message, null);
	}

	/**
	 * 用于参数校验时，添加异常信息中的msg
	 *
	 * @param errorResult 继承IResult的枚举类
	 * @param <T>         泛型
	 * @return Result对象
	 */
	public static <T> Result<T> failed(IResult errorResult, String message) {
		return new Result<>(errorResult.getCode(), message, null);
	}

	/**
	 * 自定义选择结果枚举类中的信息
	 *
	 * @param errorResult 返回接口的具体实现类，通常是枚举
	 * @param <T>
	 * @return
	 */
	public static <T> Result<T> failed(IResult errorResult) {
		return new Result<>(errorResult.getCode(), errorResult.getMessage(), null);
	}

	/**
	 * 自定义返回信息
	 *
	 * @param code    代码
	 * @param message 信息
	 * @param data    数据
	 * @param <T>     泛型
	 * @return 返回中
	 */
	public static <T> Result<T> instance(Integer code, String message, T data) {
		Result<T> result = new Result<>();

		result.setCode(code);
		result.setMessage(message);
		result.setData(data);

		return result;
	}

}

