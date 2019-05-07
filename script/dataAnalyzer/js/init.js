
	var chart, chart1 ;

    var statdatas, varaStats;

    $( document ).ready(function() {

    Highcharts.setOptions({
         global: {
            useUTC: false
        },
        time: {
            timezone: 'Australia/Sydney'
        }
    });

    chart = Highcharts.chart('container', {

    title: {
        text: 'Chart.update'
    },
    time: {
       // timezone: 'Europe/Oslo'
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

      chart1 = Highcharts.chart('container1', {

    title: {
        text: 'Chart.update'
    },
time: {
        // timezone: 'Australia/Sydney'
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

    statdatas = transformData();
    console.log(new Date(statdatas[0][0] ) + statdatas[0][0] );
    console.log(new Date(statdatas[statdatas.length -1][0]) + statdatas[statdatas.length -1][0] )
    var statDatas = extractDatas( $("#fromDate").val(), $("#toDate").val(), statdatas);
    setFromTo(statdatas[0][0], statdatas[statdatas.length - 1][0]);
    drawChart(statdatas);

    varaStats = transformVarAdata();
    drawVara(varaStats);

    $('#rangeSetter').click(function(){
        var rangeDatas = extractDatas( $("#fromDate").val(), $("#toDate").val(), statdatas);
        drawChart(rangeDatas);
        var tmpVara = extractDatas( $("#fromDate").val(), $("#toDate").val(), varaStats[0]);
        var tmpvaraa = extractDatas( $("#fromDate").val(), $("#toDate").val(), varaStats[1]);
        var rangeVaraDatas = [tmpVara, tmpvaraa];
        drawVara(rangeVaraDatas);


    });

});