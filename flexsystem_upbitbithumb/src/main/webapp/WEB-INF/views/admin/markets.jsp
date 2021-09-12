<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; UTF-8">
	<title>MARKETS</title>

	<script type="text/javascript" src="js/lib/jquery.1.9.1.min.js"></script>
	<script type="text/javascript" src="js/lib/jquery.jqGrid.js"></script>
	<script type="text/javascript" src="js/lib/bootstrap/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/lib/highcharts.js"></script>
	<script type="text/javascript" src="js/afes/common/common.js"></script>
	<script type="text/javascript" src="js/afes/admin/markets.js"></script>

	<link rel="stylesheet" type="text/css" href="css/lib/bootstrap/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="css/lib/ui.jqgrid.css">
	<link rel="stylesheet" type="text/css" href="css/lib/jquery-ui.css">
	<link rel="stylesheet" type="text/css" href="css/afes/admin/markets.css">
		<!-- Website CSS style -->
</head>
<body>
    <div id="menu" class="container" style="height:100px"> </div>
	<div class="container" id="tourpackages-carousel">
      <div class="row">
        <div class="col-lg-12"><h1>거래소 정보 <a class="btn icon-btn btn-primary pull-right" href="#" id="addButton"><span class="glyphicon btn-glyphicon glyphicon-plus img-circle"></span> 거래소 추가</a>
        </h1></div>
		<div id="marketForm">
        </div>
        <!-- div class="col-lg-12"><a href="#">View Deleted Cards</a></div-->
      </div><!-- End row -->
    </div><!-- End container -->
  <div class="modal fade" id="marketInfo" role="dialog">
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
								<label for="name" class="cols-sm-2 control-label">Market Id</label>
								<div class="cols-sm-10">
									<div class="input-group">
										<span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
										<input type="text" class="form-control" name="name" id="marketId"  placeholder="Enter your Name"/>
									</div>
								</div>
							</div>
	
							<div class="form-group">
								<label for="name" class="cols-sm-2 control-label">Market Name</label>
								<div class="cols-sm-10">
									<div class="input-group">
										<span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
										<input type="text" class="form-control" name="name" id="marketName"  placeholder="Enter your Name"/>
									</div>
								</div>
							</div>
							
							<div class="form-group">
								<label for="name" class="cols-sm-2 control-label">지역</label>
								<div class="cols-sm-10">
									<div class="input-group">
										<span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
										<input type="text" class="form-control" name="name" id="currencyCode"  placeholder="Enter your Name"/>
									</div>
								</div>
							</div>
							<div class="form-group" style="display:none">
								<label for="name" class="cols-sm-2 control-label">지역</label>
								<div class="cols-sm-10">
									<div class="input-group">
										<span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
										<input type="text" class="form-control" name="name" id="currencyName"  placeholder="Enter your Name"/>
									</div>
								</div>
							</div>
	
							<div class="form-group">
								<label for="name" class="cols-sm-2 control-label">Url</label>
								<div class="cols-sm-10">
									<div class="input-group">
										<span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
										<input type="text" class="form-control" name="name" id="url"  placeholder="Enter your Name"/>
									</div>
								</div>
							</div>
	
							<div class="form-group">
								<label for="name" class="cols-sm-2 control-label">금액</label>
								<div class="cols-sm-10">
									<div class="input-group">
										<span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
										<input type="text" class="form-control" name="name" id="amount"  placeholder="Enter your Name"/>
									</div>
								</div>
							</div>

							<div class="form-group">
								<label for="name" class="cols-sm-2 control-label">BitCoin Count</label>
								<div class="cols-sm-10">
									<div class="input-group">
										<span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
										<input type="text" class="form-control" name="name" id="bcCnt"  placeholder="Enter your Name"/>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label for="name" class="cols-sm-2 control-label">From market?</label>
								<div class="cols-sm-10">
									<div class="input-group">
										<span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
										<input type="text" class="form-control" name="name" id="fromYn"  placeholder="Enter your Name"/>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label for="name" class="cols-sm-2 control-label">To market?</label>
								<div class="cols-sm-10">
									<div class="input-group">
										<span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
										<input type="text" class="form-control" name="name" id="toYn"  placeholder="Enter your Name"/>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label for="name" class="cols-sm-2 control-label">active?</label>
								<div class="cols-sm-10">
									<div class="input-group">
										<span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
										<input type="text" class="form-control" name="name" id="activeYn"  placeholder="Enter your Name"/>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" id="saveData">save</button>
          <button style="display:none" type="button" class="btn btn-default" id="updateData">update</button>
          <button style="display:none" type="button" class="btn btn-default" id="keyInput">key input</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
  <div class="modal fade" id="keysInfo" role="dialog">
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
								<label for="name" class="cols-sm-2 control-label">public Key</label>
								<div class="cols-sm-10">
									<div class="input-group">
										<span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
										<input type="password" class="form-control" name="name" id="publicKey"  placeholder="Enter your Name"/>
									</div>
								</div>
							</div>
	
							<div class="form-group">
								<label for="name" class="cols-sm-2 control-label">private Key</label>
								<div class="cols-sm-10">
									<div class="input-group">
										<span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
										<input type="password" class="form-control" name="name" id="privateKey"  placeholder="Enter your Name"/>
									</div>
								</div>
							</div>

							<div class="form-group">
								<label for="name" class="cols-sm-2 control-label">비트코인 출금 비밀번호(없으면 패스)</label>
								<div class="cols-sm-10">
									<div class="input-group">
										<span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
										<input type="password" class="form-control" name="name" id="transpassword"  placeholder="Enter your Password for transfer"/>
									</div>
								</div>
							</div>


							<div class="form-group">
								<label for="name" class="cols-sm-2 control-label">address</label>
								<div class="cols-sm-10">
									<div class="input-group">
										<span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
										<input type="password" class="form-control" name="name" id="address"  placeholder="Enter your Name"/>
									</div>
								</div>
							</div>							
						</form>
					</div>
				</div>
			</div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" id="updateKeys">update</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
</body>
</html>