	function drawChart(datas) {
			
			chart.series[0].update({	    
				name:"rise success",
				color: "blue",
			    data: datas
			}, true);

			chart.xAxis[0].isDirty = true;
			                chart.redraw();

	}

	function drawVara(datas) {
			
			chart1.series[0].update({	    
				name:"var A",
				color: "blue",
			    data: datas[0]
			}, true);
			chart1.series[1].update({	    
				name:"var AA",
				color: "orange",
			    data: datas[1]
			}, true);

			chart1.xAxis[0].isDirty = true;
			chart1.redraw();

	}