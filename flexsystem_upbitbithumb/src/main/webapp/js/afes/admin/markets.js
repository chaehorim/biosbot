/**
 * 
 */

function getFormData() {
	var data = {};
	data['marketId'] = $('#marketId').val();
	data['marketName'] = $('#marketName').val();
	data['currencyCode'] = $('#currencyCode').val();
	data['currencyName'] = $('#currencyName').val();
	data['url'] = $('#url').val();
	data['amount'] = $('#amount').val();
	data['bcCnt'] = $('#bcCnt').val();
	data['fromYn'] = $('#fromYn').val();
	data['toYn'] = $('#toYn').val();
	data['activeYn'] = $('#activeYn').val();
	return data;
}
function getMarketList() {
	$.ajax({
		type: "GET",
		url: 'marketLists.do',
		dataType:"json",
		data: {
		},
		success: function(data) {
			console.log(JSON.stringify(data));
			for (var i = 0; i < data.length; i ++) {
				drawMarket(data[i], i);
			}
		},
		error: function () {
			alert('not really');
		}
	});
}

function saveMarket() {
	var data = getFormData();
	$.ajax({
		type: "POST",
		url: 'marketSave.do',
		dataType:"json",
		data: data,
		success: function(data) {
			$('#marketForm').children().remove();
			getMarketList();
		},
		error: function (e) {
			alert('Connection Error');
		}
	});
}

function updateMarket() {
	var data = getFormData();
	data['publicKey'] = $('#publicKey').val();
	data['privateKey'] = $('#privateKey').val();
	data['address'] = $('#address').val();
	$.ajax({
		type: "POST",
		url: 'marketUpdate.do',
		dataType:"json",
		data: data,
		success: function(data) {
			$('#marketForm').children().remove();
			getMarketList();
			$('#marketInfo').modal('close');
		},
		error: function () {
			alert('Connection Error');
		}
	});
}


function updateKeys() {
	var data = {};
	data['marketName']  = $('#marketName').val();
	data['publicKey'] = $('#publicKey').val();
	data['privateKey'] = $('#privateKey').val();
	data['address'] = $('#address').val();
	data['password'] = $('#transpassword').val();
	$.ajax({
		type: "POST",
		url: 'keyUpdate.do',
		async: false,
		//dataType:"json",
		data: data,
		success: function(data) {
			if (data == false) {
				alert('값이 정확하지 않습니다.');
			} else {
				alert('인증 성공하였습니다.');
			}
			
		},
		error: function (a, b,c) {
			alert('Connection Error');
		}
	});
}

function deleteMarket(marketId) {
	$.ajax({
		type: "POST",
		url: 'marketDelete.do',
		dataType:"json",
		data: {marketId : marketId
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

function setDefaultMarket(marketId, defaultYn) {
	$.ajax({
		type: "POST",
		url: 'marketDefault.do',
		dataType:"json",
		data: {marketId : marketId,
				defaultYn: defaultYn
		},
		success: function(data) {
			//$('#marketForm').children().remove();
			//getMarketList();
		},
		error: function () {
			alert('Connection Error');
		}
	});
}

function drawMarket(market, i) {
	var html = '        <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">' +
          '<div class="thumbnail">' +
              '<div class="caption">' +
               ' <div class="col-lg-12">' +
            //   '     <span class="glyphicon glyphicon-credit-card"></span>' +
             //  '     <span class="glyphicon glyphicon-trash pull-right text-primary"></span>' +
               ' </div>' +
               ' <div class="col-lg-12 ' + (market['activeYn'] == 'Y' ? 'well' : 'bad')+ ' well-add-card">' +
               '     <h4 id="marketId' + i + '" style="display:none" data="' + market['marketId'] + '">' + market['marketId'] + '</h4>' +
               '     <h4 id="marketName' + i + '" data="' + market['marketUserName'] + '">' + market['marketUserName'] + '</h4>' +
               ' </div>' +
               ' <div class="col-lg-12">' +
               '     <p id="currencyCode' + i + '" style="display:none" data="' + market['currencyCode'] + '">통화 :' + market['currencyCode'] + '</p>' +
               '     <p id="currencyName' + i + '"  data="' + market['currencyName'] + '">통화 :' + market['currencyName'] + '</p>' +
               '     <p class"text-muted" id="url' + i + '" data="' + market['url'] + '">URL : '+ market['url'] + '</p>' +
               '     <p class"text-muted" id="amount' + i + '" data="' + market['amount'] + '"> 전체 금액 :'+ market['amount'] + '</p>' +
               '     <p class"text-muted" id="bcCnt' + i + '" data="' + market['bcCnt'] + '"> 비트코인 개수 :'+ market['bcCnt'] + '</p>' +
               '     <p class"text-muted" id="fromYn' + i + '" data="' + market['fromYn'] + '"> from Yn :'+ market['fromYn'] + '</p>' +
               '     <p class"text-muted" id="toYn' + i + '" data="' + market['toYn'] + '"> to Yn :'+ market['toYn'] + '</p>' +
               '     <p class"text-muted" id="activeYn' + i + '" data="' + market['activeYn'] + '"> active Yn :'+ market['activeYn'] + '</p>' +
               ' </div>' +
               ' <button id="update_' + market['marketUserName'] + '" data="' + i + '" type="button" class="btn btn-primary btn-xs btn-update btn-add-card">수정</button>' +
               ' <button id="delete_' + market['marketUserName'] + '" data="' + i + '" type="button" class="btn btn-danger btn-xs btn-update btn-add-card">삭제</button>' +
               ' <button id="default_' + market['marketUserName'] + '" data="' + i + '" type="button" class="btn btn-xs btn-update btn-add-card ' +
               (market['defaultYn'] ? 'btn-danger' : 'btn-primary')
               + '">' + (market['defaultYn'] ? 'default' : 'default설정') + '</button>' +
            '</div>' +
         ' </div>' +
       ' </div>';
	$('#marketForm').append(html); 
	
	$('#update_' + market['marketUserName']).on('click', function () {
		var market = getMarketData($(this).attr('data'));
		$('#marketInfo').modal();
		$('#saveData').hide();
		$('#updateData').show();
		$('#keyInput').show();
		updateMarketInfo(market);
	});
	
	$('#delete_' + market['marketUserName']).on('click', function () {
		
		var market = getMarketData($(this).attr('data'));
		if (confirm(market['marketName'] + '를 삭제하시겠습니가?')) {
			deleteMarket(market['marketUserName']);
		}
		
	});
	
	$('#default_' + market['marketUserName']).on('click', function () {
		
		var market = getMarketData($(this).attr('data'));
		var defaultYn = !$('#default_' + market['marketUserName']).hasClass('default');
		if (confirm(market['marketName'] + '를 기본으로 설정하시겠습니까?')) {
			if (defaultYn) {
				if ($('#marketForm default').length > 1) {
					alert('두개 이상 선택 되지 않습니다.');
				}
				$('#default_' + market['marketUserName']).addClass('default btn-danger').removeClass('btn-primary');
				$('#default_' + market['marketUserName']).text('default');
			} else {
				$('#default_' + market['marketUserName']).removeClass('default btn-danger').addClass('btn-primary');
				$('#default_' + market['marketUserName']).text('default설정');
			} 
				
			setDefaultMarket(market['marketUserName'], defaultYn);
		}
		
	});
}

function updateMarketInfo(market) {
	$('#marketId').val(market['marketId']);
	$('#marketName').val(market['marketName']);
	$('#currencyCode').val(market['currencyCode']);
	$('#currencyName').val(market['currencyName']);
	$('#url').val(market['url']);
	$('#amount').val(market['amount']);
	$('#bcCnt').val(market['bcCnt']);
}

function getMarketData(i) {
	var market = {};
	
	market['marketId'] = $('#marketId' + i).attr('data');
	market['marketName'] = $('#marketName' + i).attr('data');
	market['currencyCode'] = $('#currencyCode' + i).attr('data');
	market['currencyName'] = $('#currencyName' + i).attr('data');
	market['url'] = $('#url' + i).attr('data');
	market['amount'] = $('#amount' + i).attr('data');
	market['bcCnt'] = $('#bcCnt' + i).attr('data');
	market['fromYn'] = $('#fromYn' + i).attr('data');
	market['toYn'] = $('#toYn' + i).attr('data');
	market['activeYn'] = $('#activeYn' + i).attr('data');
	return market;
}
$( document ).ready(function() {
	initMenus();
	$('#addButton').on('click', function () {
		$('#marketInfo').modal();
		$('#saveData').show();
		$('#updateData').hide();
		$('#keyInput').hide();
	});

	$('#saveData').on('click', function() {
		saveMarket();
	});

	$('#updateData').on('click', function() {
		updateMarket();
	});

	$('#keyInput').on('click', function() {
		$('#keysInfo').modal();
	});
	
	$('#updateKeys').on('click', function() {
		updateKeys();
	});
	getMarketList();

});
