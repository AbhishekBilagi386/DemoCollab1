package com.monocept.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.monocept.dto.AccountDTO;
import com.monocept.model.Transaction;
import com.monocept.service.TransactionService;

@WebServlet("/download")
public class DownloadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TransactionService service;
	private List<Transaction> transactions;

	public DownloadController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		AccountDTO account = (AccountDTO)session.getAttribute("user");
		String name = account.getName();
		service = TransactionService.getInstance();
		service.savePassbook(name);
		response.sendRedirect("passbook");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
