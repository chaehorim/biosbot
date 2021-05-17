<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="pragma" content="no-cache" />
<title>::myedu::</title>
<script type="text/javascript">
	//if ( window!=top ) top.location.replace( window.location.href )
</script>
</head>

<%-- <c:if test="${loginVO != null}">
	<c:set var="prgeUrl" value="/main/mainHome.do"/>
</c:if>
<c:if test="${loginVO == null }">
	<c:set var="prgeUrl" value="/login/login.do"/>
</c:if> --%>

<%-- <c:set var="prgeUrl" value="/main/mainHome.do"/> --%>	
	
<%-- <frameset rows="0,*" cols="*" frameborder="no" border="0" framespacing="0" >
	<frame name="hiddenFrameT" src="about:blank" frameborder="0" title="" />
	<frame name="mainFrame" src="${prgeUrl}" frameborder="0" title="컨텐츠페이지입니다" /> 
</frameset> --%>
<c:redirect url="/login.do"/>

</html>