package com.mpf.exception;
/**
 * 针对预期的异常进行抛出
 * @author Silence
 *
 */
public class CustomExecption extends Exception{
	private String code;
	private String message;
	
	public CustomExecption(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	
	public CustomExecption(String message) {
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
