	function drawChart(datas) {
			
			chart.series[0].update({	    
				name:"rise success",
				color: "blue",
			    data: datas
			}, true);

			var minVal = 10000000;
			for (idx in datas) {
				if (datas[idx][1] < minVal)
					minVal = datas[idx][1];
			}

		 	chart.update({
		        yAxis: {
		            min: Math.floor(minVal / 3) * 3
		        }
		    });
			
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

	function setFromTo(fromTime, toTime) {
		var fromStr = timeStampToTime(fromTime);
		var toStr = timeStampToTime(toTime);
		console.log(fromStr);
		console.log(toStr);
		$("#fromDate").val(fromStr);
		$("#toDate").val(toStr)

	}