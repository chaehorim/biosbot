<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="./resource/script/jquery-3.3.1.min.js"></script>
<script type="text/javascript">

$(document).ready(function(){
$('button').on('click',function(){
	console.log('1');
	$.get('Data',{
		datatype:'keyvalue'
	},function(data) {
       alert(data);
       console.log(data);
	});
	console.log('2');
});
});
</script>

</head>

<body>
	<button>Test</button>
	<div></div>
</body>

</html>