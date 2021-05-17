/**
 * 
 */
function initTable() {
	
	$("#currencyList").jqGrid({
//		datatype: "json",
		colNames:['마지막 업데이트 시간','화폐', '화폐', '화폐', '화폐','환율'],
	   	colModel:[
	   		{name:'time',  index:'time', width:160, formatter: timeStampToDateString},
	   		{name:'fromName',index:'fromName', width:100},
	   		{name:'toName',index:'toName', width:100},
	   		{name:'fromCode',index:'toCode', width:120, hidden: true},
	   		{name:'toCode',index:'toName', width:100, hidden: true},
	   		{name:'value',index:'value', width:100}
	   	], 
	   	onSelectRow: function(rowid, status, e) {  			
	   		$('#currencyInfo').modal();
			$('#updateCurrency').show();
			$('#addCurrency').hide();	
	   		var rowData = $('#currencyList').getRowData( rowid );
	   		$("#fromCntry option[code='" + rowData['fromCode'] + "'] ").attr("selected", "selected");
	   		$("#toCntry option[code='" + rowData['toCode'] + "'] ").attr("selected", "selected");
	   		$("#fromCntry").attr('disabled', 'disabled');
	   		$("#toCntry").attr('disabled', 'disabled');
	   		$("#value").val(rowData['value']);
	   	},
	    datastr : currentList,
	   	height : 600,
//	   	rowNum:60,
//	   	rowList:[10,20,30],
//	   	pager: '#pager3',
//	   	sortname: 'id',
	    viewrecords: true,
	    sortorder: "desc",
	    loadonce: true,
	    caption: "Offer List"
	});
	var currentList = JSON.parse(currencyStr);
	for(var i=0;i<=currentList.length;i++)
		jQuery("#currencyList").jqGrid('addRowData',i+1,currentList[i]);
	
}

function refreshCurrency() {
	jQuery("#currencyList").jqGrid("clearGridData");
	$.ajax({
		type: "GET",
		url: 'selectCurrency.do',
		dataType:"json",
		success: function(datas) {
			$('#currencyInfo').modal('toggle');
			for(var i=0;i<=datas.length;i++)
				jQuery("#currencyList").jqGrid('addRowData',i+1,datas[i]);
		},
		error: function () {
			alert('Connection Error');
		}
	});
}

function getCurrencyValue() {
	var data = {};
	data['fromCode'] = $("#fromCntry option:selected").attr('code');
	data['toCode'] = $("#toCntry option:selected").attr('code');
	data['value'] = $("#value").val();
	if (data['fromCode'] == data['toCode']) {
		alert('input and out is same.');
		return false;
	}
	return data;
}
function addCurrency() {
	var data = getCurrencyValue();
	if (data == false) {
		return;
	} 
		
	$.ajax({
		type: "POST",
		url: 'addCurrency.do',
		//dataType:"json",
		data: data,
		success: function(currentList) {
			jQuery("#currencyList").jqGrid("clearGridData");
			for(var i=0;i<=currentList.length;i++)
				jQuery("#currencyList").jqGrid('addRowData',i+1,currentList[i]);
			$('#currencyInfo').modal('toggle');
		},
		error: function () {
			alert('Connection Error');
		}
	});
}

function updateCurrency() {
	var data = getCurrencyValue();
	if (data == false) {
		return;
	} 
		
	$.ajax({
		type: "POST",
		url: 'updateCurrency.do',
		//dataType:"json",
		data: data,
		success: function(data) {
			alert('success');
			refreshCurrency();
		},
		error: function () {
			alert('Connection Error');
		}
	});
}

$( document ).ready(function() {
	initMenus();
	initTable();
	
	$('#addButton').on('click', function() {
		$('#currencyInfo').modal();
		$('#updateCurrency').hide();
		$('#addCurrency').show();	
   		$("#fromCntry").removeAttr('disabled');
   		$("#toCntry").removeAttr('disabled');
	});
	
	$('#addCurrency').on('click', function() {
		addCurrency();
	});
	
	$('#updateCurrency').on('click', function() {
		updateCurrency();
	});
});