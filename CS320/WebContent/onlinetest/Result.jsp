<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Online Test</title>
</head>
<body>
	<h3>Below is the result
	<c:forEach items="${Map }" var="list">
		<br>Question : <c:out value="${list.key} "></c:out>
		<br>Your answer was : <c:out value="${list.value} "></c:out>	
	</c:forEach>
	</h3>	
	<a href="../onlinetest/OnlineTest?isNewTest=true">Start test again</a>
</body>
</html>