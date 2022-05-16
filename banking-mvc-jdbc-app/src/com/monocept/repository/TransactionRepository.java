package com.monocept.repository;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.monocept.model.Transaction;
import com.monocept.model.TransactionType;
import com.opencsv.CSVWriter;

public class TransactionRepository {
	public TransactionRepository() {
		System.out.println("Transaction Repository created");
	}

	public Connection getConnection() {
		Connection con = null;
		String dbDriver = "com.mysql.jdbc.Driver";
		try {
			Class.forName(dbDriver);
			con = DriverManager.getConnection("jdbc:mysql://localhost:4040/jdbc_app", "root", "root");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	public List<Transaction> getTransactions(String name) {
		Connection con = getConnection();
		List<Transaction> transactions = new ArrayList<Transaction>();
		try {
			String selectAccount = "SELECT c_name, amount, t_type, t_date FROM bank_transaction WHERE c_name=? ORDER BY (t_date)";
			PreparedStatement statement = con.prepareStatement(selectAccount);
			statement.setString(1, name);
			ResultSet rs = statement.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					System.out.println(
							rs.getString(1) + " " + rs.getDouble(2) + " " + rs.getString(3) + " " + rs.getString(4));
					String cName = rs.getString(1);
					double amount = rs.getDouble(2);
					String type = rs.getString(3);
					TransactionType transactionType;
					if (type.equals(TransactionType.DEPOSIT.toString())) {
						transactionType = TransactionType.DEPOSIT;
					} else {
						transactionType = TransactionType.WITHDRAW;
					}
					String date = rs.getString(4);
					Transaction transaction = new Transaction(cName, amount, transactionType, date);
					transactions.add(transaction);
				}
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return transactions;
	}

	public void doTransaction(Transaction transaction, double balanceBeforeTransaction) {
		double balanceAfterTransaction = 0;
		if (transaction.getType().equalsIgnoreCase("Deposit")) {
			balanceAfterTransaction = balanceBeforeTransaction + transaction.getAmount();
		} else if (transaction.getType().equalsIgnoreCase("Withdraw")) {
			balanceAfterTransaction = balanceBeforeTransaction - transaction.getAmount();
		}
		Connection con = getConnection();
		try {
			con.setAutoCommit(false);
			Statement statement = con.createStatement();
			String transactionTableQuery = "INSERT INTO bank_transaction (c_name, amount, t_type, t_date)" + "VALUES ("
					+ "\'" + transaction.getAcccountName() + "\'" + ", " + transaction.getAmount() + ", " + "\'"
					+ transaction.getType() + "\'" + ", " + "\'" + transaction.getDateTime() + "\'" + ");";

			String masterTableQuery = "Update bank_master SET balance=" + balanceAfterTransaction + " WHERE c_name="
					+ "\'" + transaction.getAcccountName() + "\'";
			try {
				int numberOfTransactionRowsAffected = statement.executeUpdate(transactionTableQuery);
				int numberOfMasterRowsAffected = statement.executeUpdate(masterTableQuery);

				if (numberOfTransactionRowsAffected == 1 && numberOfMasterRowsAffected == 1) {
					con.commit();
					System.out.println("\nTransaction Succesful\n");
				} else {
					con.rollback();
					System.out.println("\nTransaction Unsuccesful\n");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("Record Inserted and Updated");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void savePassbook(String name) throws IOException {
		Connection con = getConnection();
		try {
			String selectAccount = "SELECT c_name, amount, t_type, t_date FROM bank_transaction WHERE c_name=? ORDER BY (t_date)";
			PreparedStatement statement = con.prepareStatement(selectAccount);
			statement.setString(1, name);

			CSVWriter writer = new CSVWriter(new FileWriter("resources/yourfile.csv"));
			Boolean includeHeaders = true;

			ResultSet rs = statement.executeQuery();

			writer.writeAll(rs, includeHeaders);

			writer.close();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
