package com.avatar.config;

@SuppressWarnings("serial")
public class BadRequestException extends RuntimeException{

	public BadRequestException() {
		
	}
	public BadRequestException(String message) {
		super(message);
	}
	
}
