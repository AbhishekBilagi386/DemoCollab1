package com.monocept.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.monocept.model.Account;
import com.monocept.model.Transaction;
import com.monocept.model.TransactionType;
import com.monocept.service.AccountService;

@WebServlet("/register")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AccountService service;

	public RegisterController() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("registration.jsp");
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		double balance = Double.valueOf(request.getParameter("balance"));
		String password = request.getParameter("password");
		String pattern = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String currentTime = sdf.format(new Date());

		Account account = new Account(name, balance, password);
		Transaction transaction = new Transaction(name, balance, TransactionType.DEPOSIT, currentTime);
		service = AccountService.getInstance();
		service.registerAccount(account, transaction);
		response.sendRedirect("dashboard");
	}

}
