/**
 * 
 */

function init() {
	users = JSON.parse(users);
	var html = '';
	for (var i = 0; i < users.length; i ++) {
		html += ' <div class="form-group">' +
		'<label for="name" class="cols-sm-2 control-label"  name="' + users[i]['userId'] + '">' + users[i]['userName'] + '</label>' +
		'<div class="cols-sm-10">' +
		'	<div class="input-group">' +
		'		<span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>' +
		'		<input type="text" class="form-control" name="name" id="name" value="'+ users[i]['amount']+ '" placeholder=""/>' +
		'	</div></div></div>';
	}
	$('#amountForm').append(html);
}

function getAmounts() {
	var forms = $('#amountForm').children();
	var datas = [];
	for (var i = 0; i < forms.length; i ++) {
		var id = $('#amountForm label:eq('+ i + ')').attr('name');
		var amount = $('#amountForm input:eq('+ i + ')').val();
		var data = {};
		data['userId'] = id;
		data['amount'] = amount;
		datas.push(data);
	}
	return datas;
}
function updateAmount() {
	var datas = getAmounts();
	$.ajax({
		type: "POST",
		url: 'updateManagerAmount.do',
		dataType:"json",
		data: {datas : JSON.stringify(datas)},
		success: function(data) {
			alert('tada');
		},
		error: function () {
			alert('Connection Error');
		}
	});
}


$( document ).ready(function() {
	initMenus();
	init();
});