<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
    <head> 
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<script type="text/javascript" src="js/lib/jquery.min.js"></script>
		<script type="text/javascript" src="js/lib/bootstrap/bootstrap.min.js"></script>
		<script type="text/javascript" src="js/afes/common/common.js"></script>
		<script type="text/javascript" src="js/afes/user/operatorAmount.js"></script>
	
		<link rel="stylesheet" type="text/css" href="css/lib/bootstrap/bootstrap.css">
		<!-- Website CSS style -->
		<link rel="stylesheet" type="text/css" href="css/afes/user/operatorAmount.css">

		<title>Admin</title>
	</head>
	<body>
	  <script>var users = '${users}'</script>
  	   <div id="menu" class="container" style="height:100px"> </div>
		<div class="container">
			<div class="row main">
				<div class="panel-heading">
	               <div class="panel-title text-center">
	               		<h1 class="title">Company Name</h1>
	               		<hr />
	               	</div>
	            </div> 
				<div class="main-login main-center">
					<form class="form-horizontal" method="post" action="#">
						<div id="amountForm"></div>

						<div class="form-group ">
							<button type="button" class="btn btn-primary btn-lg btn-block login-button" onclick="updateAmount()">Update</button>
						</div>
					</form>
				</div>
			</div>
		</div>

	</body>
</html>