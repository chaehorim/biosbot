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
		<script type="text/javascript" src="js/afes/operator/autooffer
		.js"></script>
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
          <div class="col-md-7">
          	<label for="name" class="cols-sm-2 control-label">Offer</label>
          	<table id="offerList"></table>
          </div>
          <div class="col-sm-6">
          <label for="name" class="cols-sm-2 control-label">실수 여부</label>
			<input id="positiveYn"  type="checkbox" data-toggle="toggle">
		</div>
          <div class="col-md-7">
          	<label for="name" class="cols-sm-2 control-label">order</label>
          	<table id="orderList"></table>
          </div>
        </div>
         <div class="row col-md-5 ">
	         <div class="form-group">
				<label for="name" class="cols-sm-2 control-label">오퍼 예상 수익율</label>
				<div class="cols-sm-4">
				<div id="profitChart" style="height:250px; width:250px"></div>
				</div>
			</div>
			<div class="form-group ">
				          <!-- Form Name -->
		          <legend>금액</legend>
					          <!-- Form Name -->
		           <div class="cols-sm-12">
		            <label class="col-sm-4 control-label" for="textinput">거래소 명</label>
		            <label class="col-sm-4 control-label" for="textinput" id="coinType">코인</label>
		            <label class="col-sm-4 control-label" for="textinput">당지 화페</label>    						
		          </div>
		
		
		          <!-- Text input-->
		          <!-- Text input-->
		          <div id="amountForm">
		          </div>
		    </div>
		    
		    
   			<div class="form-group ">
				          <!-- Form Name -->
		        <legend>사용자 설정</legend>
					          <!-- Form Name -->
    		    <div class="cols-sm-12">
		            <label class="col-sm-6 control-label" for="textinput">최소 이윤</label>
		            <label class="col-sm-6 control-label" for="textinput">거래 여부</label>
   	            </div>
		        <div class="cols-sm-12">
		        	<div class="col-sm-6">
						<input id="ex1" data-slider-id='ex1Slider' type="text" data-slider-min="0.2" data-slider-max="5" data-slider-step="0.1" data-slider-value="10"/>
					</div>
					<div class="col-sm-6">
						<input id="toggle-one" checked type="checkbox" data-toggle="toggle">
					</div>
		        </div>
			
			  <div class="cols-sm-12">
		            <label class="col-sm-12 control-label" for="textinput">거래 금액</label>
   	            </div>
		        <div class="cols-sm-12">
					<div class="col-sm-12">
						<input id="bidAmount" data-slider-id='bidSlider' type="text" data-slider-min="1" data-slider-max="10" data-slider-step="0.1" data-slider-value="10"/>
					</div>
		        </div>
		
	            </div>
			          
			          
		    </div>
       </div>
       </div>

    
  </div>
 <div class="modal fade" id="stats" role="dialog">
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
								<div id="chart"></div>
							</div>
	

						</form>
					</div>
				</div>
			</div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
</body>
</html>