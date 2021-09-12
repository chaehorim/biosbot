<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Afes system login</title>
	<script type="text/javascript" src="js/lib/jquery.min.js"></script>
	<script type="text/javascript" src="js/afes/user/user.js"></script>
	<link rel="stylesheet" type="text/css" href="css/lib/bootstrap/bootstrap.css">
	<!-- Website CSS style -->
	<link rel="stylesheet" type="text/css" href="css/afes/user/login.css">
</head>
<body>

<div class="container">
  
  <div class="row" id="pwd-container">
    <div class="col-md-4"></div>
    
    <div class="col-md-4">
      <section class="login-form">
        <form action="login.do" method="get"  modelAttribute="loginAttribute">
			<img src="images/user/icon.png" class="img-responsive" alt="" />
          <input type="text" name="username" placeholder="id" required class="form-control input-lg" value="" path="username"/>
          
          <input type="password" name="password" class="form-control input-lg" id="password" placeholder="Password" required="" path="password"/>
          
          
          <div class="pwstrength_viewport_progress"></div>
          
          
          <button type="submit" name="go" class="btn btn-lg btn-primary btn-block">Sign in</button>
          <div>
            <a href="#">Create account</a> or <a href="#">reset password</a>
          </div>
		</form>
        
      </section>  
      </div>
      
      <div class="col-md-4"></div>
      

  </div>
  
  
</div>
</body>
</html>