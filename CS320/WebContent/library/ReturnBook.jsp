<%@page import="java.util.ArrayList"%>
<%@ page import="cs320.library.Book" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>E-Library</title>
</head>
<body>
<h4>CS Library - Return Book</h4>
	<table border=1>
		<tr>
			<th>Book Id</th><th>Book Title</th><th>Borrowed By</th><th>Borrowed On</th><th>Operation</th>
		</tr>
		<c:if test="${Borrower_List != null }">
			<c:choose>
				<c:when test="${fn: length(Borrower_List) > 0}">
					<c:forEach items="${Borrower_List}" var="borrowerList">
						<tr>
							<td><c:out value="${borrowerList.id }"/></td>
							<td><c:out value="${borrowerList.title }"/></td>
							<td><c:out value="${borrowerList.studentName }"/></td>
							<td><c:out value="${borrowerList.borrowed }"/></td>
							<td><a href="<c:url value="ReturnBook?id=${borrowerList.borrowerId}"/>">Return</a></td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan=5><h3 style="color:red"><c:out value="No Records Found"></c:out></h3></td>
					</tr>			
				</c:otherwise>
			</c:choose>
		</c:if>
	</table>
	<br/>
	<a href="../library/MainPage">Go Back</a>
</body>
</html>
<%-- <%
		ArrayList<Book> borrowerList = (ArrayList<Book>)this.getServletContext().getAttribute("Borrower List");
		if(borrowerList.size() > 0){
			for(int i=0; i<borrowerList.size(); i++){
	%>
		<tr>
			<td><%= borrowerList.get(i).getId() %></td>
			<td><%= borrowerList.get(i).getTitle() %></td>
			<td><%= borrowerList.get(i).getStudentName() %></td>
			<td><%= borrowerList.get(i).getBorrowed() %></td>
			<td><a href="ReturnBook?id=<%= borrowerList.get(i).getBorrowerId() %>">Return</a></td>
		</tr>
	
	<%}} else { %>
		<tr>
			<td colspan=5><h3 style="color:red">No Records found</h3></td>
		</tr>
	<%}%>
	
	 --%>