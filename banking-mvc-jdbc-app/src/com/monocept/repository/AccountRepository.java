package com.monocept.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.monocept.model.Account;
import com.monocept.model.Transaction;

public class AccountRepository {

	public AccountRepository() {
		System.out.println("Account Repository created");
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

	public Account getAccount(String name, String password) {
		Connection con = getConnection();
		Account account = null;
		try {
			String selectAccount = "SELECT c_name, balance, c_password FROM bank_master WHERE c_name=? AND c_password=?";
			PreparedStatement statement = con.prepareStatement(selectAccount);
			statement.setString(1, name);
			statement.setString(2, password);
			ResultSet rs = statement.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					System.out.println(rs.getString(1) + " " + rs.getDouble(2) + " " + rs.getString(3));
					String cName = rs.getString(1);
					double balance = rs.getDouble(2);
					String cpassword = rs.getString(3);
					account = new Account(cName, balance, cpassword);
				}
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return account;
	}

	public void registerAccount(Account account, Transaction transaction) {
		Connection con = getConnection();
		try {
			String registerAccount = "INSERT INTO bank_master (c_name, balance, c_password)" + "VALUES (" + "\'"
					+ account.getName() + "\'" + ", " + account.getBalance() + ", " + "\'" + account.getPassword()
					+ "\');";
			String transactionTableQuery = "INSERT INTO bank_transaction (c_name, amount, t_type, t_date)" + "VALUES ("
					+ "\'" + transaction.getAcccountName() + "\'" + ", " + transaction.getAmount() + ", " + "\'"
					+ transaction.getType().toString() + "\'" + ", " + "\'" + transaction.getDateTime() + "\'" + ");";
			Statement statement = con.createStatement();
			try {
				int numberOfMasterRowsAffected = statement.executeUpdate(registerAccount);
				int numberOfTransactionRowsAffected = statement.executeUpdate(transactionTableQuery);
				
				if (numberOfTransactionRowsAffected == 1 && numberOfMasterRowsAffected == 1) {
					con.commit();
					System.out.println("\nTransaction Succesful\n");
				} else {
					con.rollback();
					System.out.println("\nTransaction Unsuccesful\n");
				}
				System.out.println("Record Inserted");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
