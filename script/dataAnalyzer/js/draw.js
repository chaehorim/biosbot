	function drawChart(datas) {
			
			chart.series[0].update({	    
				name:"rise success",
				color: "blue",
			    data: datas
			}, true);

			chart.xAxis[0].isDirty = true;
			                chart.redraw();

		}