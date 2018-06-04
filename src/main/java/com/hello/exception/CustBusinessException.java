/**
 * 
 */
package com.hello.exception;

public class CustBusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CustBusinessException() {
	}

	public CustBusinessException(String s) {
		super(s);
	}

	public CustBusinessException(String s, Throwable throwable) {
		super(s, throwable);
	}

	public CustBusinessException(Throwable throwable) {
		super(throwable);
	}

}
