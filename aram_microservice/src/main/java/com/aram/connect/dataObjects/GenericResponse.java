package com.aram.connect.dataObjects;

public class GenericResponse<T> {
	
	private T t;
	
	private String message;
	
	private String status;

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
