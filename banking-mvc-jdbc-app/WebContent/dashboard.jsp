<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.monocept.dto.AccountDTO"%>
<!DOCTYPE html>
<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
	crossorigin="anonymous"></script>
<meta charset="ISO-8859-1">
<title>Dashboard</title>
</head>

<body style="background-color: white;">
	<p id="header">
		<span id="user" align="right"> <%
 	AccountDTO user = (AccountDTO) session.getAttribute("user");
 if (user == null) {
 	out.println("Guest User  <a href=" + "\"login\"" + ">login</a>");
 } else {
 	out.println("<h5>" + user.getName() + "  <a href=" + "\"logout\"" + ">logout</a></h5>");
 	out.println("<h6>Balance: " + user.getBalance() + "</h6>");
 }
 %>
		</span>
	</p>
	<div align="center">
		<h1>
			<u><i>Dashboard</i></u>
		</h1>
		<br> <br> <a href="passbook">
			<button type="button" class="btn btn-warning hell"
				aria-haspopup="true" aria-expanded="false" style="width: 50%">
				<font size="6">View Passbook</font>
			</button>
		</a> <br> <br> <br> <a href="transaction">
			<button type="button" class="btn btn-success" aria-haspopup="true"
				aria-expanded="false" style="width: 50%">
				<font size="6">Do Transactions</font>
			</button>
		</a>
	</div>
</body>
</html>