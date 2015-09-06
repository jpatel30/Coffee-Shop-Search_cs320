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
<form action="../onlinetest/SaveAnswer" method="post">
<h4>Question :
<c:forEach items="${Questions}" var="i">
	${i.question}
	<br><input type="radio" name="option" value="a">${i.optiona}
	<input type="radio" name="option" value="b">${i.optionb}
	<input type="radio" name="option" value="c">${i.optionc}
	<input type="radio" name="option" value="d">${i.optiond}
	<br><input type="hidden" name="qid" value="${i.question}">
	<input type="hidden" name="answer" value="${i.answer} ">
	<input type="submit" value="Next Question">
	<input type="submit" value="End Test" name="end">
</c:forEach>
</h4>
</form>
</body>
</html>