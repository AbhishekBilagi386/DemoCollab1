console.log("hello")
$(document).ready(function() {
	$('#submit').click(function() {
		var user = $('#name').val();
		var balance = $('#balance').val();
		var password = $('#password').val();
		$("#error").html("");
		$("#error2").html("");
		 $("#error3").html("");
        

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
		if (balance == "") {
			$("#error2").html("**Balance must be filled");
			$("#error2").css("color", "red");
			return false;
		}
		if (balance < 500) {
			$("#error2").html("**Balance must be greater than or equals to 500.");
			$("#error2").css("color", "red");
			return false;
		}
		if (password == "") {
			$("#error3").html("**Password must be filled");
			$("#error3").css("color", "red");
			return false;
		}
		if (!password.match(/^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,16}$/)) {
			$("#error3").html("**password should contain at least one digit, at least one lower case,at least one upper case, atleast one special character,atleast 8 characters");
			$("#error3").css("color", "red");
			return false;
		}
	});
});