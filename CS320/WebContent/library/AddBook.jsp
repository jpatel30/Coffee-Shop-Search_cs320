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
<h1>CS Library - Add Book</h1>
<form action="../library/AddBook" method="post">
	<table border=1>
		<tr>
			<td>Book Title :</td>
			<td><input type="text" name="title"></td>
			<c:if test="${param.title != null}">
				<c:if test="${param.title == false}">
					<td><h5 style="color:red">Invalid Input</h5></td>
				</c:if>
			</c:if>
		</tr>
		<tr>
			<td>Copies :</td>
			<td><input type="text" name="copies"></td>
			<c:if test="${param.copies != null}">
				<c:if test="${param.copies == false}">
					<td><h5 style="color:red">Invalid Input</h5></td>
				</c:if>
			</c:if>
		</tr>
		<tr>
			<td colspan=2><input type="submit" value="Save"></td>
		</tr>
	</table>
</form>
</body>
</html>
<%-- 
<%
				String title = request.getParameter("title");
				if(title != null){
					if(title.equals("false")){
			%>
			<td><h6 style="color:red">Invalid Input</h6></td>
			<%}} %>
			
						<%
				String copies = request.getParameter("copies");
				if(copies != null){
				if(copies.equals("false")){
			%>
			<td><h6 style="color:red">Invalid Input</h6></td>
			<%}}%>
			
			 --%>