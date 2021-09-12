/**
 * 
 */

function getFormData() {
	var data = {};
	data['userId'] = $('#userId').val();
	data['desc'] = $('#desc').val();
	data['publicKey'] = $('#publicKey').val();
	data['privateKey'] = $('#privateKey').val();
	data['marketId'] = $("#marketId option:selected").val();
	data['currencyCode'] = $("#marketId option:selected").attr('code');
	return data;
}

function updateMarketBox()  {
	$.ajax({
		type: "GET",
		url: 'marketLists.do',
		dataType:"json",
		data: {
		},
		success: function(data) {
			console.log(JSON.stringify(data));
			var html = '';
			for (var i = 0, size = data.length; i < size; i ++) {
				html += '<option code="' + data[i]['currencyCode']+ '">' + data[i]['marketId'] + '</option>';
			}
			$('#marketId').append(html);
		},
		error: function () {
			alert('not really');
		}
	});
}

function getMarketIdList() {
	$.ajax({
		type: "GET",
		url: 'idList.do',
		dataType:"json",
		data: {
		},
		success: function(data) {
			console.log(JSON.stringify(data));
			for (var i = 0; i < data.length; i ++) {
				drawMarketId(data[i], i);
			}
		},
		error: function () {
			alert('not really');
		}
	});
}

function saveUserId() {
	var data = getFormData();
	$.ajax({
		type: "GET",
		url: 'idAdd.do',
		dataType:"json",
		data: data,
		success: function(data) {
			if(data['succesYn']) {
				alert('성공적으로 추가하였습니다.');
			} else {
				alert('인증 실패하였습니다.');
			}
		},
		error: function (e) {
			alert('Connection Error');
		}
	});
}

function updateUserId() {
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
			getMarketIdList();
			$('#marketIdInfo').modal('close');
		},
		error: function () {
			alert('Connection Error');
		}
	});
}

function deleteMarketId(userId) {
	$.ajax({
		type: "POST",
		url: 'idDelete.do',
		dataType:"json",
		data: {userId : userId
		},
		success: function(data) {
			$('#marketForm').children().remove();
			getMarketIdList();
		},
		error: function () {
			alert('Connection Error');
		}
	});
}

function setDefaultMarketId(userId, defaultYn) {
	$.ajax({
		type: "POST",
		url: 'setDefaultId.do',
		dataType:"json",
		data: {userId : userId,
				defaultYn: defaultYn
		},
		success: function(data) {
			//$('#marketForm').children().remove();
			//getMarketIdList();
		},
		error: function () {
			alert('Connection Error');
		}
	});
}

function drawMarketId(marketId, i) {
	var html = '        <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">' +
          '<div class="thumbnail">' +
              '<div class="caption">' +
               ' <div class="col-lg-12">' +
            //   '     <span class="glyphicon glyphicon-credit-card"></span>' +
             //  '     <span class="glyphicon glyphicon-trash pull-right text-primary"></span>' +
               ' </div>' +
               ' <div class="col-lg-12 well well-add-card">' +
               '     <h4 id="userId' + i + '">' +marketId['userId'] + '</h4>' +
               ' </div>' +
               ' <div class="col-lg-12">' +
               '     <p id="desc' + i + '"  data="' + marketId['desc'] + '"> Description :' + marketId['desc'] + '</p>' +
               '     <p id="marketId' + i + '"  data="' + marketId['marketId'] + '"> 거래소 :' + marketId['marketId'] + '</p>' +
               '     <p id="currencyCode' + i + '"  data="' + marketId['currencyCode'] + '"> 국번 :' + marketId['currencyCode'] + '</p>' +
               ' </div>' +
               ' <button id="update_' + marketId['userId'] + '" data="' + i + '" type="button" class="btn btn-primary btn-xs btn-update btn-add-card">수정</button>' +
               ' <button id="delete_' + marketId['userId'] + '" data="' + i + '" type="button" class="btn btn-danger btn-xs btn-update btn-add-card">삭제</button>' +
               ' <button id="default_' + marketId['userId'] + '" data="' + i + '" type="button" class="btn btn-xs btn-update btn-add-card ' +
               (marketId['defaultYn'] ? 'btn-danger default' : 'btn-primary')
               + '">' + (marketId['defaultYn'] ? 'default' : 'default설정') + '</button>' +
            '</div>' +
         ' </div>' +
       ' </div>';
	$('#marketForm').append(html); 
	
	$('#update_' + marketId['userId']).on('click', function () {
		var market = getMarketIdData($(this).attr('data'));
		$('#marketIdInfo').modal();
		$('#saveData').hide();
		$('#updateData').show();
		updateMarketInfo(market);
	});
	
	$('#delete_' + marketId['userId']).on('click', function () {
		
		var marketId = getMarketIdData($(this).attr('data'));
		if (confirm(marketId['userId'] + '를 삭제하시겠습니가?')) {
			deleteMarketId(marketId['userId']);
		}
		
	});
	
	$('#default_' + marketId['userId']).on('click', function () {
		var marketId = getMarketIdData($(this).attr('data'));
		var idDom = $('#default_' + marketId['userId']);
		var defaultYn = !idDom.hasClass('default');
		
			if (defaultYn) {
				if ($('#marketForm .default').length > 1) {
					alert('두개 이상 선택 되지 않습니다.');
					return;
				}
				if ($('#marketForm .default').length == 1) {
					var selectedMarket = getMarketIdData($('#marketForm .default').attr('data'));
					if (selectedMarket['currencyCode'] == marketId['currencyCode']) {
						alert('같은 지역의 거래소를 선택할수 없습니다.');
						return;
					}
				}
				if (confirm(marketId['userId'] + '를 기본으로 설정하시겠습니까?')) {
					idDom.addClass('default btn-danger').removeClass('btn-primary');
					idDom.text('default');
				}
			} else {
				idDom.removeClass('default btn-danger').addClass('btn-primary');
				idDom.text('default설정');
			} 
			setDefaultMarketId(marketId['userId'], defaultYn);
		
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

function getMarketIdData(i) {
	var marketId = {};
	
	marketId['marketId'] = $('#marketId' + i).attr('data');
	marketId['currencyCode'] = $('#currencyCode' + i).attr('data');
	marketId['userId'] = $('#userId' + i).text();
	return marketId;
}
$( document ).ready(function() {
	initMenus();
	$('#addButton').on('click', function () {
		$('#marketIdInfo').modal();
		$('#saveData').show();
		$('#updateData').hide();
	});

	$('#saveData').on('click', function() {
		saveUserId();
	});

	$('#updateData').on('click', function() {
		updateUserId();
	});

	getMarketIdList();
    
	updateMarketBox();
});
