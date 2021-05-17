/**
 * 
 */

function saveProp() {
	var period = $('#period').val();
	var bidBitcoin = $('#bidBitcoin').val();
	var minMargin = $('#minMargin').val();
	var minBuyMargin = $('#minBuyMargin').val();
	var minSellMargin = $('#minSellMargin').val();
	var orderPeriod = $('#orderPeriod').val();
	var orderChangeCnt = $('#orderChangeCnt').val();
	var orderWaitCnt = $('#orderWaitCnt').val();
	$.ajax({
		type: "POST",
		url: 'saveProperties.do',
		data: {
			period : period,
			bidBitcoin: bidBitcoin,
			minMargin : minMargin,
			minBuyMargin : minBuyMargin,
			minSellMargin : minSellMargin,
			orderPeriod : orderPeriod,
			orderChangeCnt : orderChangeCnt,
			orderWaitCnt : orderWaitCnt
		},
		success: function(data) {
			alert('success');
		},
		error: function () {
			alert('not really');
		}
	});
}

function initProp() {
	var prop = JSON.parse(properties);
	console.log(prop);
	$('#period').val(prop['period']);
	$('#bidBitcoin').val(prop['bidBitcoin']);
	$('#minMargin').val(prop['minMargin']);
	$('#minBuyMargin').val(prop['minBuyMargin']);
	$('#minSellMargin').val(prop['minSellMargin']);
	$('#orderPeriod').val(prop['orderConfirmPeriod']);
	$('#orderChangeCnt').val(prop['orderChangeCnt']);
	$('#orderWaitCnt').val(prop['orderChangeWaitCnt']);
}
$( document ).ready(function() {
	initMenus();
	initProp();
});