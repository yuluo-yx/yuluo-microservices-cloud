package indi.yuluo.common.domain.system;

import java.io.Serial;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import indi.yuluo.common.domain.base.BaseEntity;

/**
 * @author: yuluo
 * @date: 2023/5/17 19:14
 * @description: 操作日志记录表 oper_log
 */

@ExcelIgnoreUnannotated
@TableName("sys_oper_log")
public class SysOperLog extends BaseEntity {

	@Serial
	private static final long serialVersionUID = 1L;

	/** 日志主键 */
	@TableId(value = "oper_id")
	@ExcelProperty(value = "日志主键")
	private Long operId;

	/** 操作模块 */
	@ExcelProperty(value = "操作模块")
	private String title;

	/** 业务类型（0其它 1新增 2修改 3删除） */
	@ExcelProperty(value = "业务类型")
	private Integer businessType;

	/** 业务类型数组 */
	@TableField(exist = false)
	private Integer[] businessTypes;

	/** 请求方法 */
	@ExcelProperty(value = "请求方法")
	private String method;

	/** 请求方式 */
	@ExcelProperty(value = "请求方式")
	private String requestMethod;

	/** 操作类别（0其它 1后台用户 2手机端用户） */
	@ExcelProperty(value = "操作类别")
	private Integer operatorType;

	/** 操作人员 */
	@ExcelProperty(value = "操作人员")
	private String operName;

	/** 请求url */
	@ExcelProperty(value = "请求地址")
	private String operUrl;

	/** 操作地址 */
	@ExcelProperty(value = "操作地址")
	private String operIp;

	/** 请求参数 */
	@ExcelProperty(value = "请求参数")
	private String operParam;

	/** 返回参数 */
	@ExcelProperty(value = "返回参数")
	private String jsonResult;

	/** 操作状态（0正常 1异常） */
	@ExcelProperty(value = "状态")
	private Integer status;

	/** 错误消息 */
	@ExcelProperty(value = "错误消息")
	private String errorMsg;

	/**
	 * 操作时间
	 * 注意：EasyExcel不支持 LocalDateTime 类型，需要自己实现Converter接口
	 * */
	@ExcelProperty(value = "操作时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date operTime;

	/** 消耗时间 */
	@ExcelProperty(value = "操作消耗时间")
	private Long costTime;

	/**
	 * 请求参数
	 */
	@TableField(exist = false)
	private Map<String, Object> params = new HashMap<>();

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public Long getOperId() {
		return operId;
	}

	public void setOperId(Long operId) {
		this.operId = operId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getBusinessType() {
		return businessType;
	}

	public void setBusinessType(Integer businessType) {
		this.businessType = businessType;
	}

	public Integer[] getBusinessTypes() {
		return businessTypes;
	}

	public void setBusinessTypes(Integer[] businessTypes) {
		this.businessTypes = businessTypes;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getRequestMethod() {
		return requestMethod;
	}

	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}

	public Integer getOperatorType() {
		return operatorType;
	}

	public void setOperatorType(Integer operatorType) {
		this.operatorType = operatorType;
	}

	public String getOperName() {
		return operName;
	}

	public void setOperName(String operName) {
		this.operName = operName;
	}

	public String getOperUrl() {
		return operUrl;
	}

	public void setOperUrl(String operUrl) {
		this.operUrl = operUrl;
	}

	public String getOperIp() {
		return operIp;
	}

	public void setOperIp(String operIp) {
		this.operIp = operIp;
	}

	public String getOperParam() {
		return operParam;
	}

	public void setOperParam(String operParam) {
		this.operParam = operParam;
	}

	public String getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(String jsonResult) {
		this.jsonResult = jsonResult;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public Date getOperTime() {
		return operTime;
	}

	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}

	public Long getCostTime() {
		return costTime;
	}

	public void setCostTime(Long costTime) {
		this.costTime = costTime;
	}

	@Override
	public String toString() {
		return "系统操作日志：{" +
				"operId=" + operId +
				", title='" + title + '\'' +
				", businessType=" + businessType +
				", businessTypes=" + Arrays.toString(businessTypes) +
				", method='" + method + '\'' +
				", requestMethod='" + requestMethod + '\'' +
				", operatorType=" + operatorType +
				", operName='" + operName + '\'' +
				", operUrl='" + operUrl + '\'' +
				", operIp='" + operIp + '\'' +
				", operParam='" + operParam + '\'' +
				", jsonResult='" + jsonResult + '\'' +
				", status=" + status +
				", errorMsg='" + errorMsg + '\'' +
				", operTime=" + operTime +
				", costTime=" + costTime +
				'}';
	}
}
