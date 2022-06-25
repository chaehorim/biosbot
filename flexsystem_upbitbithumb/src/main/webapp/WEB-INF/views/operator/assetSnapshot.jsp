<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; UTF-8">
	<title>CURRENCY</title>

	<script type="text/javascript" src="js/lib/jquery.1.9.1.min.js"></script>
	<script type="text/javascript" src="js/lib/jquery.jqGrid.js"></script>
	<script type="text/javascript" src="js/lib/bootstrap/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/lib/highcharts.js"></script>
	<script type="text/javascript" src="js/afes/common/common.js"></script>
	<script type="text/javascript" src="js/afes/operator/assetSnapshot.js"></script>

	<link rel="stylesheet" type="text/css" href="css/lib/bootstrap/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="css/lib/ui.jqgrid.css">
	<link rel="stylesheet" type="text/css" href="css/lib/jquery-ui.css">
	<link rel="stylesheet" type="text/css" href="css/afes/admin/markets.css">
		<!-- Website CSS style -->
</head>
<body>
	<script>var currencyStr = '${currencyList}'</script>
	    <div id="menu" class="container" style="height:100px"> </div>
	<div class="container" id="tourpackages-carousel">
	     <div class="form-group col-lg-5">
		  <label for="example-date-input" class="col-2 col-form-label">Date</label>
		  <div class="col-5">
		    <input class="form-control" type="date" value="" id="fromDate">
		  </div>
		</div>
		<div class="form-group col-lg-5">
		  <label for="example-date-input" class="col-2 col-form-label">Date</label>
		  <div class="col-5">
		    <input class="form-control" type="date" value="" id="toDate">
		  </div>
		</div>
		<a class="btn icon-btn btn-primary pull-right" href="#" id="sendDate">눌러봐요</a>
      <div class="row">
      	 <div id="amountForm">
	        <div class="cols-sm-12">
	            <label class="col-sm-3 control-label" for="textinput">코인</label>
	            <label class="col-sm-3 control-label" for="textinput">From 자산</label>
	            <label class="col-sm-3 control-label" for="textinput">To 자산</label>
	            <label class="col-sm-3 control-label" for="textinput">차액</label>
            </div>
            <div class="cols-sm-12">
	        	<label class="col-sm-3 control-label" for="textinput">BTC </label>
	        	<label class="col-sm-2 control-label" for="textinput">0.88082</label>
	        	<label class="col-sm-2 control-label" for="textinput">4.11973</label>
	        	<label class="col-sm-2 control-label" for="textinput">0.00000</label>
	        	<label class="col-sm-3 control-label" for="textinput">5.00055</label>
	        	<label class="col-sm-3 control-label" for="textinput">KRW </label>
	        	<label class="col-sm-2 control-label" for="textinput">33789.1</label>
	        	<label class="col-sm-2 control-label" for="textinput">11823.3</label>
	        	<label class="col-sm-2 control-label" for="textinput">3931.8</label>
	        	<label class="col-sm-3 control-label" for="textinput">49544.2</label>
	        	<label class="col-sm-3 control-label" for="textinput">ETC </label>
	        	<label class="col-sm-2 control-label" for="textinput">31.9</label>
	        	<label class="col-sm-2 control-label" for="textinput">4468.5</label>
	        	<label class="col-sm-2 control-label" for="textinput">0.0</label>
	        	<label class="col-sm-3 control-label" for="textinput">4500.5</label>
	        	<label class="col-sm-3 control-label" for="textinput">BCH </label>
	        	<label class="col-sm-2 control-label" for="textinput">1.3</label>
	        	<label class="col-sm-2 control-label" for="textinput">138.7</label>
	        	<label class="col-sm-2 control-label" for="textinput">0.0</label>
	        	<label class="col-sm-3 control-label" for="textinput">140.0</label>
	        	<label class="col-sm-3 control-label" for="textinput">XRP </label>
	        	<label class="col-sm-2 control-label" for="textinput">3398.5</label>
	        	<label class="col-sm-2 control-label" for="textinput">1604.0</label>
	        	<label class="col-sm-2 control-label" for="textinput">0.0</label>
	        	<label class="col-sm-3 control-label" for="textinput">5002.5</label>
	        	<label class="col-sm-3 control-label" for="textinput">EOS </label>
	        	<label class="col-sm-2 control-label" for="textinput">5137.7</label>
	        	<label class="col-sm-2 control-label" for="textinput">1862.4</label>
	        	<label class="col-sm-2 control-label" for="textinput">0.0</label>
	        	<label class="col-sm-3 control-label" for="textinput">7000.2</label>
	        	<label class="col-sm-3 control-label" for="textinput">ETH </label>
	        	<label class="col-sm-2 control-label" for="textinput">46.6</label>
	        	<label class="col-sm-2 control-label" for="textinput">36.2</label>
	        	<label class="col-sm-2 control-label" for="textinput">17.6</label>
	        	<label class="col-sm-3 control-label" for="textinput">100.4</label>
	        	<label class="col-sm-3 control-label" for="textinput">QTUM </label>
	        	<label class="col-sm-2 control-label" for="textinput">163.6</label>
	        	<label class="col-sm-2 control-label" for="textinput">0.0</label>
	        	<label class="col-sm-2 control-label" for="textinput">0.0</label>
	        	<label class="col-sm-3 control-label" for="textinput">163.6</label>
	        	<label class="col-sm-3 control-label" for="textinput">ADA </label>
	        	<label class="col-sm-2 control-label" for="textinput">381.7</label>
	        	<label class="col-sm-2 control-label" for="textinput">49624.9</label>
	        	<label class="col-sm-2 control-label" for="textinput">0.0</label>
	        	<label class="col-sm-3 control-label" for="textinput">50006.5</label>
        	</div>
       	</div>
     </div><!-- End row -->
    </div><!-- End container -->
</body>
</html>