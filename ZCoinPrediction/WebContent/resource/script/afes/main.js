/**
 * 
 */
var chart = null;
function chartInit() {
	 Highcharts.setOptions({
	    global: {
	        useUTC: false
	    }
	});

	 chart =Highcharts.chart('orderChart', {
	    chart: {
	        type: 'spline',
	        animation: Highcharts.svg, // don't animate in old IE
	        marginRight: 10,
	        events: {
	        }
	    },
	    title: {
	        text: 'Live random data'
	    },
	    xAxis: {
	        type: 'datetime',
	        tickPixelInterval: 150
	    },
	    yAxis: {
	        title: {
	            text: 'Value'
	        },
	        plotLines: [{
	            value: 0,
	            width: 1,
	            color: '#808080'
	        }]
	    },
	    tooltip: {
	        formatter: function () {
	            return '<b>' + this.series.name + '</b><br/>' +
	                Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
	                Highcharts.numberFormat(this.y, 2);
	        }
	    },
	    legend: {
	        enabled: false
	    },
	    exporting: {
	        enabled: false
	    },
	    series: [{
	        name: 'Random data',
	        data: []
	    }]
	});
}
function init() {
	chartInit();
}

function drawChart(data) {
	var testData = [];
	chart.series[0].setData([[(new Date()).getTime() ,1], [(new Date()).getTime(),0], [(new Date()).getTime(),0]] );

}
$(document).ready(function(){

	init();
	
	
	// testData
	
	$.get('Dashboard',{
		datatype:'orderData'
	},function(data) {
		drawChart(data);
       console.log(data);
	});
});