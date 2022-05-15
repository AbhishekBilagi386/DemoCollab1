console.log("hello")
$(document).ready(function() {
	$('#submit').click(function() {
		var user = $('#uname').val();
		var password = $('#password').val();
        $("#error").html("");
         $("#error2").html("");

		if (user == "") {
			$("#error").html("**The Username must be filled.");
			$("#error").css("color", "red");
			return false;
		}
		if (user.length < 3 || user.length > 30) {
			$("#error").html("**The Username must be between 3 and 30 letters.");
			$("#error").css("color", "red");
			return false;
		}
		if (password == "") {
			$("#error2").html("**Password must be filled");
			$("#error2").css("color", "red");
			return false;
		}
		if (!password.match(/^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,16}$/)) {
			$("#error2").html("**password should contain at least one digit, at least one lower case,at least one upper case, atleast one special character,atleast 8 characters");
			$("#error2").css("color", "red");
			return false;
		}
	});
});