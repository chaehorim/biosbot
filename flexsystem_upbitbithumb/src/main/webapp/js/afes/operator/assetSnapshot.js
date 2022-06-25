/**
 * 
 */

function getTodayDate() {
	var now = new Date();
	var month = now.getMonth() + 1;
	var day = now.getDate() ;
    return now.getFullYear() + '-' + ((now.getMonth() + 1 < 10) ? ('0' + month) : month) + '-' + ((day < 10)? ('0' + day) : day );
}
//remainBitcoin.
$( document ).ready(function() {
	initMenus();

    $('#toDate').val(getTodayDate());
    
	$('#sendDate').on('click', function () {
		alert($('#fromDate').val() + ' ' + $('#toDate').val());
		var fromDate = $('#fromDate').val();
		var toDate = ($('#toDate').val() === getTodayDate()) ? '' : $('#toDate').val();
		$.ajax({
			type: "POST",
			url: 'switchToMarket.do',
			dataType:"json",
			data: {fromDate : fromDate,
					toDate  : toDate
			},
			success: function(data) {
				alert('마켓 변경 완료');
				location.reload();

			},
			error: function (e) {
				alert('Connection Error');
			}
		});
	});
		
});
