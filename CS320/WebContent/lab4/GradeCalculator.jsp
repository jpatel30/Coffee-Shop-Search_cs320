<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel=stylesheet href="grades.css" type="text/css">
<title>Your Grades</title>
</head>
<body>
	<h1 style="color:red">Welcome, Palak!</h1>
	<form name = "grades" action="GradeCalculator.jsp" method="post">
		<table border=1 id=grade>
			<tr>
				<th></th><th>Homework and Assignments</th><th>Percentage</th><th>Mid term</th><th>Percentage</th><th>Final</th><th>Percentage</th><th>Attendance</th><th>Percentage</th>
			</tr>
			<tr>
				<th>User's Marks</th>
				<td>Enter your grades: <input type ="text" name="grade1"></td>
				<% 	
					double result1 = 0;
					double total = 0;
					String grade1 = request.getParameter("grade1");
					if(grade1 != null && grade1.trim().length() > 0){
						result1 = calculate(Double.parseDouble(grade1), 500, 30);
					}
				%>
				<td><%= result1 %>%</td>
				
				<td>Enter your grades: <input type="text" name="grade2"></td>
				<% 	
					double result2 = 0;
					String grade2 = request.getParameter("grade2");
					if(grade2 != null && grade2.trim().length() > 0){
						result2 = calculate((Double.parseDouble(grade2)), 121, 30);
					}
				%>
				<td><%= result2 %>%</td>
				
				<td>Enter your grades: <input type="text" name="grade3"></td>
				<% 	
					double result3 = 0;
					String grade3 = request.getParameter("grade3");
					if(grade3 != null && grade3.trim().length() > 0){
						result3 = calculate((Double.parseDouble(grade3)), 150, 35);
					}
				%>
				<td><%= result3 %>%</td>
				
				<td>Enter your Attendance: <input type="text" name="attendance"></td>
				<% 	
					double result4 = 0;
					String grade4 = request.getParameter("attendance");
					if(grade4 != null && grade4.trim().length() > 0){
						result4 = Double.parseDouble(grade4);
					}
				%>
				<td><%= (result4/5)*100 %>%</td>
			</tr>
			<tr>
				<th>Total Marks</th>
				<td align="center" colspan=2>500</td>
				<td align="center" colspan=2>121</td>
				<td align="center" colspan=2>150</td>
				<td align="center" colspan=2>5</td>
			</tr>
		</table>
		<br/><input type="submit" value="Calculate" id="button">
		<br/>
		<%
			
			double finalMarks = result1+result2+result3+result4;
			String yourGrade = "";
			if(finalMarks >= 92 )
				yourGrade = "A";
			else if(finalMarks >= 90 )
				yourGrade = "A-";
			else if(finalMarks >= 87)
				yourGrade = "B+";
			else if(finalMarks >= 83)
				yourGrade = "B";
			else if(finalMarks >= 80)
				yourGrade = "B-";
			else if(finalMarks >= 77)
				yourGrade = "C+";
			else if(finalMarks >= 73)
				yourGrade = "C";
			else if(finalMarks >= 70)
				yourGrade = "C-";
			else if(finalMarks >= 67)
				yourGrade = "D+";
			else if(finalMarks >= 63)
				yourGrade = "D";
			else if(finalMarks >= 60)
				yourGrade = "D-";
			else if(finalMarks < 60)
				yourGrade = "F";
		%>
			<marquee>
				<h1 style="color:red">Your Percentage is : <%= finalMarks %>%</h1>
				<h1 style="color:red">Your Grade is : <%= yourGrade %></h1>
			</marquee>
				
	</form>
	<%!
		public double calculate(double marks, double total, double percent){
			double result=0;
			result = Math.round((marks/total)*percent);
			return result;
	}
	%>
</body>
</html>