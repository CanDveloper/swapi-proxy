package com.tech.proxyapi.exceptions;

@SuppressWarnings("serial")
public class EmptySearchException extends Exception {

	public EmptySearchException(String errorMessage) {
		super(errorMessage);
	}

}
