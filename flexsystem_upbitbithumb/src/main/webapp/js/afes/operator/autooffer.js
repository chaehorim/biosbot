/**
 * 
 */
var OFFER_TYPE_BUY = 1;
var OFFER_TYPE_SELL = 2;
var OFFER_MAX_RANGE = 0.1;
//var COIN_RANGE = {"btc": {"min" : 0.05, "max" : 2}, "eth" : {"min" : 1, "max" :20}, "etc":{"min" :10 , "max" : 200}, "ltc" :{"min":1, "max" : 20}};
var COIN_RANGE = {"btc": {"min" : 0.005, "max" : 2}, "eth" : {"min" : 0.02, "max" :20}, "etc":{"min" :0.02 , "max" : 200}, "ltc" :{"min":1, "max" : 20}};

var run = true;
var popup_exchange_rate = 0;
var MINIMUM_PROFIT = 0.01;
var BID_RANGE = 0.02;
var MAX_PROFIT_RANGE = 3;
var chart;
var positiveYn = false;

function initTables() {
	$("#orderList").jqGrid({
		colNames:['시간','매수금액', '비트코인 건수', '매도금액', '매도건수','매수화폐', '매수화페', '매도화페', '매도화페', '환율', '예상이윤','예상 이윤 금액','코인종류', '상태'],
	   	colModel:[
	   		{name:'startTime',  index:'startTime', width:120, formatter: timeStampToString},
	   		{name:'buyAmout',index:'buyAmout', width:60, formatter: floatRoundoff},
	   		{name:'buyBcCnt',index:'buyBcCnt', width:40, formatter: floatRoundoff},
	   		{name:'sellAmount',index:'sellAmount', width:50, formatter: floatRoundoff},
	   		{name:'sellBcCnt',index:'sellBcCnt', width:80, hidden: true},
	   		{name:'fromLocaleName',index:'fromLocaleName', width:40},
	   		{name:'fromLocale',index:'fromLocale', width:60, hidden: true},
	   		{name:'toLocaleName',index:'toLocaleName', width:40},
	   		{name:'toLocale',index:'toLocale', width:60, hidden: true},
	   		{name:'outerExchangeRate',index:'outerExchangeRate', width:70},
	   		{name:'profitPercentage',index:'profitPercentage', width:50, formatter: percentRoundoff},
	   		{name:'profit',index:'profit', width:50, formatter: floatRoundoff},
	   		{name:'coinType',index:'coinType', width:30},
	   		{name:'status',index:'status', width:30, formatter: successFunc}
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
	$('#orderList').after('<button id="orderStat" class="button blue">통계</button>');
	
}

function initSchedular() {
	getDatas();
}

function getDatas() {
	if (!run) {
		return;
	}
	getUserOfferData();
	setTimeout(getDatas, 5000);
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
			var tmpData = data['offerList'];
			$('#orderList').jqGrid('setGridParam', {data: data['orderList'], datatype: 'local'}).trigger('reloadGrid');
			addMarketsForm(data['userSettingList']);
			addCurrencyForm(data['outterOfferList'], data['curTime']);
//			var newVal = calcChartData(tmpData) * 1;
//			console.log(data['recentOffer']['profitPercentage'] + ' chart : ' + newVal);
//			
//			var point = chart.series[0].points[0];
//			point.update(newVal);
		},
		error: function () {
			//alert('not really');
		}
	});
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
	var tradeYn = $('#toggle-one').prop('checked');
//	var minProfit = $('#ex1').val() / 100;
	var bidAmount = $('#bidAmount').val();
	var userId = $('#id').val();
	var coinType = $("#currency option:selected").val();
	var exchangeRate = $('#exchangeRate').val();
	var fromCur = $("#direction option:selected").attr('from');
	var toCur = $("#direction option:selected").attr('to');
	var realCurrency = $('#realCurrency').val();
	if (!validateInput(coinType, bidAmount, exchangeRate, realCurrency)) {
		return;
	}
	$.ajax({
		type: "POST",
		url: 'userOfferSetting.do',
		dataType:"json",
		data: {
			userId : userId,
			tradeYn : tradeYn,
			bidAmount : bidAmount,
			coinType : coinType,
			exchangeRate : exchangeRate,
			realCurrency, realCurrency,
			fromCur : fromCur,
			toCur : toCur
		},
		success: function(data) {
			alert('성공적으로 변경하였습니다.');		
		},
		error: function (e) {
			console.log(e);
		}
	});
}

function sendCurrencySetting() {
	var btcBid = $('#btcInput').val();
	var ethBid = $('#ethInput').val();
	var etcBid = $('#etcInput').val();
	if (!$.isNumeric(btcBid ) || !$.isNumeric(ethBid) || !$.isNumeric(etcBid)) {
		alert('숫자를 입력하세요~');
		return false;
	}
	$.ajax({
		type: "POST",
		url: 'coinBidSetting.do',
		dataType:"json",
		data: {
			btcBid : btcBid,
			ethBid : ethBid,
			etcBid : etcBid
		},
		success: function(data) {
			alert('성공적으로 변경하였습니다.');		
		},
		error: function (e) {
			console.log(e);
		}
	});
}


function initPopup() {
	
}

function initSetting() {
	var data = JSON.parse(userSetting);
	console.log(userSetting);
	$('#id').val(data['userId']);

	if (data['minProfit'] < 0.001) {
		$('#ex1').attr('data-slider-value', 10);
	} else {
		$('#ex1').attr('data-slider-value', data['minProfit'] * 100);
	}
	
	$('#bidAmount').val( data['coinNum']);
	
	$('#exchangeRate').val( data['exchangeRate']);
	
	$('#realCurrency').val(data['currency']);
	
	if (data['tradeYn']) {
		$('#toggle-one').bootstrapToggle('on');
	} else {
		$('#toggle-one').bootstrapToggle('off');
	}
	$("#currency").val(data['coinType']);

	$("#direction").val(data['fromCur']);
	
	if (data['from'] == 82) {
		$("#selectBox").val("82");
	} else 
		$("#selectBox").val("86");
	var bidMap = data['outerBidMap']
	
	var coins = ['btc', 'eth', 'etc'];
	for (var i = 0, size = coins.length; i < size; i ++ ) {
		var data = bidMap[i];
		var coin = coins[i]; 
		$('#' + coin + 'Input').val(bidMap[coin]);
	}
	
	var marketDatas = JSON.parse(marketAsset);
	addMarketsForm(marketDatas);
}

function addMarketsForm(marketDatas) {
	$('#amountForm').children().remove();
	var assetHtml = '', bitcoinHtml = '';
	for (var i = 0, size = marketDatas.length; i < size; i ++) {
		var marketData = marketDatas[i];
		assetHtml += '<div class="cols-sm-12">' +
			            '<label class="col-sm-4 control-label" for="textinput">' +marketData['marketId'] + '</label>' +
			            '<div class="col-sm-4">' +
			            '  <input type="text" class="form-control"  value="' + marketData['remainBcCnt']+'" readonly>' +
			            '</div>' +
			            '<div class="col-sm-4">' +
			            '  <input type="text" class="form-control"  value="' +marketData['remainAmount'] +'"readonly>' +
			            '</div>' +
			          '</div>';
	}
	assetHtml += '<legend></legend>';
	$('#amountForm').append(assetHtml);
}

function addCurrencyForm(tableDatas, time) {
	$('#title2').text('오퍼 (' + timeConverter(time) + ')');
	for (var i = 0, size = tableDatas.length; i < size; i ++) {
		var data = tableDatas[i];
		var coin = data['coinType'];
		var marketData = data['fromMarket'] + '->' + data['toMarket'];
		$('#' + coin + 'Markets').text(data['fromMarket'] + '->' + data['toMarket']);
		var marketData = data['fromMarket'] + '->' + data['toMarket'];
		$('#' + coin + 'Markets_reverse').text(data['toMarket'] + '->' + data['fromMarket']);
		$('#' + coin + 'TradeVal').text('[' + data['buyVal1'] + '/' + data['sellVal2'] + ']');
		$('#' + coin + 'TradeVal_reverse').text('[' + data['buyVal2'] + '/' + data['sellVal1'] + ']');
		$('#' + coin + 'Currency').text(data['outterCurrency1']);
		$('#' + coin + 'Currency_reverse').text(data['outterCurrency2']);
		$('#' + coin + 'Profit').text((data['profit1'] * 100).toFixed(3) +'%');
		$('#' + coin + 'Profit_reverse').text((data['profit2'] * 100).toFixed(3) + '%');
	}
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

function validateInput(coinType, bidAmount, exchangeRate, realCurrency) {
	if (!$.isNumeric(bidAmount ) || !$.isNumeric(exchangeRate) || !$.isNumeric(realCurrency)) {
		alert('숫자를 입력하세요~');
		return false;
	}
	
	var range = COIN_RANGE[coinType];
	if (bidAmount < range['min'] || bidAmount > range['max']) {
		alert(coinType + '의 주문금액범위는 [' + range['min'] +' - ' + range['max'] + '] 입니다.');
		return false;
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
		$('#bidAmount').val(COIN_RANGE[coinType]['min']);
	});
});