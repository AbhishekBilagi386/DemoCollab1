package com.monocept.model;

public enum TransactionType {
	DEPOSIT, WITHDRAW;

	public String toString() {
		switch (this) {
		case DEPOSIT:
			return "Deposit";
		case WITHDRAW:
			return "Withdraw";
		default:
			return "other";
		}
	}
}
