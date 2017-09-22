package edu.nju.model;

public enum RESCODE {

	SUCCESS(0, "成功"), 
	WRONG_PARAM(1, "参数错误"),
	NOT_FOUND(2, "无该条记录"),
	PSW_ERROR(3, "密码错误"), 
	UPDATE_ERROR(4, "更新数据错误"), 
	CREATE_ERROR(5, "存储数据错误"), 
	DATE_FORMAT_ERROR(6, "日期格式错误"),
	DELETE_ERROR(7, "删除错误"), 
	DUPLICATED_ERROR(8,"重复数据"),
	FILE_ERROR(9, "上传文件错误"),
	STATE_ERROR(10, "对象状态错误"),
	JSON_ERROR(11, "Json解析出错"),
	UNKNOW_ERROR(12, "系统发生位置错误"),
	TECHNICIAN_INACTIVATED(14, "技师未激活"),
	TECHNICIAN_ERROR(13,"技师不存在");
	;

	// 定义私有变量
	private int nCode;

	private String nMsg;

	// 构造函数，枚举类型只能为私有
	private RESCODE(int _nCode, String _nMsg) {

		this.nCode = _nCode;
		this.nMsg = _nMsg;
	}

	public String getMsg() {

		return nMsg;
	}

	@Override
	public String toString() {

		return String.valueOf(this.nCode);

	}
}
