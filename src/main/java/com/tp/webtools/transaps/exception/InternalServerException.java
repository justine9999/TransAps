package com.tp.webtools.transaps.exception;

import lombok.Getter;

@Getter
public class InternalServerException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	String root_operation;
		
	public InternalServerException(String root_operation) {
		this.root_operation = root_operation;
	}
}
