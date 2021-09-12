/**
 * 
 */

function getFormData() {
	var data = {};
	data['userId'] = $('#userId').val();
	data['userType'] = $('#userType').val();
	alert($('#userType').val());
	return data;
}
function getUserList() {
	$.ajax({
		type: "GET",
		url: 'userDatas.do',
		dataType:"json",
		data: {
		},
		success: function(data) {
			console.log(JSON.stringify(data));
			for (var i = 0; i < data.length; i ++) {
				drawUserInfo(data[i], i);
			}
		},
		error: function () {
			alert('not really');
		}
	});
}

function updateUser() {
	var data = getFormData();
	$.ajax({
		type: "POST",
		url: 'userUpdate.do',
		dataType:"json",
		data: data,
		success: function(data) {
			$('#userForm').children().remove();
			getUserList();
			$('#userInfo').modal('toggle');
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
	
	$('#updateData').on('click', function() {
		updateUser();
	});

	getUserList();

});
