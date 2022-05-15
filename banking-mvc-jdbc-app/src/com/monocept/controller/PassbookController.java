package com.monocept.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.monocept.model.Transaction;
import com.monocept.service.TransactionService;


/**
 * Servlet implementation class PassbookController
 */
@WebServlet("/passbook")
public class PassbookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TransactionService service;
	private List<Transaction> transactions;   

    public PassbookController() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service = TransactionService.getInstance();
		transactions = service.getTransactions();

		request.setAttribute("data", transactions);

		RequestDispatcher view = request.getRequestDispatcher("passbook.jsp");
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}

}
