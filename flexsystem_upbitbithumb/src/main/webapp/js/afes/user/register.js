/**
 * 
 */

function register() {
	var id = $('#id').val();
	var name = $('#name').val();
	var email = $('#email').val();
	var phone = $('#phone').val();
	var address = $('#address').val();
	var password = $('#password').val();
	
	$.ajax({
		type: "GET",
		url: 'saveUser.do',
		data: {
			id : id,
			name : name,
			email : email,
			phone : phone,
			address : address,
			password : password
		},
		success: function(data) {
			alert('success');
		},
		error: function () {
			alert('not really');
		}
	});
}

function checkUserExist(id) {
	$.ajax({
		type: "GET",
		url: 'checkUser.do',
		dataType:"json",
		data: {
			id : id
		},
		success: function(data) {
			if (data == null || data['existYn'] == 'Y') {
				alert('Already Exist, write again.');
			}
		},
		error: function () {
			alert('not really');
		}
	});
}

$( document ).ready(function() {
	$('#id').focusout(function() {
		checkUserExist($('#id').val());
   });
});