package com.jumia.model;

public enum SuccessResponse {
	
	VALID_CUSTOMER_PHONE_NUMBER("Valid", "The customer's phone number is valid");

	private String status;
	private String description;

	private SuccessResponse(String status, String description) {
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