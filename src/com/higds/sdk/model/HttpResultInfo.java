package com.higds.sdk.model;

import java.util.Date;

public class HttpResultInfo {
	private int code=-1;
	
	private int logId=0;
	
	private Object message="";

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getLogId() {
		return logId;
	}

	public void setLogId(int logId) {
		this.logId = logId;
	}

	public Object getMessage() {
		return message;
	}

	public void setMessage(Object message) {
		this.message = message;
	}
	
	
}
