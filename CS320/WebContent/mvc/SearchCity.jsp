<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CS3 Coffee Shop Locator</title>
</head>
<h2>Search by City</h2>
<body>
	<form action="../mvc/CoffeeShopSearchController" method="get">
		<input type="text" name="cityName" placeholder="Enter a city">
		<input type="submit" value="Search">
		<input type="hidden" name="type" value="city">
		<p>Please search with "hong cong" (mispelled) and then with "hong kong"</p>
		<br><a href="index.jsp">Go to main page</a>
	</form>
	
	<br>
	<c:if test="${CoffeeList != null}">
		<c:choose>
			<c:when  test="${fn:length(CoffeeList) > 0}">
				<h3 style="color:red"><c:out value="Displaying ${fn:length(CoffeeList)} Records"/></h3> 
				<table border=1 style="background: none repeat scroll 0% 0% yellow;">
					<tr>
						<th>Sr No.</th><th>Store Name</th><th>City</th><th>Zipcode</th><th>View Details</th><th>Map</th>
					</tr>
					<c:forEach var="coffeeShops" items="${CoffeeList}" varStatus="count">
						<tr>
							<td><c:out value="${count.index+1}"></c:out></td>
							<td><c:out value="${coffeeShops.name}"></c:out></td>
							<td><c:out value="${coffeeShops.city}"></c:out></td>
							<td><c:out value="${coffeeShops.postalCode}"></c:out></td>
							<td><a href="../mvc/CoffeeShopDetailsController?id=${coffeeShops.storeId}">Click for more details</a></td>
							<td><img height=200px width=200px src="http://maps.googleapis.com/maps/api/staticmap?center=${coffeeShops.latitude},${coffeeShops.longitude}&zoom=13&size=2500x2500&markers=color:red%7Clabel:S%7C${coffeeShops.latitude},${coffeeShops.longitude}"/></td>
						</tr>
					</c:forEach>
				</table>
			</c:when>
			<c:otherwise>
				<h4 style="background: none repeat scroll 0% 0% red;">No Records Founds</h4>
			</c:otherwise>
		</c:choose>
	</c:if>
	
</body>
</html>