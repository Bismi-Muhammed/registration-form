package com.employee.registrationform.exception;

import org.springframework.stereotype.Component;

@Component
public class ServiceLayerException extends RuntimeException {

	/**
	 * Custom exception handling to throw exceptions using different logic in
	 * service layer and return our own error message and code
	 */

	private static final long serialVersionUID = 1L;
	private String errorMessage;
	private String errorCode;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ServiceLayerException(String errorMessage, String errorCode) {
		super();
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
	}

	public ServiceLayerException() {

	}

	public ServiceLayerException(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

}
