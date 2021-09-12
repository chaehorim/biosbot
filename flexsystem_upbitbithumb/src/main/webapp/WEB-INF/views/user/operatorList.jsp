<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; UTF-8">
	<title>Users</title>

	<script type="text/javascript" src="js/lib/jquery.1.9.1.min.js"></script>
	<script type="text/javascript" src="js/lib/jquery.jqGrid.js"></script>
	<script type="text/javascript" src="js/lib/jquery-ui.js"></script>
	<script type="text/javascript" src="js/lib/bootstrap/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/afes/common/common.js"></script>
	<script type="text/javascript" src="js/afes/user/operatorList.js"></script>

	<link rel="stylesheet" type="text/css" href="css/lib/bootstrap/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="css/lib/ui.jqgrid.css">
	<link rel="stylesheet" type="text/css" href="css/lib/jquery-ui.css">
	<link rel="stylesheet" type="text/css" href="css/afes/admin/markets.css">
	<link rel="stylesheet" type="text/css" href="css/afes/user/style.css">
		<!-- Website CSS style -->
</head>
<body>
    <div id="menu" class="container" style="height:100px"> </div>
	<div class="container" id="tourpackages-carousel">
      <div class="row">
        <div class="col-lg-12"><h1>Users 
        	
			<a class="btn icon-btn btn-primary pull-right" href="#" id="addButton"><span class="glyphicon btn-glyphicon glyphicon-plus img-circle"></span> Operator 분배</a>
			<a class="btn icon-btn btn-primary pull-right" href="#" id="amountButton"><span class="glyphicon btn-glyphicon glyphicon-plus img-circle"></span> 금액 분배</a>
			<h2> <select class="selectpicker col-lg-3 pull-right"  id="managers">
		    </select></h2>
        </h1></div>
		<div id="userForm">
        </div>
        <!-- div class="col-lg-12"><a href="#">View Deleted Cards</a></div-->
      </div><!-- End row -->
    </div><!-- End container -->
  <div class="modal fade" id="userListTable" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">User</h4>
        </div>
        <div class="modal-body">
	         <div>
				<div class="row main">
					<div class="main-left">
							
							<div >
					        	<table id="operatorList"></table>
					    	</div>
					</div>
				</div>
			</div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" id="updateData">update</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
  <div class="modal fade" id="managerAmountInfo" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">User</h4>
        </div>
        <div class="modal-body">
	         <div>
				<div class="row main">
					<div class="main-left">
						<div id="managerForm" class="pre-scrollable">
							
						</div>
					</div>
				</div>
			</div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" id="updateAmount">update</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
</body>
</html>