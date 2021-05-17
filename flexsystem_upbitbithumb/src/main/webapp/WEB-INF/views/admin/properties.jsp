<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
    <head> 
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<script type="text/javascript" src="js/lib/jquery.min.js"></script>
		<script type="text/javascript" src="js/afes/common/common.js"></script>
		<script type="text/javascript" src="js/afes/admin/properties.js"></script>
	
		<link rel="stylesheet" type="text/css" href="css/lib/bootstrap/bootstrap.css">
		<!-- Website CSS style -->
		<link rel="stylesheet" type="text/css" href="css/afes/user/register.css">

		<title>Admin</title>
	</head>
	<body>
	   <script>var properties = '${properties}'</script>
	   <div id="menu" class="container" style="height:100px"> </div>
		<div class="container">
			<div class="row main">
				<div class="main-login main-center">
					<form class="form-horizontal" method="post" action="#">
						
						<div class="form-group">
							<label for="name" class="cols-sm-2 control-label">offer 실행 주기</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
									<input type="text" class="form-control" name="id" id="period"  placeholder="거래 주기"/>
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<label for="name" class="cols-sm-2 control-label">거래 비트코인</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
									<input type="text" class="form-control" name="name" id="bidBitcoin"  placeholder="건당 거래 비트코인"/>
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<label for="name" class="cols-sm-2 control-label">최저 이윤</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
									<input type="text" class="form-control" name="name" id="minMargin"  placeholder="Enter your Name"/>
								</div>
							</div>
						</div>

						<div class="form-group">
							<label for="email" class="cols-sm-2 control-label">Buy 최대 범위</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>
									<input type="text" class="form-control" name="email" id="minBuyMargin"  placeholder="Enter your Email"/>
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<label for="email" class="cols-sm-2 control-label">Sell 최대 범위</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>
									<input type="text" class="form-control" name="phone" id="minSellMargin"  placeholder="Enter your phone"/>
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<label for="email" class="cols-sm-2 control-label">Order 확인 주기</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>
									<input type="text" class="form-control" name="address" id="orderPeriod"  placeholder="Enter your address"/>
								</div>
							</div>
						</div>

						<div class="form-group">
							<label for="password" class="cols-sm-2 control-label">Order 변경 횟수</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
									<input type="text" class="form-control" name="password" id="orderChangeCnt"  placeholder="Enter your Password"/>
								</div>
							</div>
						</div>

						<div class="form-group">
							<label for="confirm" class="cols-sm-2 control-label">Order 변경 대기 건수</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
									<input type="text" class="form-control" name="confirm" id="orderWaitCnt"  placeholder="Confirm your Password"/>
								</div>
							</div>
						</div>

						<div class="form-group ">
							<button type="button" class="btn btn-primary btn-lg btn-block login-button" onclick="saveProp()">Save</button>
						</div>
					</form>
				</div>
			</div>
		</div>

		<script type="text/javascript" src="assets/js/bootstrap.js"></script>
	</body>
</html>