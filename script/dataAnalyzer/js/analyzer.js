	// function reading and transform data
	function transformData() {
		console.log("old" + datax);
	//"1540738804:205.23:5.86315:205.2:8.03946;1540738804:205.23:5.86315:205.2:8.03946;"
		var statDatas = [];
		var tmpSet = datax.split(";");

		for (idx in tmpSet) {
			if (tmpSet[idx] == '') 
				continue;
			console.log("set :" + tmpSet[idx]);
			var tmpDatas = tmpSet[idx].split(":");
			var statData = [tmpDatas[0], ((Number(tmpDatas[1]) + Number(tmpDatas[3])) / 2).toFixed(2)];
			statDatas.push(statData);
			console.log(statDatas);
		}
	}

	// function arrange data by user input


	// function draw chart

