$(document).ready(function() {
	$('.download').click(function(event) {
		if (confirm("Are you Sure?")) {
			return true;
		} else {
			event.preventDefault();
			return false;
		}
	});
});