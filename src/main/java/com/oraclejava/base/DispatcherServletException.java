package com.oraclejava.base;

import jakarta.servlet.ServletException;

public class DispatcherServletException extends ServletException {
	public DispatcherServletException() {
		
	}
	public DispatcherServletException(String message) {
		super(message);
	}
}
