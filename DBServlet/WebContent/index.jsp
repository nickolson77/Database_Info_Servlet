<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="rsvet.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/JS/simple.js"></script> --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Database info servlet</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/style.css">
</head>
<body>
	<center>
		<div>Choose table and click ОК</div>
		<!-- Form for table choice. After clicking ОК pass "tname" to servlet -->
		<form method="get" action="show">
			<select name="tname">
				<c:forEach items="${tableNames}" var="tableName">  
				           <option value="${tableName}">${tableName}</option>  
				</c:forEach>
			</select>
			<input type="submit" value=OK> 
		</form>
	</center>
</body>
</html> 