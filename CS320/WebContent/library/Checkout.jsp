<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page errorPage="error.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>E-Library</title>
</head>
<body>
<h1>CS Library - Checkout Book</h1>

<form action="../library/CheckoutPage?action=checkout" method="post">
	<table border=1>
		<tr>
			<td>Book Id :</td>
			<td><input type=text name="id" value="<c:out value="${BookId}"/>" readonly></td>
		</tr>
		<tr>
			<td>Book Title :</td>
			<td><input type="text" name="title" value="<c:out value="${Title}"/>" readonly></td>
		</tr>
		<tr>
			<td>Student Name :</td>
			<td><input type="text" name="studentName"></td>
			<c:if test="${isStudentValid != null}">
				<c:if test="${isStudentValid == false }">
					<td><h4 style="color:red;">Invalid Input</h4></td>
				</c:if>
			</c:if>
		</tr>
		<tr>
			<td colspan=2><input type="submit" value="Checkout"></td>
		</tr>
	</table>
</form>
</body>
</html>