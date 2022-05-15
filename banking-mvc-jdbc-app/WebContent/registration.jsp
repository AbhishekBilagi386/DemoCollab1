<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
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
<title>Registration Page</title>
</head>
<body>
	<div class="container" style="background-color: whitesmoke";>
		<div class="row">
			<div>
				<form action="register" method="post">
					<div class="form-group">
						<h1>Registration</h1>
					</div>
					<br>

					<div class="form-group">
						<label for="name">Name:</label> <input type="text" name="name"
							id="name" class="form-control">
						<div id="error"></div>
					</div>
					<br>

					<div class="form-group">
						<label for="balance">Balance:</label> <input type="number"
							name="balance" id="balance" class="form-control">
						<div id="error2"></div>
					</div>
					<br>

					<div class="form-group">
						<label for="password">Password:</label> <input type="password"
							class="form-control" name="password" id="password">
							<div id="error3"></div>
					</div>
					<br>

					<div class="form-group">
						<button type="submit" class="btn btn-success" id="submit">Create
							Account</button>
					</div>
					<br>

				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="registration.js"></script>
</body>
</html>