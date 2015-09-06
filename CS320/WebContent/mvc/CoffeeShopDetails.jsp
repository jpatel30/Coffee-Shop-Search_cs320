<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CS3 Coffee Shop Locator</title>
</head>
<body>
	<jsp:useBean id="coffeeBean" class="cs320.mvc.CoffeeShopBean" scope="request"></jsp:useBean>
	<h3 style="color:red">Displaying Your Coffee Shops Details</h3>
	<c:set var="latitude" scope="request" value="${ coffeeBean.latitude}"></c:set>
	<c:set var="longitude" scope="request" value="${ coffeeBean.longitude}"></c:set>
	<table border=1 style="background: none repeat scroll 0% 0% yellow;">
		<tr>
			<th>Store Name</th><th>Phone</th><th>Address</th>
		</tr>
		<tr>
			<td><jsp:getProperty name="coffeeBean" property="storeName"/></td>
			<td><jsp:getProperty name="coffeeBean" property="phone"/></td>
			<td><jsp:getProperty name="coffeeBean" property="address"/></td>
		</tr>
	</table>
	<br><jsp:include page="MapView.jsp"/><jsp:include page="StreetView.jsp"/>
	
	
	<%-- <br><img height=500px width=500px src="http://maps.googleapis.com/maps/api/staticmap?center=${latitude},${longitude}&zoom=13&size=2500x2500&markers=color:red%7Clabel:S%7C${latitude},${longitude}"/>
	<img height=500px width=500px src="http://maps.googleapis.com/maps/api/streetview?location=${latitude},${longitude}&zoom=11&size=2500x2500"/>
 --%></body>
</html>