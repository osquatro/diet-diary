package com.zsoft.exception;

public class ParseDateException extends RuntimeException {

	private static final long serialVersionUID = -7891074724701791152L;

	public ParseDateException() {
		super();
	}

	public ParseDateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ParseDateException(String message, Throwable cause) {
		super(message, cause);
	}

	public ParseDateException(String message) {
		super(message);
	}

	public ParseDateException(Throwable cause) {
		super(cause);
	}
}
