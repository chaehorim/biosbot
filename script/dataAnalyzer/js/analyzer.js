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
			var statData = [parseInt(tmpDatas[0]), parseFloat(((Number(tmpDatas[1]) + Number(tmpDatas[3])) / 2).toFixed(2))];
			statDatas.push(statData);
			console.log(statDatas);
		}
		return statDatas;
	}

	// function arrange data by user input
	function extractDatas(fromData, toData, tmpDatas) {
		
		var datum = new Date(Date.UTC(fromData.substring(0, 3),fromData.substring(4, 5),fromData.substring(4, 5),fromData.substring(6, 7),fromData.substring(8, 9),fromData.substring(10, 11)));
		console.log(datum);
		var statDatas = [];


		return statDatas;
	}

	// function draw chart

