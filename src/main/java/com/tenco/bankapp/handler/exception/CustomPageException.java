package com.tenco.bankapp.handler.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
@Getter
public class CustomPageException extends RuntimeException{
	private HttpStatus httpstatus;
	public CustomPageException(String message, HttpStatus status) {
		super(message);
		this.httpstatus = status;
	}
	
}
