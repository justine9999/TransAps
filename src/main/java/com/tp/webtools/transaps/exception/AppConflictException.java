package com.tp.webtools.transaps.exception;

import lombok.Getter;

@Getter
public class AppConflictException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	String root_operation;
	
	String app_title;
	
	public AppConflictException(String root_operation, String app_title) {
		this.root_operation = root_operation;
		this.app_title = app_title;
	}
}
