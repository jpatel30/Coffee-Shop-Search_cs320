<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Grade Calculator</title>
</head>
<body>


<%! double hwper; %>
<%! double mtper; %>
<%! double ftper; %>
<%! double atper; %>
<%! double totalper; %>	

<% if((request.getParameter("homework")!=null && 
	   request.getParameter("homework1")!=null &&  
       request.getParameter("midterm")!=null &&
       request.getParameter("midterm1")!=null && 
       request.getParameter("finale")!=null && 
       request.getParameter("final1")!=null && 
       request.getParameter("attendance")!=null && 
       request.getParameter("attendance1")!=null))
{

    int hwlusermark = Integer.parseInt(request.getParameter("homework"));
    int hwltotalmark = Integer.parseInt(request.getParameter("homework1"));
    int mtusermark = Integer.parseInt(request.getParameter("midterm"));
    int mttotalmark = Integer.parseInt(request.getParameter("midterm1"));
    int fnusermark = Integer.parseInt(request.getParameter("finale"));
    int fntotalmark = Integer.parseInt(request.getParameter("final1"));
    int atusermark = Integer.parseInt(request.getParameter("attendance"));
    int attotalmark = Integer.parseInt(request.getParameter("attendance1"));

     int total =100;
    double hwper=(hwlusermark/hwltotalmark)*100*0.3;
    double mtper=(mtusermark/mttotalmark)*100*0.3;
    double ftper=(fnusermark/fntotalmark)*100*0.35;
    double atper=(atusermark/attotalmark)*100*0.05;
    double totalper=((hwper+mtper+ftper+atper)/total)*100;%>
    
    

    <% if(totalper < 60) { %>
    	<h1>Grade : F</h1>
    	
        	
        
    <%}else if(totalper >= 60 && totalper <=62){%>
		<h1>Grade : D-</h1>       
        
    <% } else if(totalper >=63 && totalper <=66) { %>
        <h1>Grade : D</h1>
        
    <% } else if(totalper >=67 && totalper <=69) { %>
       <h1>Grade : D+</h1>
        
    <% } else if(totalper >=70 && totalper<=72){ %>
        <h1>Grade : C-</h1>
        
    <% } else if(totalper >=73 && totalper <=76){%>
        <h1>Grade : C</h1>
       
    <% } else if(totalper >=77 && totalper<=79){ %>
        <h1>Grade : C+</h1>
        
    <% } else if(totalper >=80 && totalper <=82){%>
        <h1>Grade : B-</h1>
        
    <% }else if(totalper >=83 && totalper <=86){%>
        <h1>Grade : B</h1>
        
    <% } else if(totalper >=87 && totalper <=89){%>
        <h1>Grade : B+</h1>
        
    <% } else if (totalper >=90 && totalper <=91){%>
        <h1>Grade : A-</h1>
        
   <%  } else if (totalper >=92 && totalper <=100){%>
        <h1>Grade : A</h1>
       
  <% } %>
       
    <% }else { %>
<form action ="GradeCalculator.jsp" method="Get">
<table style="width:450px" border='1'>
<tr>
  <td> Name </td>
  <td>User Marks</td>		
  <td>Total Marks</td>
</tr>
<tr>
  <td>Home work and lab</td>
  <td><input type="text" name="homework" id="hw" /></td>		
  <td><input type="text" name="homework1" id="hw1" /></td>
</tr>
<tr>
  <td>Midterm </td>
  <td><input type="text" name="midterm" id="mt" /></td>		
  <td><input type="text" name="midterm1" id="mt1" /></td>
</tr>
<tr>
   <td>Final </td>
  <td><input type="text" name="finale" id="fn" /> </td>		
  <td><input type="text" name="final1" id="fn1" /> </td>
</tr>
<tr>
   <td>Attendance </td>
  <td><input type="text" name="attendance" id="at" /> </td>		
  <td><input type="text" name="attendance1" id="at1" /> </td>
</tr>
<tr>
<td ><input type="submit" value="Calculate" /></td>
</tr>
</table>
<% } %>

</form>
</body>
</html>