package com.monocept.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.ProcessBuilder.Redirect;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.monocept.dto.AccountDTO;
import com.monocept.model.Account;
import com.monocept.model.Transaction;
import com.monocept.model.TransactionType;
import com.monocept.service.TransactionService;

@WebServlet("/transaction")
public class TransactionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TransactionService transactionService;

	public TransactionController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("transactions.jsp");
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		AccountDTO user = (AccountDTO) session.getAttribute("user");

		double balBeforeTransaction = user.getBalance();
		String name = user.getName();
		double amount = Double.valueOf(request.getParameter("amount"));
		String type = request.getParameter("radio");
		double finalBalance;
		TransactionType transactionType;
		if (type.equalsIgnoreCase("deposit")) {
			transactionType = TransactionType.DEPOSIT;
			finalBalance = balBeforeTransaction + amount;
		} else {
			transactionType = TransactionType.WITHDRAW;
			if ((balBeforeTransaction - amount) < 500) {
				response.sendRedirect("error");
				return;
			}
			finalBalance = balBeforeTransaction - amount;
		}

		String pattern = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String currentTime = sdf.format(new Date());

		Transaction transaction = new Transaction(name, amount, transactionType, currentTime);
		transactionService = TransactionService.getInstance();
		transactionService.doTransaction(transaction, balBeforeTransaction);
		user.setBalance(finalBalance);
		response.sendRedirect("dashboard");
	}

}
