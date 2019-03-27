		function getInitTime(firstDatas) {
			var minTime = 2009529200
			for (var i = 0, size = firstDatas.length; i < size; i ++) {
				if (firstDatas[i][0] < minTime) 
					minTime = firstDatas[0][i];
			}
			return minTime;
		}

		function getLastTime(lastDatas){
			var maxTime = 0;
			for (var i = 0, size = lastDatas.length; i < size; i ++) {
				var lastDate = lastDatas[i][lastDatas[i].length -1];
				if (lastDate > maxTime)
					maxTime = lastDate;
			}
	
			// return maxTime + 7210;
			return maxTime ;
		}
		function calc(input,initTime, finalTime) {
			//var initTime = 1539529200;
			
			//var increaseTime = 0.2 * 3600;
			
			var increaseTime = parseInt($("#timeSelector option:selected").val());

			var tmpCnt = 0;
			var data = [];

			for (var i = initTime; i <= finalTime; i = i + increaseTime) {
				var eachCnt = 0;
				for (var j = 0, size = input.length; j < size; j ++) {
					if (input[j] < i) {
						eachCnt ++;					
					}
				}
				var timeCnt = eachCnt - tmpCnt;
				data.push([(i + 3600 * 11) * 1000, timeCnt]);
				tmpCnt = eachCnt;				
			}

			return data;
		}

		function runChart() {
			var initTime = getInitTime([riseSuccess, riseFail, downSuccess,downFail]);
			var finalTime = getLastTime([riseSuccess, riseFail, downSuccess,downFail]);

			var riseSuccData = calc(riseSuccess,initTime, finalTime);
			var downSuccData = calc(downSuccess,initTime, finalTime);
			var riseFailData = calc(riseFail,initTime, finalTime);
			var downFailData = calc(downFail,initTime, finalTime);
	

			var succData = calc(riseSuccess.concat(downSuccess),initTime, finalTime);
			var failData = calc(riseFail.concat(downFail),initTime, finalTime);
			console.log(riseSuccData);
			console.log(downSuccData);
			chart.series[0].update({	    
				name:"rise success",
				color: "blue",
			    data: riseSuccData
			}, true);
			chart.series[1].update({	    
				name:"down success",
				color: "green",
			    data: downSuccData
			}, true);
			chart.series[2].update({	    
				name:"rise fail",
				color: "red",
			    data: riseFailData
			}, true);
			chart.series[3].update({	    
				name:"down fail",
				color: "pink",
			    data: downFailData
			}, true);
			chart.series[4].update({	    
				name:"total success",
				color: "green",
			    data: succData
			}, true);
			chart.series[5].update({	    
				name:"total failed",
				color: "orange",
			    data: failData
			}, true);
		}