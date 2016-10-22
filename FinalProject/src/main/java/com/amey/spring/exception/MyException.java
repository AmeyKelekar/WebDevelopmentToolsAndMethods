package com.amey.spring.exception;

@SuppressWarnings("serial")
public class MyException extends Exception{
	public MyException(String message) {
        super(message);
    }

    public MyException(String message, Throwable cause) {
        super(message, cause);
    }
}
