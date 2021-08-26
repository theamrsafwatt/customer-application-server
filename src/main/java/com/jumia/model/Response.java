package com.jumia.model;

/**
 * @author Amr Elbassiouni
 *
 */
public class Response {

	private String status;
	private String description;
	
	public Response(String status, String description) {
		super();
		this.status = status;
		this.description = description;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}