<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="rsvet.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Database info servlet</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/style.css">
	</head>
	<body>
		<center>
			<div>You choosed table <span>${param.tname}</span>
				<br><a href="${pageContext.request.contextPath}/show">Go back</a>
			</div>	
		<center>
		<c:choose>
		    <c:when test="${empty rows[0]}">
		        <div>The table is empty</div>
		    </c:when>    
		    <c:otherwise>
		    	<!-- Dinamically build table on jsp -->
				<table class="simple-little-table" cellspacing='0'>
				    <tr>
						<c:forEach items="${rows[0]}" var="column">
					    	<th>${column.key}</th>
					    </c:forEach>
				    </tr>
				    <c:forEach items="${rows}" var="columns">
				      <tr>
				        <c:forEach items="${columns}" var="column">
				          <td>${column.value}</td>
				        </c:forEach>
				      </tr>
				    </c:forEach>
				</table>
		    </c:otherwise>
		</c:choose>
	</body>
</html>