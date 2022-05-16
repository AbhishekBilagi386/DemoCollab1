package com.monocept.model;

public class Transaction {
	private String acccountName;
	private double amount;
	private TransactionType type;
	private String dateTime;

	public Transaction(String acccountName, double amount, TransactionType type, String dateTime) {
		this.acccountName = acccountName;
		this.amount = amount;
		this.type = type;
		this.dateTime = dateTime;
	}

	public String getAcccountName() {
		return acccountName;
	}

	public double getAmount() {
		return amount;
	}

	public String getType() {
		return type.toString();
	}

	public String getDateTime() {
		return dateTime;
	}

}
