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
	<script type="text/javascript" src="js/afes/manager/currency.js"></script>

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
      <div class="row">
      	<div class="col-lg-12"><h1>환율 <a class="btn icon-btn btn-primary pull-right" href="#" id="addButton"><span class="glyphicon btn-glyphicon glyphicon-plus img-circle"></span> Add New Currency</a></h1></div>
        <div class="col-md-9">
        	<table id="currencyList"></table>
        </div>
     </div><!-- End row -->
    </div><!-- End container -->
</body>
</html>