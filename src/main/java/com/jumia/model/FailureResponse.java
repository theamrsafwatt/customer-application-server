package com.jumia.model;

public enum FailureResponse {
	
	ERROR("Error", "An error occurred while processing"),
	NOT_FOUND("Not found", "Resource can not be found"),
	INVALID_CUSTOMER_PHONE_NUMBER("Invalid", "The customer's phone number is invalid");

	private String status;
	private String description;

	private FailureResponse(String status, String description) {
		this.status = status;
		this.description = description;
	}

	public String getStatus() {
		return status;
	}
	public String getDescription() {
		return description;
	}
}