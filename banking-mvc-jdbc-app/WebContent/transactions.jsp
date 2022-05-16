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
<title>Transaction Page</title>
</head>
<body>
	<%
	AccountDTO user = (AccountDTO) session.getAttribute("user");
	%>
	<h1>
		Transaction Page for:<%=user.getName()%></h1>
	<div class="container">
		<div class="row">
			<div>
				<form action="transaction" method="post"
					onsubmit="return validation();">
					<div class="form-group">
					
						<label for="amount">Amount:</label> <input type="number"
							name="amount" id="amount" class="form-control">
					</div>
					<br>
					<div class="form-group">
						<label for="Deposit">Deposit:</label> <input type="radio"
							name="radio" id="radio1" value="Deposit"> <label for="Withdraw">Withdraw:</label>
						<input type="radio" name="radio" id="radio2" value="Withdraw">
					</div>
					<br>
					<div class="form-group">
						<button type="submit" class="btn btn-success">Update</button>
					</div>
					<br>
				</form>
			</div>
		</div>
	</div>
</body>
</html>