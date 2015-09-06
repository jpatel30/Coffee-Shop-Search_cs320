<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Online Calculator</title>
</head>
<body>
			<%
				String numberOne = request.getParameter("number1");
				String numberTwo = request.getParameter("number2");
				String operand = request.getParameter("button");
				double result = 0;
				if(numberOne !=null && !numberOne.isEmpty() && numberOne !=null && !numberTwo.isEmpty() && operand != null){
					
					if(operand.equals("+")){
						result = Integer.parseInt(numberOne)+Integer.parseInt(numberTwo);
					} else if(operand.equals("-")){
						result = Integer.parseInt(numberOne)-Integer.parseInt(numberTwo);
					} else if(operand.equals("*")){
						result = Integer.parseInt(numberOne)*Integer.parseInt(numberTwo);
					} else if(operand.equals("/")){
						result = Integer.parseInt(numberOne)/Integer.parseInt(numberTwo);
					}
				}
			%>
<form action="OnlineCalculator.jsp" method="post">
	<table border=1>
		<tr>
			<th colspan=2>Online Calculator</th>
		</tr>
		<tr>
			<td>Enter the 1st number : </td>
			<td><input type="text" name="number1"></td>
		</tr>
		<tr>
			<td>Enter the 2nd number : </td>
			<td><input type="text" name="number2"></td>
		</tr>
		<tr>
			<td> Result : </td>
			<td><input type="text" name="result"
			<% if(numberOne != null && numberTwo != null){ %>
					value ="<%= result %>">
			<%}%>
				
			</td>
		</tr>
		<tr>
			<td colspan=2>
				<input type="submit" name ="button" value="+">
				<input type="submit" name ="button" value="-">
				<input type="submit" name ="button" value="*">
				<input type="submit" name ="button" value="/">
			</td>
		</tr>
		<tr>
			<td colspan=2><input type="Reset" value="Clear"></td>
		</tr>
	</table>
</form>
</body>
</html>