package com.aram.connect.dataObjects;

import java.util.List;

public class SmsResponse {
	
	private List<Errors> errors;
	
	private List<Errors> warnings;
	
	private Message message;
	
	private List<Messages> messages;
	
	private long balance;
	
	private long batch_id;
	
	private long cost;
	
	private int num_messages;
	
	private String receipt_url;
	
	private String custom;
	
	private String status;

	public List<Errors> getErrors() {
		return errors;
	}

	public void setErrors(List<Errors> errors) {
		this.errors = errors;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Errors> getWarnings() {
		return warnings;
	}

	public void setWarnings(List<Errors> warnings) {
		this.warnings = warnings;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public List<Messages> getMessages() {
		return messages;
	}

	public void setMessages(List<Messages> messages) {
		this.messages = messages;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	public long getBatch_id() {
		return batch_id;
	}

	public void setBatch_id(long batch_id) {
		this.batch_id = batch_id;
	}

	public long getCost() {
		return cost;
	}

	public void setCost(long cost) {
		this.cost = cost;
	}

	public int getNum_messages() {
		return num_messages;
	}

	public void setNum_messages(int num_messages) {
		this.num_messages = num_messages;
	}

	public String getReceipt_url() {
		return receipt_url;
	}

	public void setReceipt_url(String receipt_url) {
		this.receipt_url = receipt_url;
	}

	public String getCustom() {
		return custom;
	}

	public void setCustom(String custom) {
		this.custom = custom;
	}

	@Override
	public String toString() {
		return "SmsResponse [errors=" + errors + ", warnings=" + warnings + ", message=" + message + ", messages="
				+ messages + ", balance=" + balance + ", batch_id=" + batch_id + ", cost=" + cost + ", num_messages="
				+ num_messages + ", receipt_url=" + receipt_url + ", custom=" + custom + ", status=" + status + "]";
	}




	
	
	

}
