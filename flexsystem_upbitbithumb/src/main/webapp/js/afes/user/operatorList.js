/**
 * 
 */

function init() {
	$("#operatorList").jqGrid({
//		datatype: "json",
		colNames:['사용자Id','사용자명', '사용자타입', '관리자Id'],
	   	colModel:[
	   		{name:'userId',  index:'userId', width:100},
	   		{name:'userName',index:'userName', width:100},
	   		{name:'userType',index:'userType', width:100},
	   		{name:'parentId',index:'parentId', width:100}
	   	], 
	   	onSelectRow: function(rowid, status, e) {  			
	   	//	$("#offerDetail").modal();
		},
	   	height : 500,
	   	multiselect: true, 
	    viewrecords: true,
	    sortorder: "desc",
	    loadonce: true,
	    caption: "Offer List"
	});
}

function getAmounts() {
	var forms = $('#managerForm').children();
	var datas = [];
	for (var i = 0; i < forms.length; i ++) {
		var id = $('#managerForm label:eq('+ i + ')').attr('name');
		var amount = $('#managerForm input:eq('+ i + ')').val();
		var data = {};
		data['userId'] = id;
		data['amount'] = amount;
		datas.push(data);
	}
	return datas;
}

function getFormData() {
	var data = {};
	data['userId'] = $('#userId').val();
	data['userType'] = $('#userType').val();
	alert($('#userType').val());
	return data;
}

function getManagerList() {
	$.ajax({
		type: "GET",
		url: 'managerDatas.do',
		dataType:"json",
		data: {
		},
		success: function(datas) {
			console.log(JSON.stringify(datas));
			var html = '';
			for (var i = 0; i < datas.length; i ++) {
				html += ' <option name="' + datas[i]['userId'] + '">' + datas[i]['userName'] + '</option>';
			}
			$('#managers').append(html);
			$('#managers').change(function(){
				//alert( $('#managers option:selected').attr('name') );
				getManagerOperatorList($('#managers option:selected').attr('name') );
			});
			if (datas.length > 0) {
				getManagerOperatorList(datas[0]['userId'] );
			}
		},
		error: function () {
			alert('not really');
		}
	});
}

function getManagerAmount() {

	$.ajax({
		type: "GET",
		url: 'managerDatas.do',
		dataType:"json",
		data: {
		},
		success: function(datas) {
			console.log(JSON.stringify(datas));
			var html = '';
			for (var i = 0; i < datas.length; i ++) {
				html += ' <div class="form-group">' +
				'<label for="name" class="cols-sm-2 control-label"  name="' + datas[i]['userId'] + '">' + datas[i]['userName'] + '</label>' +
				'<div class="cols-sm-10">' +
				'	<div class="input-group">' +
				'		<span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>' +
				'		<input type="text" class="form-control" name="name" id="name" value="'+ datas[i]['amount']+ '" placeholder=""/>' +
				'	</div></div></div>';
			}
			$('#managerForm').append(html);
		},
		error: function () {
			alert('not really');
		}
	});
}

function getManagerOperatorList(parentId) {
	$.ajax({
		type: "GET",
		url: 'managerOperatorDatas.do',
		dataType:"json",
		data: {parentId : parentId
		},
		success: function(datas) {
			$('#userForm').children().remove();
			for (var i = 0; i < datas.length; i ++) {
				drawUserInfo(datas[i], i);
			}
		},
		error: function () {
			alert('not really');
		}
	});
}

function getOperatorList() {
	$.ajax({
		type: "GET",
		url: 'operatorDatas.do',
		dataType:"json",
		data: {
		},
		success: function(datas) {
			$('#operatorList').jqGrid('setGridParam', {data: datas, datatype: 'local'}).trigger('reloadGrid');
		},
		error: function () {
			alert('not really');
		}
	});
}

function updateUser() {
	var parentId = $('#managers option:selected').attr('name');
	var rows = jQuery("#operatorList").jqGrid('getGridParam', 'selarrrow');
	if (rows.length == 0) {
		return;
	}
	var ids = '';
	for (var i = 0; i < rows.length;i ++) {
		var rowData =  $("#operatorList").getRowData( rows[i] );
		ids += (i==0? '' :',') + rowData['userId'];
	}
	var data = {};
	data['parentId'] = parentId;
	data['operators'] = ids;
	$.ajax({
		type: "POST",
		url: 'updateOperatorManager.do',
		dataType:"json",
		data: data,
		success: function(data) {
			$('#userListTable').modal('toggle');
		},
		error: function () {
			alert('Connection Error');
		}
	});
}

function updateManagerAmount() {
	var datas =	getAmounts();
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

function deleteUser(userId) {
	$.ajax({
		type: "POST",
		url: 'marketDelete.do',
		dataType:"json",
		data: {userId : userId
		},
		success: function(data) {
			$('#marketForm').children().remove();
			getMarketList();
		},
		error: function () {
			alert('Connection Error');
		}
	});
}

function drawUserInfo(user, i) {
	var html = '        <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">' +
          '<div class="thumbnail">' +
              '<div class="caption">' +
               ' <div class="col-lg-12">' +
            //   '     <span class="glyphicon glyphicon-credit-card"></span>' +
             //  '     <span class="glyphicon glyphicon-trash pull-right text-primary"></span>' +
               ' </div>' +
               ' <div class="col-lg-12 well well-add-card">' +
               '     <h4 id="userName' + i + '" data="' + user['userName'] + '">' + user['userName'] + '</h4>' +
               ' </div>' +
               ' <div class="col-lg-12">' +
               '     <p id="userId' + i + '"  data="' + user['userId'] + '">ID :' + user['userId'] + '</p>' +
               '     <p class"text-muted" id="phoneNo' + i + '" data="' + user['phoneNo'] + '">전화 : '+ user['phoneNo'] + '</p>' +
               '     <p class"text-muted" id="userType' + i + '" data="' + user['userType'] + '">사용자 : '+ user['userType'] + '</p>' +
               '     <p class"text-muted" id="address' + i + '" data="' + user['address'] + '">주소 : '+ user['address'] + '</p>' +
               '     <p class"text-muted" id="locale' + i + '" data="' + user['locale'] + '">지역 : '+ user['locale'] + '</p>' +
               '     <p class"text-muted" id="email' + i + '" data="' + user['email'] + '">email : '+ user['email'] + '</p>' +
               '     <p class"text-muted" id="amount' + i + '" data="' + user['amount'] + '"> 전체 금액 :'+ user['amount'] + '</p>' +
               '     <p class"text-muted" id="parentId' + i + '" data="' + user['parentId'] + '"> 관리자 :'+ user['parentId'] + '</p>' +
               ' </div>' +
               ' <button id="update_' + user['userId'] + '" data="' + i + '" type="button" class="btn btn-primary btn-xs btn-update btn-add-card">수정</button>' +
               ' <button id="delete_' + user['userId'] + '" data="' + i + '" type="button" class="btn btn-danger btn-xs btn-update btn-add-card">삭제</button>' +
            '</div>' +
         ' </div>' +
       ' </div>';
	$('#userForm').append(html);
	
	$('#update_' + user['userId']).on('click', function () {
		var market = getUserData($(this).attr('data'));
		$('#userInfo').modal();
		updateUserInfo(market);
	});
	
	$('#delete_' + user['userId']).on('click', function () {
		
		var user = getUserData($(this).attr('data'));
		if (confirm(user['userName'] + '를 삭제하시겠습니가?')) {
			deleteUser(user['userId']);
		}
		
	});
}

function updateUserInfo(user) {
	$('#userId').val(user['userId']);
	$("#selectBox").val(user['userType']).attr("selected", "selected");
}

function getUserData(i) {
	var user = {};
	user['userId'] = $('#userId' + i).attr('data');
	user['userName'] = $('#userName' + i).attr('data');
	return user;
}


$( document ).ready(function() {
	initMenus();
	init();
	
	$("#slider" ).slider();
	
	$('#updateData').on('click', function() {
		updateUser();
	});

	$('#updateAmount').on('click', function () {
		updateManagerAmount();
	});
	
	getManagerList();
	
	$('#addButton').on('click', function() {
		$('#userListTable').modal();
		getOperatorList();
	});

	$('#amountButton').on('click', function() {
		$('#managerAmountInfo').modal();
		$('#managerForm').children().remove();
		getManagerAmount();
	})
});
