<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page errorPage="error.jsp" %>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>E-Library</title>
</head>
<body>
<h1>CS Library</h1>
<form>
	<a href="AddBook.jsp">Add Book</a>&nbsp;<b>|&nbsp;</b><a href="../library/ReturnBook">Return Book</a>
	<br><br>
	<table border=1>
		<tr>
			<th>ID</th><th>Title</th><th>Copies</th><th>Operation</th>
		</tr>
		<c:if test = "${Books != null}">
			<c:forEach items="${Books}" var="book">
				<tr>
					<td><c:out value="${book.id} "></c:out></td>
					<td><c:out value="${book.title} "></c:out></td>
					<td><c:out value="${book.copies} "></c:out></td>
					<td><a href="<c:url value="../library/CheckoutPage?id=${book.id}"/>">Checkout</a></td>
				</tr>
			</c:forEach>
		</c:if>
	</table>
</form>
</body>
</html>
<%-- 
<%
		
			ServletContext context = this.getServletContext();
			ArrayList<Book> books = (ArrayList<Book>)context.getAttribute("Books");	
			for(int i=0; i< books.size();i++){%>
		
		<tr>
			<td><%= books.get(i).getId() %></td>
			<td><%= books.get(i).getTitle() %></td>
			<td><%= books.get(i).getCopies() %></td>
			<% if(!(books.get(i).getCopies() < 1)){ %>
			<td><a href="../library/CheckoutPage?id=<%= books.get(i).getId()%>">Checkout</a></td>
			<%} %>
		</tr>	
			
		<%}%>
		 --%>