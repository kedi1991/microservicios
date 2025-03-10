function updateMultiplication() {
	$.ajax({ url: "http://localhost:8080/multiplications/random" }).then(function(data) {

		// Cleans the form
		$("#attempt-form").find("input[name='result-attempt']").val("");
		$("#attempt-form").find("input[name='user-alias']").val("");
		// Gets a random challenge from API and loads the data in the HTML
		$('.multiplication-a').empty().append(data.argA);
		$('.multiplication-b').empty().append(data.argB);
	});
}

function updateStats(alias) {
	$.ajax({url: "http://localhost:8080/results?alias=" + alias,}).then(
			function(data) {
				$('#stats-body').empty();
				data.forEach(function(row) {
								$('#stats-body').append('<tr><td>' + 
													row.id + 
													'</td>' + 
													'<td>' + 
													row.multiplication.argA  + 
													' x ' + 
													row.multiplication.argB  + 
													'</td>' + 
													'<td>' + 
													row.resultAttempt + 
													'</td>' + 
													'<td>' + 
													(row.correct === true ? 'YES' : 'NO') + 
													'</td></tr>');
						});	
			});
}


$(document).ready(function() {
	updateMultiplication();
	$("#attempt-form").submit(function(event) {
		// Don't submit the form normally
		event.preventDefault();
		// Get some values from elements on the page
		var a = $('.multiplication-a').text();
		var b = $('.multiplication-b').text();
		var $form = $(this),
			attempt = $form.find("input[name='result-attempt']"
			).val(), userAlias = $form.find("input[name='user-alias']")
				.val();

		//Compose the data in the format that the API is expecting
		var data = {
			user: { alias: userAlias }, multiplication:
				{ argA: a, argB: b }, resultAttempt: attempt
		};

		//Send the data using post
		$.ajax({
			url: '/results',
			type: 'POST',
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			success: function(result) {

				if (result.correct) {
					$('.result-message').empty().append("The result is correct! Congratulations!");
					updateStats(result.user.alias);
				} else {
					$('.result-message').empty().append("Oops that's not correct! But keep trying!");
				}
			}
		});
		updateMultiplication();
	});
});
