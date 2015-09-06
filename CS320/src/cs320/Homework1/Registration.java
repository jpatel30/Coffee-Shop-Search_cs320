package cs320.Homework1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Homework1/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String pattern = "^[A-Za-z0-9._-]+@[A-Za-z0-9.-]+\\.[a-zA-Z]{2,}$";
		
		String fName = null;					boolean fNameValid = false;
		String lName = null;					boolean lNameValid = false;
		String email = null;					boolean emailValid =false;
		String confirmEmail = null;				boolean confirmEmailValid = false;
		String password = null;					boolean passwordValid = false;
		String confirmPassword = null;			boolean confirmPasswordValid = false;
		String street1 = null;					boolean street1Valid = false;
		String street2 = null;					boolean cityValid = false;
		String city = null;						boolean stateValid = false;
		String state = null;					boolean zipcodeValid = false;
		String zipcode = null;					boolean areaValid = false;
		String areaCode = null;					boolean prefixValid = false;
		String prefix = null;					boolean suffixValid = false;
		String suffix = null;					boolean hasErrors = true;
		String errorMessage = null;				
		String errorMessage1 = null;
		String errorMessage2 = null;
		String errorMessage3 = null;
		
		out.println("<html><head><title>Registration</title></head>");
		out.println("<body>");
		out.println("<h1> Registration Form</h1>");
		out.println("<form action=\"Registration\" method=\"post\">");
		out.println("<table><tr>");
		
		fName = request.getParameter("fname");
		if(fName != null){
			if(fName.length() < 1){
				errorMessage = "First name should be more than 1 characters";
			} else if (!fName.matches("[a-zA-Z]+[ a-zA-Z ]*")){
				errorMessage = "First name should have only alphabets and spaces";
			} else{
				fNameValid = true;
			}
		}
		out.println("<td>First Name : </td><td><input type = \"text\" name=\"fname\" tabindex=\"1\" autofocus");
		if(fNameValid){
			out.println(" value = \""+ fName +"\"></td>");
			hasErrors = false;
		} else if(fName != null) {
			out.println(" style=\"border-color: red\"></td>");
			out.println("<td><h5 style= \"color: red\">" +errorMessage + "</h5></td>");
		}
		out.println("</tr><tr>");
		
		
		lName = request.getParameter("lname");
		if(lName != null){
			if(lName.length() < 1){
				errorMessage = "Last name should be more than 1 characters";
			} else if (!lName.matches("[a-zA-Z]+[ a-zA-Z ]*")){
				errorMessage = "Last name should have only alphabets and spaces";
			} else {
				lNameValid = true;
			}
		}
		out.println("<td>Last Name : </td><td><input type = \"text\" name=\"lname\" tabindex=\"2\"");
		if(lNameValid){
			out.println(" value = \""+ lName +"\"></td>");
			if(!hasErrors)
				hasErrors = false;
		} else if(lName != null) {
			out.println(" style=\"border-color: red\"></td>");
			out.println("<td><h5 style= \"color: red\">" +errorMessage + "</h5></td>");
		}
		out.println("</tr><tr>");
		
		
		email = request.getParameter("email");
		if(email != null){
			if(!email.matches(pattern)){
				errorMessage = "Please input a valid email";
			} else {
				emailValid = true;
				}
		}
		out.println("<td>E-mail : </td><td><input type = \"text\" name=\"email\" tabindex=\"3\"");
		if(emailValid){
			out.println(" value = \""+ email +"\"></td>");
			if(!hasErrors)
				hasErrors = false;
		} else if(email != null) {
			out.println(" style=\"border-color: red\"></td>");
			out.println("<td><h5 style= \"color: red\">" +errorMessage + "</h5></td>");
		}
		out.println("</tr><tr>");
		
		
		confirmEmail = request.getParameter("confirmemail");
		if(confirmEmail != null){
			if(!confirmEmail.matches(pattern)){
				errorMessage = "Please input a valid email";
			} else if(!email.equalsIgnoreCase(confirmEmail)) {
				errorMessage = "Email and confirm email does not match";
			} else {
				confirmEmailValid = true;
			}
		}
		out.println("<td>Confirm E-mail : </td><td><input type = \"text\" name=\"confirmemail\" tabindex=\"3\"");
		if(confirmEmailValid){
			out.println(" value = \""+ confirmEmail +"\"></td>");
			if(!hasErrors)
				hasErrors = false;
		} else if(confirmEmail != null) {
			out.println(" value = \""+ confirmEmail +"\" style=\"border-color: red\"></td>");
			out.println("<td><h5 style= \"color: red\">" +errorMessage + "</h5></td>");
		}
		out.println("</tr><tr>");
		
		password = request.getParameter("password");
		if(password != null){
			if (!password.matches("^(?=.{4,})(?=.*[a-zA-Z])(?=.*[!@#$%^&*]).*$")){
				errorMessage = "Password must have atleast 4 charcters containing 1 alphabet, 1 number and 1 special character from [!@#$%^&*]";
			} else {
				passwordValid = true;
			}
		}
		out.println("<td>Password : </td><td><input type = \"password\" name=\"password\" tabindex=\"4\"");
		if(!passwordValid && password != null){
			out.println(" style=\"border-color: red\"></td>");
			out.println("<td><h5 style= \"color: red\">" +errorMessage+ "</h5></td>");
			hasErrors = true;
		}
		out.println("</tr><tr>");
		
		confirmPassword = request.getParameter("confirmpassword");
		if(confirmPassword != null){
			if(!confirmPassword.matches("^(?=.{4,})(?=.*[a-zA-Z])(?=.*[!@#$%^&*]).*$")){
				errorMessage = "Password must have atleast 4 charcters containing 1 alphabet, 1 number and 1 special character from [!@#$%^&*]";
			} else if(!password.equals(confirmPassword)) {
				errorMessage = "Password and confirm password does not match";
			} else {
				confirmPasswordValid = true;
			}
		}
		
		out.println("<td>Confirm Password : </td><td><input type = \"password\" name=\"confirmpassword\" tabindex=\"5\"");
		if(!confirmPasswordValid && confirmPassword!=null){
			out.println(" style=\"border-color: red\"></td>");
			out.println("<td><h5 style= \"color: red\">" +errorMessage+ "</h5></td>");
			hasErrors = true;
		}
		out.println("</tr><tr>");
		
		street1 = request.getParameter("street1");
		if(street1 != null){
			if(street1 == ""){
				errorMessage = "Street 1 cannot be empty";
			} else {
				street1Valid = true;
			}
		}
		out.println("<td>Street 1 : </td><td><input type = \"text\" name=\"street1\" tabindex=\"6\"");
		if(street1Valid){
			out.println(" value = \""+ street1 +"\"></td>");
			if(!hasErrors)
				hasErrors = false;
		} else if(street1 != null) {
			out.println(" style=\"border-color: red\"></td>");
			out.println("<td><h5 style= \"color: red\">" +errorMessage + "</h5></td>");
		}
		out.println("</tr><tr>");
		
		street2 = request.getParameter("street2");
		out.println("<td>Street 2 : </td><td><input type = \"text\" name=\"street2\" tabindex=\"7\"");
		if(street2 !=null){
			out.println(" value = \""+ street2 +"\"></td>");
		}
		out.println("</tr><tr>");
		
		city = request.getParameter("city");
		if(city != null){
			if(city == ""){
				errorMessage = "City cannot be empty";
			} else {
				cityValid = true;
			}
		}
		out.println("<td>City : </td><td><input type = \"text\" name=\"city\" tabindex=\"8\"");
		if(cityValid){
			out.println(" value = \""+ city +"\"></td>");
			if(!hasErrors)
				hasErrors = false;
		} else if(city !=null) {
			out.println(" style=\"border-color: red\"></td>");
			out.println("<td><h5 style= \"color: red\">" +errorMessage + "</h5></td>");
		}
		out.println("</tr><tr>");
		
		state = request.getParameter("state");	
		if(state != null){
			if(state.length() < 2){
				errorMessage = "State should be  2 characters";
			} else if(!state.matches("[a-zA-z]+")){
				errorMessage = "Only alphabets are allowed";
			}
			else {
				stateValid = true;
			}
		}
		out.println("<td>State : </td><td><input type = \"text\" name=\"state\" maxlength =\"2\"tabindex=\"9\"");
		if(stateValid){
			out.println(" value = \""+ state +"\"></td>");
			if(!hasErrors)
				hasErrors = false;
			
		} else if(state !=null) {
			out.println(" style=\"border-color: red\"></td>");
			out.println("<td><h5 style= \"color: red\">" +errorMessage + "</h5></td>");
		}
		out.println("</tr><tr>");
		
		zipcode = request.getParameter("zipcode");
		if(zipcode != null){
			if(zipcode.length() < 5){
				errorMessage = "Zip code should be upto 5 numbers";
			} else if (!zipcode.matches("[0-9]+")){
				errorMessage = "Only numerics are allowed";
			} else {
				zipcodeValid = true;
			}
		}
		out.println("<td>Zip Code : </td><td><input type = \"text\" name=\"zipcode\" maxlength =\"5\" tabindex=\"10\"");
		if(zipcodeValid){
			out.println(" value = \""+ zipcode +"\"></td>");
			if(!hasErrors)
				hasErrors = false;
		} else if(zipcode != null) {
			out.println(" style=\"border-color: red\"></td>");
			out.println("<td><h5 style= \"color: red\">" +errorMessage + "</h5></td>");
		}
		out.println("</tr><tr rowspan=2>");
		areaCode =request.getParameter("areaCode");		
		if(areaCode != null){
			if(areaCode.length() < 3){
				errorMessage1 = "Area Code should be of 3 digits";
			} else {
				areaValid = true;
			}
		}
		out.println("<td>Telephone : </td>");
		out.println("<td><input type = \"text\" name=\"areaCode\" maxlength=\"3\" size=\"3\" tabindex=\"11\"");
		if(areaValid){
			out.println(" value = \""+ areaCode +"\"></td>");
		} else if(areaCode != null){
			out.println(" style=\"border-color: red\"></td>");
		}
		
		prefix = request.getParameter("prefix");		
		if(prefix != null){
			if(prefix.length() < 3){
				errorMessage2 = "Prefix should be of 3 digits";
			} else {
				prefixValid = true;
			}
		}
		out.println("<td>&nbsp;<input type = \"text\" name=\"prefix\" maxlength=\"3\" size=\"3\" tabindex=\"12\"");
		if(prefixValid){
			out.println(" value = \""+ prefix +"\"></td>");
		} else if(prefix != null){
			out.println(" style=\"border-color: red\"></td>");
		}	
		suffix = request.getParameter("suffix");		
		if(suffix != null){
			if(suffix.length() < 4){
				errorMessage3 = "Suffix should be of 4 digits";
			} else {
				suffixValid = true;
			}
		}
		out.println("<td>&nbsp;<input type = \"text\" name=\"suffix\" maxlength=\"4\" size=\"4\" tabindex=\"13\"");
		if(suffixValid){
			out.println(" value = \""+ suffix +"\"></td>");
		}
		if(areaValid && prefixValid && suffixValid){
			if(!hasErrors){
				hasErrors = false;
			}	
		} else if((suffix != null && prefix != null && areaCode != null)) {
			out.println(" style=\"border-color: red\"></td>");
			if(!areaValid){
				errorMessage = errorMessage1;
			} else if(!prefixValid){
				errorMessage = errorMessage2;
			} else {
				errorMessage = errorMessage3;
			}
				out.println("<td><h5 style= \"color: red\">" +errorMessage + "</h5></td>");
				hasErrors = true;
		}
		out.println("</tr><tr>");
		out.println("<td><input type = \"submit\" value = \"Registration\" tabindex=\"14\"></td>");
		out.println("</tr></table></form>");
		out.println("</body></html>");
		System.out.println(hasErrors);
		
		if(!hasErrors){
			response.sendRedirect("../lab2/Login");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
