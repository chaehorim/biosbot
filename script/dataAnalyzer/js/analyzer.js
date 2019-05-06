	// function reading and transform data
	function transformData() {
		// console.log("old" + datax);
	//"1540738804:205.23:5.86315:205.2:8.03946;1540738804:205.23:5.86315:205.2:8.03946;"
		var statDatas = [];
		var tmpSet = datax.split(";");

		for (idx in tmpSet) {
			if (tmpSet[idx] == '') 
				continue;
			// console.log("set :" + tmpSet[idx]);
			var tmpDatas = tmpSet[idx].split(":");
			var statData = [parseInt(tmpDatas[0]) * 1000, parseFloat(((Number(tmpDatas[1]) + Number(tmpDatas[3])) / 2).toFixed(2))];
			statDatas.push(statData);
			// console.log(statDatas);
		}
		return statDatas;
	}

	function transformVarAdata() {
		var statDatas = [];
		statDatas.push([]);
		statDatas.push([]);

		var tmpSet = aValue.split(":");

		for (idx in tmpSet) {
			if (tmpSet[idx] == '') 
				continue;
			// console.log("set :" + tmpSet[idx]);
			var tmpDatas = tmpSet[idx].split(";");
			var stat1Data = [parseInt(tmpDatas[0]), parseFloat(tmpDatas[1])];
			var stat2Data = [parseInt(tmpDatas[0]), parseFloat(tmpDatas[2])];
			statDatas[0].push(stat1Data);
			statDatas[1].push(stat2Data);
		}
		return statDatas;
	}

	// function arrange data by user input
	function extractDatas(fromData, toData, tmpDatas) {
		
		var fdat = new Date(Date.UTC(fromData.substring(0, 4),fromData.substring(4, 6),fromData.substring(6, 8),fromData.substring(8, 10),fromData.substring(10, 12),fromData.substring(12, 14)));
		var ftimestamp = fdat.getTime();
		console.log(fromData + fdat + '' + ftimestamp);
		var tdat = new Date(Date.UTC(toData.substring(0, 4),toData.substring(4, 6),toData.substring(6, 8),toData.substring(8, 10),toData.substring(10, 12),toData.substring(12, 14)));
		var ttimestamp = tdat.getTime();
		console.log(toData + tdat +'' + ttimestamp);
		
		var statDatas = [];
		for (var i = 0, size = tmpDatas.length; i < size; i++) {
			var inputDate = tmpDatas[i][0] * 1000;
			if (inputDate > ftimestamp && inputDate < ttimestamp) {
				statDatas.push(tmpDatas[i]);
			}
		}

		return statDatas;
	}

	// function draw chart

