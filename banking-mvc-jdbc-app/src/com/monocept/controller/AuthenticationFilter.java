package com.monocept.controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.monocept.dto.AccountDTO;


@WebFilter(urlPatterns = { "/dashboard", "/passbook", "/transaction" }, servletNames = { "DashboardController",
	    "PassbookController", "TransactionController" })
public class AuthenticationFilter implements Filter {

   
    public AuthenticationFilter() {

    }

	
	public void destroy() {

	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("Inside Authetication Filter");
	    HttpServletRequest httpRequest = (HttpServletRequest) request;
	    HttpServletResponse httpResponse = (HttpServletResponse) response;
	    HttpSession session = httpRequest.getSession();

	    AccountDTO accountDto = (AccountDTO) session.getAttribute("user");
	    if (accountDto == null) {
	      httpResponse.sendRedirect("login");
	      return;
	    }
	    chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
