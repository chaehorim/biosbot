/**
 * 
 */

function addSlider() {
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
}

function getMenuList() {
	$.ajax({
		type: "GET",
		url: 'menuList.do',
		dataType:"json",
		async:false,
		data: {
		},
		success: function(datas) {
			console.log(JSON.stringify(datas));
			var menuHtml = '';
			for (var idx in datas) {
				menuHtml += '        <li><a href="' + datas[idx]['url'] + '">' + datas[idx]['menuName'] + '</a></li>';
			} 
			var html = '<header role="banner" class="navbar navbar-fixed-top navbar-inverse">' +
		      '<div class="container">' +
		      '  <div class="navbar-header">' +
		      '    <button data-toggle="collapse-side" data-target=".side-collapse" data-target-2=".side-collapse-container" type="button" class="navbar-toggle pull-left"><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button>' +
		      '  </div>' +
		      '  <div class="navbar-inverse side-collapse in">' +
		      '    <nav role="navigation" class="navbar-collapse">' +
		      '      <ul class="nav navbar-nav">' +
		      	menuHtml +
		      '      </ul>' +
		      '      <ul class="nav navbar-nav pull-right">' +
		      '        <li><a href="/logout.do">로그아웃</a></li>   '+
		      '      </ul>' +
		      '    </nav>' +
		      '  </div>' +
		      '</div></header>';
			$('#menu').append(html);
		},
		error: function () {
			alert('not really');
		}
	});
}

function timeStampToString(sVal) {
	var d = new Date(sVal);
	var timeStr = d.getHours() + '시' + d.getMinutes() + '분' + d.getSeconds() + '초';
	var today = new Date();
	if (d.getMonth() == today.getMonth() && d.getDate() == today.getDate()) {
		timeStr = '오늘 ' + timeStr;
	} else {
		timeStr = d.getMonth() + 1 + '월 -' + d.getDate() + '일 ' + timeStr;
	}
	return timeStr;
}

function timeStampToTimeString(sVal) {
	var d = new Date(sVal);
	var timeStr = d.getHours() + '시' + d.getMinutes() + '분' + d.getSeconds() + '초';
	return timeStr;
}

function timeStampToLogTimeString(sVal) {
	var d = new Date(sVal);
	var timeStr = d.getDate() + '일 ' + d.getHours() + '시' + d.getMinutes() + '분' + d.getSeconds() + '초';
	return timeStr;
}

function timeStampToDateString(sVal) {
	var d = new Date(sVal);
	return (d.getMonth()+ 1) + '월 ' + d.getDate() + '일 ' + d.getHours() + '시 ' + d.getMinutes() + '분 ' + d.getSeconds() + '초';
}

function floatRoundoff(sVal) {
	if (isNaN(sVal)) {
		return '';
	}
	return sVal.toFixed(2);
}

function changeColor(sVal) {
	var str = '';
	sVal  = sVal.toFixed(4);
	if (sVal > 0){
		str = '<p style="color:green">'+sVal+'</p>';
	}
	else str = '<p style="color:red">'+sVal+'</p>';
	return str;
}

function percentRoundoff(sVal) {
	if (isNaN(sVal)) {
		return '';
	}
	sVal = sVal * 100
	return sVal.toFixed(2) + '%';
}

function successFunc(sVal) {
	if (sVal == 2) {
		return '<div style="color:red"> 실패 </div>';
	}
	else return sVal;
}

function initMenus() {
	getMenuList();
}