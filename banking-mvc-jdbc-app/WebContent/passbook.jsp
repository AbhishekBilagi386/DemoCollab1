<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.monocept.model.Transaction"%>
<%@page import="com.monocept.dto.AccountDTO"%>
<%@page import = "java.util.List.*" %>
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
<title>Passbook Page</title>
</head>
<body>
	<p id="header">
		<span id="user">
  <%
 AccountDTO user = (AccountDTO) session.getAttribute("user");
 out.println(user.getName() + "\n" + user.getBalance());
 %>
		</span>
	</p>
	<h1>PassBook Page</h1>
	<h1>Transaction page</h1>
	<div class="form-group">
		<button type="submit" class="btn btn-success">Download</button>
	</div>
	<table class="table table-striped">
		<tr>
			<th>Name</th>
			<th>Amount</th>
			<th>Type</th>
			<th>Date&Time</th>
		</tr>
		<%
		for (Transaction transaction : transactions) {
			out.println("<tr><td>" + transaction.getAccountName() + "</td><td>" + transaction.getAmount() + "</td><td>"
			+ student.getType() + "</td><td>" + student.getDateTime() + "</td></tr>");
		}
		%>
	</table>
</body>
</html>