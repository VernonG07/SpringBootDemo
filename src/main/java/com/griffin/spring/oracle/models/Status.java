package com.griffin.spring.oracle.models;

public class Status {

	public enum StatusType {
			SUCCESS,
			ERROR
	}
	
	private StatusType type;
	private String message;
	
	public Status(StatusType type, String message) {
		this.type = type;
		this.message = message;
	}
	
	public StatusType getType() {
		return type;
	}
	public String getMessage() {
		return message;
	}
	
	
}
