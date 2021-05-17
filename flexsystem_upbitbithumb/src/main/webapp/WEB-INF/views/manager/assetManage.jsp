<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; UTF-8">
	<title>MARKETS</title>

	<script type="text/javascript" src="js/lib/jquery.1.9.1.min.js"></script>
	<script type="text/javascript" src="js/lib/jquery.jqGrid.js"></script>
	<script type="text/javascript" src="js/lib/bootstrap/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/afes/common/common.js"></script>
	<script type="text/javascript" src="js/afes/manager/asset.js"></script>

	<link rel="stylesheet" type="text/css" href="css/lib/bootstrap/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="css/lib/ui.jqgrid.css">
	<link rel="stylesheet" type="text/css" href="css/lib/jquery-ui.css">
	<link rel="stylesheet" type="text/css" href="css/afes/admin/markets.css">
		<!-- Website CSS style -->
</head>
<body>
	<script>var defaultAsset = '${defaultAsset}'</script>
	<script>var currentAsset = '${currentAsset}'</script>
    <div id="menu" class="container" style="height:100px"> </div>
	<div class="container" id="tourpackages-carousel">
      <div class="row" id="marketForm">
        <div class="col-lg-12"><h1>My Card <a class="btn icon-btn btn-primary pull-right" href="#" id="resetDefaultAsset"><span class="glyphicon btn-glyphicon glyphicon-plus img-circle"></span>default setting</a></h1></div>
	
		<div id="markets">
		</div>
      </div><!-- End row -->
    </div><!-- End container -->
    <div class="modal fade" id="targetMarket" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">비트코인 전송</h4>
        </div>
        <div class="modal-body">
	         <div>
				<div class="row main">
					<div class="main-login main-center">
						<form class="form-horizontal" method="post" action="#">
							
							<div class="form-group">
							  <label for="sel1">거래소</label>
							  <select class="form-control" id="marketList">
							  </select>
							</div>
							<div class="form-group">
								<label for="name" class="cols-sm-2 control-label"> 전송 금액 </label>
								<div class="cols-sm-10">
									<div class="input-group">
										<span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
										<input type="number" class="form-control" name="name" id="transferAmount" data-bind="value:replyNumber"/>
									</div>
								</div>
							</div>
	
						</form>
					</div>
				</div>
			</div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" id="transferBtn">update</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
</body>
</html>