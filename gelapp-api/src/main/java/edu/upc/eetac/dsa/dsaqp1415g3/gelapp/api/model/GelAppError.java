package edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.model;

public class GelAppError {
	private int status;
	private String message;
 
	public GelAppError() {
		super();
	}
 
	public GelAppError(int status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
 
	public int getStatus() {
		return status;
	}
 
	public void setStatus(int status) {
		this.status = status;
	}
 
	public String getMessage() {
		return message;
	}
 
	public void setMessage(String message) {
		this.message = message;
	}
}
