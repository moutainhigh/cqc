package com.cqc.common.enums;

import com.cqc.common.api.IErrorCode;

/**
 * @author ZZT
 */

public enum BaseErrorMsg implements IErrorCode<BaseErrorMsg> {

///////////////////////////////////////系统通用错误信息（开始）///////////////////////////////////////
	/** 成功 */
	SUCCESS("1000", "成功！"),

	/** 已经存在 **/
	EXIST("1001", "已经存在！"),

	/** 社交登录待绑定 */
	BINDING("9999", "社交登录待绑定！"),

	/** 失败 */
	FAILED("1002", "失败！"),

	/** 未知错误 */
	UNKNOW_ERROR("1003", "未知错误！"),

	/** 错误 */
	ERROR("1004", "系统错误！"),

	/** 不支持当前请求方法 */
	REQUEST_METHOD_NOT_SUPPORT("1005", "不支持当前请求方法！"),

	/** 缺少请求参数 */
	MISS_REQUEST_PARAM("1006", "缺少请求参数！"),

	/** 不存在 **/
	NOT_EXIT("1007", "不存在！"),

	/** 帐号或者密码错误 **/
	ACCOUNT_OR_PASSWORD_ERROR("1008", "帐号或者密码错误！"),

	/** 验证码错误 **/
	VILIDATE_CODE_ERROR("1010", "验证码错误！"),

	/** 无权访问 **/
	ACCESS_DENIED("1011", "无权访问！"),

	/** access 过期 **/
	ACCESS_OVERDUE("1017", "access过期！"),

	/** 授权码验证失败 **/
	VERIFY_TIMEOUT("1012", "授权码验证失败！"),

	/** 业务错误 **/
	BUSINESS_ERROR("1013", "业务错误！"),

	/** 请求头token为空 **/
	NOT_AUTHORIZATION_ERROR("1014", "请求头token为空"),

	/** 文件格式不允许 **/
	FILETYPE_NOT_ALLOW("1015", "文件格式不允许"),

	/** 上传文件失败 **/
	UPLOAD_FILE_ERROR("1019", "上传文件失败"),

	/** 无法找到缓存的用户社交账号信息 **/
	SOCIAL_NOT_FOUND("1016", "无法找到缓存的用户社交账号信息"),

    /** 未设置支付密码 **/
    PAY_PWD_NONE("1017", "未设置支付密码"),

    /** 支付密码错误 **/
    PAY_PWD_ERROR("1018", "支付密码错误"),

	/** 文操作频率过快 **/
	VALIDATE_OPTION_TOO_FAST("oauth-1001", "操作频率过快"),

	BALANCE_LESS("1020","余额为0，无法抢单"),

	NOT_REAL("1030", "未实名认证，不允许操作"),
	// 已经开启了自动抢单
	ALREADY_AUTO_BUY("1031", "已经开启了自动抢单"),

	NO_RECEIVE_CODE("1040","未上传收款码"),

	NO_BIND_GOODS_SECRET("1048","未绑定Google验证器"),

	NO_GOOGLE_CODE("1050", "谷歌验证码为空"),

	GOOGLE_CODE_ERROR("1060", "谷歌验证码错误"),
	;

/////////////////////////////////////////////系统通用错误信息（结束）///////////////////////////////////////////////
	
	private String code;
	private String message;

	BaseErrorMsg(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
