/**
 * 
 */
var defAssets;
var curAssets;

function init(defaultAsset, currentAsset) {
	console.log(defaultAsset);
	console.log(currentAsset);
	var defaults = JSON.parse(defaultAsset);
	var currents = JSON.parse(currentAsset);
	defAssets = defaults;
	curAssets = currents;
	var html = '';
	for (var name in defaults) {
		console.log(JSON.stringify(name));
		var defAsset = defaults[name];
		var curAsset = currents[name];
		html += '<div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">'+
          '<div class="thumbnail">'+
          '    <div class="caption">'+
          '      <div class="col-lg-12">'+
          '          <span class="glyphicon glyphicon-credit-card"></span>'+
          '          <span class="glyphicon glyphicon-trash pull-right text-primary"></span>'+
          '      </div>'+
          '      <div class="col-lg-12 well well-add-card">'+
          '          <h4>' + name + '</h4>'+
          '      </div>'+
          '      <div class="col-lg-12">'+
          '          <p class"text-muted" id="' +name + '_defAmount">시작 금액  : <span>' + defAsset['amounts']+ '</span></p>'+
          '          <p class"text-muted" id="' +name + '_curAmount">현재 금액 : <span>' + curAsset['amounts']+ '</span></p>'+
          '          <p class"text-muted" id="' +name + '_amountDiff">차액 : <span>' + (curAsset['amounts'] - defAsset['amounts'])+ '</span></p>'+
          '          <p class"text-muted" id="' +name + '_defBitCoin">시작 bitcoin: <span>' + defAsset['bitCnt']+ '</span></p>'+
          '          <p class"text-muted" id="' +name + '_curBitCoin">현재 bitcoin: <span>' + curAsset['bitCnt']+ '</span></p>'+
          '          <p class"text-muted" id="' +name + '_bcDiff">bitcoin변동: <span>' + (curAsset['bitCnt'] - defAsset['bitCnt'])+ '</span></p>'+
          '      </div>'+
          '      <button type="button" name="' + name + '" class="btn btn-primary btn-xs btn-update btn-add-card deposit">금액 조정</button>'+
          '  </div>'+
          '</div>'+
        '</div>';
	}
	$('#markets').append(html);
	$('.deposit').on('click', function () {
		//resetAsset($(this).attr('name'));
		depositBtn($(this).attr('name'));
	});
}

function depositBtn(marketName) {
	$('#targetMarket').modal('toggle');
	$('#targetMarket').attr('data', marketName);

	var html = '';
	for (var name in curAssets) {
		if (marketName != name) {
			html += '<option>' + name + '</option>';
		}
	}
	$('#marketList').append(html);
}

function resetAsset(marketId) {
	$.ajax({
		type: "GET",
		url: 'marketAsset.do',
		dataType:"json",
		data: {
			marketId: marketId
		},
		success: function(data) {
			console.log(JSON.stringify(data));
			var defAmount = $('#' + marketId + '_curAmount span').text();
			var defBitcoin = $('#' + marketId + '_defBitCoin span').text();
			$('#' + marketId + '_curAmount span').text(data['amounts']);
			$('#' + marketId + '_curBitCoin span').text(data['bitCnt']);
			$('#' + marketId + '_amountDiff span').text(data['amounts']-defAmount);
			$('#' + marketId + '_bcDiff span').text(data['bitCnt']-defBitcoin);
		},
		error: function () {
			alert('not really');
		}
	});
}

function resetDefaultAsset(marketId) {
	$.ajax({
		type: "GET",
		url: 'setDefaultAsset.do',
		dataType:"json",
		data: {
			marketId: marketId
		},
		success: function(data) {
			console.log(JSON.stringify(data));
			
			$('#markets').children().remove();
			var curVal = data[0];
			var defVal = data[1];
			alert('금액 재설정하였습니다.');
			init(JSON.stringify(defVal), JSON.stringify(curVal));
		},
		error: function () {
			alert('not really');
		}
	});
}

function transfer() {
	var target = $("#marketList option:selected").val();
	var value = $('#transferAmount').val();
	var fromMarket = $('#targetMarket').attr('data');
	$.ajax({
		type: "GET",
		url: 'transferBitcoin.do',
		dataType:"json",
		data: {
			fromMarket: fromMarket,
			toMarket :  target,
			value  : value
		},
		success: function(data) {
			console.log(JSON.stringify(data));
			if (data['succesYn']) {
				alert('성공적으로 전송 되었습니다.');
			} else {
				alert('실패했습니다.');
			}
		},
		error: function () {
			alert('not really');
		}
	});
}

$( document ).ready(function() {
	initMenus();
	init(defaultAsset, currentAsset);
	$('#resetDefaultAsset').on('click', function(){
		resetDefaultAsset();
	});
	
	$('#transferBtn').on('click', function() {
		transfer();
	});
});