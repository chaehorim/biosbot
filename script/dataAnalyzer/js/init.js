
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
        }
    },
    series: [{
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
	runChart();

	$('#timeSelector').change(function() {
		runChart();
	});

    transformData();

});