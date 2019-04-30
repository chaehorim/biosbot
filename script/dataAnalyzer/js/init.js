
	var chart ;

    $( document ).ready(function() {


    chart = Highcharts.chart('container', {

    title: {
        text: 'Chart.update'
    },
time: {
        timezone: 'Australia/Sydney'
    },
    subtitle: {
        text: 'Plain'
    },
    legend: {
        layout: 'vertical',
        align: 'right',
        verticalAlign: 'middle'
    },
     xAxis: {
		        type: 'datetime',
    },
    yAxis: {
        title: {
            text: 'Rainfall (mm)'
        },
         min: 195,
    },
    series: [{
        // type: 'column',
        // colorByPoint: true,
        data: [[1540738804000, 210],[1540738814000,220], [1540738824000,230], [1540738834000,215],[1540738834000,250]],
        showInLegend: true
    }, {
        // type: 'column',
        // colorByPoint: true,
        data: [],
        showInLegend: true
    }, {
        type: 'column',
        // colorByPoint: true,
        data: [],
        showInLegend: true
    }, {
        type: 'column',
        // colorByPoint: true,
        data: [],
        showInLegend: true
    }, {
        type: 'column',
        // colorByPoint: true,
        data: [],
        showInLegend: true
    }, {
        type: 'column',
        // colorByPoint: true,
        data: [],
        showInLegend: true
    }]

	});
	// runChart();

	// $('#timeSelector').change(function() {
	// 	runChart();
	// });

    var statdatas = transformData();
    console.log(new Date(statdatas[0][0] * 1000) + statdatas[0][0] * 1000);
    console.log(new Date(statdatas[statdatas.length -1][0] * 1000) + statdatas[statdatas.length -1][0] )
    statDatas = extractDatas( $("#fromDate").val(), $("#toDate").val(), statdatas);
    drawChart(statdatas);

});