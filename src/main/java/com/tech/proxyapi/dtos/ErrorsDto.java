package com.tech.proxyapi.dtos;

public class ErrorsDto {

	private String errorMessage;

	public ErrorsDto(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
