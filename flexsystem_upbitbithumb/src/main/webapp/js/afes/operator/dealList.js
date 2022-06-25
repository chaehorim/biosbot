/**
 * 
 */
var OFFER_TYPE_BUY = 1;
var OFFER_TYPE_SELL = 2;
var OFFER_MAX_RANGE = 0.1;
//var COIN_RANGE = {"btc": {"min" : 0.05, "max" : 2}, "eth" : {"min" : 1, "max" :20}, "etc":{"min" :10 , "max" : 200}, "ltc" :{"min":1, "max" : 20}};
var COIN_RANGE = {"btc": {"min" : 0.001,"norm": 0.2, "max" : 1}, "eth" : {"min" : 0.1, "norm": 2,"max" :10}, "etc":{"min" :1 ,"norm": 20, "max" : 100}, "ltc" :{"min":0.1,"norm": 1, "max" : 20}, "bch" :{"min":0.1,"norm": 1, "max" : 2}};

var run = true;
var popup_exchange_rate = 0;
var MINIMUM_PROFIT = 0.01;
var BID_RANGE = 0.02;
var MAX_PROFIT_RANGE = 3;
var chart;
var positiveYn = false;
var BASE_CURRENCY_INCREASE = 10000;

function initTables() {
	$("#offerFromList").jqGrid({
		colNames:['시간','시작 마켓', '대상 마켓', '매도화페', '금액', '매수예상가격','매도예상가격', '예상이윤'],
	   	colModel:[
	   		{name:'time',  index:'startTime', width:80, formatter: timeStampToTimeString},  // 
	   		{name:'fromMarket',index:'fromLocaleName', width:40},
	   		{name:'toMarket',index:'toLocaleName', width:40},
	   		{name:'coinType',index:'coinType', width:30},
	   		{name:'betAmount',index:'buyAmout', width:60, formatter: floatRoundoff},
	   		{name:'searchBuyPrice',index:'buyBcCnt', width:80},
	   		{name:'searchSellPrice',index:'sellBcCnt', width:80},
	   		{name:'betProfit',index:'profitPercentage', width:50, formatter: changeColor}//, formatter: percentRoundoff
	   	],
	   	height : 200,
//	   	rowNum:60,
//	   	rowList:[10,20,30],
//	   	sortname: 'id',
	    viewrecords: true,
	    sortorder: "desc",
	    loadonce: true,
	    caption: "Order List"
	});
	
	$("#offerToList").jqGrid({
		colNames:['시간','시작 마켓', '대상 마켓', '매도화페', '금액', '매수예상가격','매도예상가격', '예상이윤'],
	   	colModel:[
	   		{name:'time',  index:'startTime', width:80, formatter: timeStampToTimeString},  // 
	   		{name:'fromMarket',index:'fromLocaleName', width:40},
	   		{name:'toMarket',index:'toLocaleName', width:40},
	   		{name:'coinType',index:'coinType', width:30},
	   		{name:'betAmount',index:'buyAmout', width:60, formatter: floatRoundoff},
	   		{name:'searchBuyPrice',index:'buyBcCnt', width:80},
	   		{name:'searchSellPrice',index:'sellBcCnt', width:80},
	   		{name:'betProfit',index:'profitPercentage', width:50, formatter: changeColor}//, formatter: percentRoundoff
	   	],
	   	height : 200,
//	   	rowNum:60,
//	   	rowList:[10,20,30],
//	   	sortname: 'id',
	    viewrecords: true,
	    sortorder: "desc",
	    loadonce: true,
	    caption: "Order List"
	});
	
	$("#dealList").jqGrid({
		colNames:['시간','시작 마켓', '대상 마켓', '매도화페','금액', '배팅이윤', '매수예상가격','매도예상가격', '예상이윤'],
	   	colModel:[
	   		{name:'time',  index:'startTime', width:120, formatter: timeStampToTimeString},  // 
	   		{name:'fromMarket',index:'fromLocaleName', width:40},
	   		{name:'toMarket',index:'toLocaleName', width:40},
	   		{name:'coinType',index:'coinType', width:30},
	   		{name:'betAmount',index:'buyAmout', width:60, formatter: floatRoundoff},
	   		{name:'betProfit',index:'betProfit', width:60, formatter: floatRoundoff},
	   		{name:'buyPrice',index:'buyBcCnt', width:40, formatter: floatRoundoff},
	   		{name:'sellPrice',index:'sellBcCnt', width:80},
	   		{name:'profit',index:'profitPercentage', width:50, formatter: changeColor}//, formatter: percentRoundoff
	   	],
	   	height : 200,
//	   	rowNum:60,
//	   	rowList:[10,20,30],
//	   	sortname: 'id',
	    viewrecords: true,
	    sortorder: "desc",
	    loadonce: true,
	    caption: "Order List"
	});
	
	$("#logList").jqGrid({
		colNames:['시간','로그 내용', '대상 마켓', '코인종류'],
	   	colModel:[
	   		{name:'timeStamp',  index:'timeStamp', width:120, formatter: timeStampToLogTimeString},  // 
	   		{name:'messageTxt',index:'messageTxt', width:240},
	   		{name:'marketId',index:'sellBcCnt', width:80},
	   		{name:'coinType',index:'coinType', width:30}
	   	],
	   	height : 200,
//	   	rowNum:60,
//	   	rowList:[10,20,30],
//	   	sortname: 'id',
	    viewrecords: true,
	    sortorder: "desc",
	    loadonce: true,
	    caption: "Order List"
	});
	
	$("#riskyLogList").jqGrid({
		colNames:['시간','로그 내용', '대상 마켓', '코인종류'],
	   	colModel:[
	   		{name:'timeStamp',  index:'timeStamp', width:120, formatter: timeStampToLogTimeString},  // 
	   		{name:'messageTxt',index:'messageTxt', width:240},
	   		{name:'marketId',index:'sellBcCnt', width:80},
	   		{name:'coinType',index:'coinType', width:30}
	   	],
	   	height : 200,
//	   	rowNum:60,
//	   	rowList:[10,20,30],
//	   	sortname: 'id',
	    viewrecords: true,
	    sortorder: "desc",
	    loadonce: true,
	    caption: "Order List"
	});
}

function initSchedular() {
	getDatas();
}

function getDatas() {
	if (!run) {
		return;
	}
	getUserOfferData();
	setTimeout(getDatas, 3000);
}

function getUserOfferData() {
	$.ajax({
		type: "GET",
		url: 'offerUserData.do',
		dataType:"json",
		data: {
		},
		success: function(data) {
			
			var offerDatas = [];
			var tmpData =  makeFromToTable(data['offerList']);;
			
			$('#offerFromList').jqGrid('setGridParam', {data: tmpData['fromData'], datatype: 'local'}).trigger('reloadGrid');
			$('#offerToList').jqGrid('setGridParam', {data: tmpData['toData'], datatype: 'local'}).trigger('reloadGrid');
			$('#dealList').jqGrid('setGridParam', {data: data['dealList'], datatype: 'local'}).trigger('reloadGrid');
			$('#logList').jqGrid('setGridParam', {data: data['logList'], datatype: 'local'}).trigger('reloadGrid');
			$('#riskyLogList').jqGrid('setGridParam', {data: data['riskyLogList'], datatype: 'local'}).trigger('reloadGrid');
			var html = '';
			$.each(data['coinAssetMap'], function(key, value) {
				var point = 1;
				var wan = 1;
				if (key == 'BTC') {
					point = 5;
				}
				if (key == 'ETH') {
					point = 2;
				}
				if (key == 'KRW') {
					wan = 10000;
				}
				
				var num1 =  value[0] / wan;
				var num2 = value[1] / wan;
				var num3 = value[2] == null ? 0 : value[2]/ wan;
				var num4 = (value[0] + value[1] ) / wan + + num3;
				html +='<label class="col-sm-3 control-label" for="textinput">' + key + ' </label>' +
	            '<label class="col-sm-2 control-label" for="textinput">' + num1.toFixed(point) + '</label>' +
	            '<label class="col-sm-2 control-label" for="textinput">' + num2.toFixed(point) + '</label>' + 
	            '<label class="col-sm-2 control-label" for="textinput">' + num3.toFixed(point) + '</label>' + 
	            '<label class="col-sm-3 control-label" for="textinput">' + num4.toFixed(point) + '</label>';
			});
			$('#amountForm').html(html);
			
			console.log(data['coinAssetMap']);
		},
		error: function () {
			//alert('not really');
		}
	});
}

function makeFromToTable(tmpData) {
	var fromData = [];
	var toData = [];
	$.each(tmpData, function( index, value ) {
	  if (value['fromMarket'] == fromMarket) {
		 fromData.push(value);
	  } else {
		  toData.push(value);
	  }
	});
	return {'fromData': fromData, 'toData' : toData};
}

function calcChartData(tmpData) {
	var val; 
	if (tmpData == null || tmpData.length == 0) {
		return 0;
	} else if (tmpData.length  == 1) {
		val = tmpData[0]['profitPercentage'];
	} else {
		if (tmpData[1]['startTime'] - tmpData[0]['startTime'] > 5000) {
			val = tmpData[0]['profitPercentage'];
		} else {
			if (tmpData[1]['profitPercentage'] > tmpData[0]['profitPercentage'] ) {
				val = tmpData[1]['profitPercentage']
			} else {
				val = tmpData[0]['profitPercentage']
			}
		}
	}
	
	if (val > MAX_PROFIT_RANGE) {
		return MAX_PROFIT_RANGE;
	} else if (val <= 0) {
		return 0;
	} 
	return (val * 100).toFixed(2);
}
function sendUserSetting() {
	var data = JSON.parse(coinOptions);
	var tradeYn = $('#toggle-one').prop('checked');
	console.log(coinOptions);
	var optionList = []
	for (var i = 0, size = data.length; i < size; i++) {
		var option = {};
		var prex = data[i]['fromMarket'] == fromMarketId ? 'forward' : 'backward';
		option['fromMarket'] = data[i]['fromMarket'];
		option['toMarket'] = data[i]['toMarket'];
		option['coinType'] = data[i]['coinType'];
		option['betAmount'] = $('#' + prex + data[i]['coinType'] + "_amount").val() * BASE_CURRENCY_INCREASE;
		option['betProfit'] = $('#' + prex + data[i]['coinType'] + "_profit").val();
		option['activeYn'] =  $('#' + prex + data[i]['coinType'] + "_yn").is(':checked');
		optionList.push(option);
	}
	
	if (!validateInput(optionList)) {
		return;
	}
	var str = JSON.stringify(optionList)
	$.ajax({
		type: "POST",
		url: 'userOfferSetting.do',
		dataType:"json",
		data: {optionList : str,
			   tradeYn    : tradeYn
		},
		success: function(data) {
			alert();
		},
		error: function (e) {
			alert('Connection Error');
		}
	});
}

function runningCoinSetting() {
	$.ajax({
		type: "POST",
		url: 'getDealCoin.do',
		dataType:"json",
		data: {},
		success: function(data) {
			$('#runCoinDialog').modal();
			
			
			var html = '';
			$.each(data, function(key, value) {
				html += '<span class="col-sm-6">' + value['coinType'] + '</span>' +
						'<div class="col-sm-6">' +
						'<input class="toggle btn btn-primary" name="' + value['coinType'] + '" id="' + value['coinType'] + '_runYn" type="checkbox" data-toggle="toggle"></div>';
			});
			$('#runningCoinDiv').html(html);
			$.each(data, function(key, value) {
				if (value['runningCoin'] == true) {
					$('#' + value['coinType']+ '_runYn').prop('checked', true);
				}
			});
		},
		error: function (e) {
			alert('Connection Error');
		}
	});
	
}

function initPopup() {
	
	$('#updateRunningCoin').on('click', function () {
		var runCoins = [];
		$.each($('#runningCoinDiv  input'), function(key, value) {
			var coin = {};
			coin['type'] = value['name'];
			coin['runYn'] = $('#' + coin['type'] + "_runYn").is(':checked');
			runCoins.push(coin);
		});
		var str = JSON.stringify(runCoins)
		$.ajax({
			type: "POST",
			url: 'runningCoinSetting.do',
			dataType:"json",
			data: {runCoins : str
			},
			success: function(data) {
				alert('변경 완료~');
				location.reload();
			},
			error: function (e) {
				alert('Connection Error');
			}
		});
	});
}

function initSetting() {
	var data = JSON.parse(coinOptions);
	console.log(coinOptions);
	
	var forward = [];
	var backward = [];
	
	for (var i = 0, size = data.length; i < size; i++) {
		if (data[i]['fromMarket'] == fromMarketId && data[i]['toMarket'] == toMarketId) {
			forward.push(data[i]); 
		} else if (data[i]['fromMarket'] == toMarketId && data[i]['toMarket'] == fromMarketId) {
			backward.push(data[i]); 
		}
	}
	
	for (var i = 0, size = backward.length; i < size; i++) {
		$('#backwardDeal').append('  <div class="cols-sm-3"><div class="col-sm-3"><span class="col-sm-3">' + backward[i]['coinType'] + '</span></div></div>' +
        '<div class="cols-sm-3"><div class="col-sm-3"><input id="'  + "backward" + backward[i]['coinType'] + '_amount" type="text" size="5"/></div>'+
        '<div class="cols-sm-3"><div class="col-sm-3"><input id="'  + "backward" + backward[i]['coinType'] + '_profit" type="text" size="5"/></div><div class="col-sm-3">' +
			  '<input class="toggle btn btn-primary" id="' + "backward" +  backward[i]['coinType'] + '_yn"  type="checkbox" data-toggle="toggle"></div> ');
		if (backward[i]['activeYn'] == true) {
			$('#backward' + backward[i]['coinType']+ '_yn').prop('checked', true);
		} else {
			$('#backward' + backward[i]['coinType']+ '_yn').prop('checked', false);
		}
		$('#backward' + backward[i]['coinType']+ '_amount').val(backward[i]['betAmount'] /  BASE_CURRENCY_INCREASE);
		$('#backward' + backward[i]['coinType']+ '_profit').val(backward[i]['betProfit']);
	}
	
	for (var i = 0, size = forward.length; i < size; i++) {
		$('#forwardDeal').append('  <div class="cols-sm-3"><div class="col-sm-3"><span class="col-sm-3">' + forward[i]['coinType'] + '</span></div></div>' +
		        '<div class="cols-sm-3"><div class="col-sm-3"><input id="'  + "forward" + forward[i]['coinType'] + '_amount" type="text" size="5"/></div>'+
		        '<div class="cols-sm-3"><div class="col-sm-3"><input id="'  + "forward" + forward[i]['coinType'] + '_profit" type="text" size="5"/></div><div class="col-sm-3">' +
					  '<input class="toggle btn btn-primary" id="' + "forward" +  forward[i]['coinType'] + '_yn"  type="checkbox" data-toggle="toggle"></div> ');
		if (forward[i]['activeYn'] == true ) {
			$('#forward' + forward[i]['coinType']+ '_yn').prop('checked', true);
		} else {
			$('#forward' + forward[i]['coinType']+ '_yn').prop('checked', false);
		}
		$('#forward' + forward[i]['coinType']+ '_amount').val(forward[i]['betAmount'] / BASE_CURRENCY_INCREASE);
		$('#forward' + forward[i]['coinType']+ '_profit').val(forward[i]['betProfit']);
	}
	if ('true' == tradeYn) {
		$('#toggle-one').bootstrapToggle('on');
	} else {
		$('#toggle-one').bootstrapToggle('off');
	}
	
	$('#offerFromTag').html(fromMarket + ' -> ' + toMarket);
	$('#offerToTag').html(toMarket + ' -> ' + fromMarket);
	$('#optionFromTag').html(fromMarket + ' -> ' + toMarket);
	$('#optionToTag').html(toMarket + ' -> ' + fromMarket);
	$('#fromAsset').html(fromMarket);
	$('#toAsset').html(toMarket);
	$('#altAsset').html("other");
	
	var marketNames = JSON.parse(altMarket);
	var str = ' <option selected>' + toMarket + '</option>';
	for (num in marketNames) {
		str += '<option >' + marketNames[num] + '</option>';
	}
	
	$('#switchToMarket').html(str);
	// $( "#myselect option:selected" ).text();
}
function switchMarket() {
	$.ajax({
		type: "POST",
		url: 'switchToMarket.do',
		dataType:"json",
		data: {altMarketName : $( "#switchToMarket option:selected" ).text()
		},
		success: function(data) {
			alert('마켓 변경 완료');
			location.reload();

		},
		error: function (e) {
			alert('Connection Error');
		}
	});
}
function timeConverter(UNIX_timestamp){
	  var a = new Date(UNIX_timestamp );
	  var year = a.getFullYear();
	  var month = a.getMonth() + 1;
	  var date = a.getDate();
	  var hour = a.getHours();
	  var min = a.getMinutes();
	  var sec = a.getSeconds();
	  var time = month + '-' + date + ' ' +  + hour + ':' + min + ':' + sec ;
	  return time;
}

function validateInput(optionList) {
	
	for (var i = 0, size = optionList.length;i < size; i ++) {
		var option = optionList[i];
		if (!$.isNumeric(option['betAmount'] ) || !$.isNumeric(option['betProfit']  )) {
			alert('숫자를 입력하세요~');
			return false;
		}
//		if (option['betAmount']  > 2000000 || option['betAmount']  < 50000) {
//			alert('백만보다 크거나 5만보다 작으면 안돼요~');
//			return false;
//		}
//		if (option['betProfit']  > 10 || option['betProfit']  < -1) {
//			alert('이윤이 10프로보다 크거나 -1프로보다 작으면 안돼요~');
//			return false;
//		}
	}
	return true;
}

function postiveSetting() {
	positiveYn = $('#positiveYn').prop('checked');
}

//remainBitcoin.
$( document ).ready(function() {
	initMenus();
	initTables();
	
	initSchedular();
	
	initSetting();
	
	initPopup();
	
	$('#currency').on('change', function () {
		var coinType = $("#currency option:selected").val();
		$('#bidAmount').val(COIN_RANGE[coinType]['norm']);
	});
});
