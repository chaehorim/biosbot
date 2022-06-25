<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; UTF-8">
<title>Insert title here</title>

		<script type="text/javascript" src="js/lib/jquery.1.9.1.min.js"></script>
		<script type="text/javascript" src="js/lib/jquery.jqGrid.js"></script>
		<script type="text/javascript" src="js/lib/bootstrap/bootstrap.min.js"></script>
		<script type="text/javascript" src="js/lib/bootstrap-slider.js"></script>
		<script type="text/javascript" src="js/lib/bootstrap-toggle.js"></script>
		<script type="text/javascript" src="js/afes/common/common.js"></script>
		<script type="text/javascript" src="js/afes/operator/dealList.js"></script>
		<script type="text/javascript" src="js/lib/highcharts.js"></script>
		<script src="js/lib/highcharts-more.js"></script>
	
		<link rel="stylesheet" type="text/css" href="css/lib/bootstrap/bootstrap.css">
		<link rel="stylesheet" type="text/css" href="css/lib/bootstrap-slider.css">
		<link rel="stylesheet" type="text/css" href="css/lib/bootstrap-toggle.css">
		<link rel="stylesheet" type="text/css" href="css/afes/offer/offer.css">
		<link rel="stylesheet" type="text/css" href="css/lib/ui.jqgrid.css">
		<link rel="stylesheet" type="text/css" href="css/lib/jquery-ui.css">
		<!-- Website CSS style -->
</head>
<body>
	<script>var userSetting = '${userSetting}'</script>
	<script>var marketAsset = '${marketAsset}'</script>
	<script>var coinOptions = '${coinOptions}'</script>
	<script>var tradeYn = '${tradeYn}'</script>
	<script>var fromMarket = '${fromMarket}'</script>
	<script>var toMarket = '${toMarket}'</script>
	<script>var fromMarketId = '${fromMarketId}'</script>
	<script>var toMarketId = '${toMarketId}'</script>
	<script>var altMarket = '${altMarket}'</script>
    <div id="menu" class="container" style="height:100px"> </div>
    <div class="section">
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <h1 class="text-left">Operator OfferList&nbsp;</h1>
          </div>
        </div>
        <p></p>
        <div class="row col-md-7">
          <legend id="title2">거래 정보</legend>
          <div class="col-sm-6">
		</div>
          <div class="col-md-7">
          	<label for="name" class="cols-sm-2 control-label" id ="offerFromTag">빗썸->업비트</label>
          	<table id="offerFromList"></table>
          </div>
          <div class="col-md-7">
          	<label for="name" class="cols-sm-2 control-label" id ="offerToTag">업비트-> 빗썸</label>
          	<table id="offerToList"></table>
          </div>
          <div class="col-md-7">
          	<label for="name" class="cols-sm-2 control-label">order</label>
          	<table id="dealList"></table>
          </div>
          <div class="col-md-7">
          	<label for="name" class="cols-sm-2 control-label">log</label>
          	<table id="logList"></table>
          </div>
          <div class="col-md-7">
          	<label for="name" class="cols-sm-2 control-label">문제로그</label>
          	<table id="riskyLogList"></table>
          </div>
        </div>
         <div class="row col-md-5 ">
		    
   			<div class="form-group ">
   			<button type="button" onclick="runningCoinSetting()">거래코인 설정</button>
				          <!-- Form Name -->
		        <legend id="id="optionFromTag""> </legend>
					          <!-- Form Name -->
					          
	            <div class="cols-sm-12">
		            <label class="col-sm-3 control-label" for="textinput">거래 코인</label>
		            <label class="col-sm-3 control-label" for="textinput">거래 금액</label>
		            <label class="col-sm-3 control-label" for="textinput">거래 이윤</label>
		            <label class="col-sm-3 control-label" for="textinput">거래 여부</label>
   	            </div>
   	            <div id="forwardDeal">
				</div>
            </div>
			  <div class="form-group ">
				          <!-- Form Name -->
		        <legend id="optionToTag"></legend>
					          <!-- Form Name -->
					          
	            <div class="cols-sm-12">
		            <label class="col-sm-3 control-label" for="textinput">거래 코인</label>
		            <label class="col-sm-3 control-label" for="textinput">거래 금액</label>
		            <label class="col-sm-3 control-label" for="textinput">거래 이윤</label>
		            <label class="col-sm-3 control-label" for="textinput">거래 여부</label>
   	            </div>
   	            <div id="backwardDeal">
				</div>       
            </div>    
    		    <div class="cols-sm-12">
		            <label class="col-sm-6 control-label" for="textinput">거래 여부</label>
		            <label class="col-sm-6 control-label" for="textinput">설정 변경</label>
   	            </div>
		        <div class="cols-sm-12">

					<div class="col-sm-6">
						<input id="toggle-one" checked type="checkbox" data-toggle="toggle">
					</div>
					<div class="col-sm-6">
						<button type="button" onclick="sendUserSetting()">전송</button>
					</div>
		        </div>
				<legend>금액</legend>
					          <!-- Form Name -->
		           <div class="cols-sm-12">
		            <label class="col-sm-3 control-label" for="textinput">코인명 </label>
		            <label class="col-sm-2 control-label" id="fromAsset" for="textinput"> </label>
		            <label class="col-sm-2 control-label" id="toAsset" for="textinput"></label>
		            <label class="col-sm-2 control-label" id="altAsset" for="textinput"></label>
		            <label class="col-sm-3 control-label" for="textinput">전체</label>    						
		          </div>
		          <div id="amountForm">
		          </div>
		          <div>
					<select id="switchToMarket" >
					</select>
					<button type="button" onclick="switchMarket()">거래소 변환</button>
		          </div>
		    </div>
       </div>
       </div>

    
  <div class="modal fade" id="runCoinDialog" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">마켓</h4>
        </div>
        <div class="modal-body">
	         <div>
				<div class="row main">
					<div class="main-login main-center">
						<form class="form-horizontal" method="post" action="#">
							
							<div class="form-group">
								<label for="name" class="cols-sm-2 control-label">To market?</label>
								<div class="cols-sm-12">
									<div id="forwardDeal">
									  <div class="cols-sm-12">
									  	<div class="col-sm-12" id="runningCoinDiv">
										  	<span class="col-sm-6">ADA</span>
										  	<div class="col-sm-6">
										  		<input class="toggle btn btn-primary" id="xxx" type="checkbox" data-toggle="toggle">
									  		</div>
									  	 </div>
								  	 </div>  
								  	 </div>  
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" id="updateRunningCoin">save</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
</body>
</html>