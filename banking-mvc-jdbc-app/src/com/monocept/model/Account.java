package com.monocept.model;

public class Account {
	private String name;
	private double balance;
	private String password;

	public Account(String name, double balance, String password) {
		this.name = name;
		this.balance = balance;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public double getBalance() {
		return balance;
	}

	public String getPassword() {
		return password;
	}

}
