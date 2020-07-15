<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String signUpSuccess = null;
	signUpSuccess = (String) request.getAttribute("signUp");
%>

<%
	request.setCharacterEncoding("UTF-8");
%>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Sign Up</title>
		<link rel="stylesheet" href="signup.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	</head>
	<body>
		<% 
			if (signUpSuccess != null && signUpSuccess.equals("fail")) {
				out.println("<h2 style='color: red'>Try different username or try again later.</h2>");
			}
		%>
		<form action="signup.do" method="post">
			<input type="text" name="username" id="username" placeholder="Username" /><br />
			<input type="password" name="password" id="password" placeholder="Password" /><br />
			<button type="submit" onclick=checkInputs()>Sign Up</button>
		</form>
	</body>
	<script>
	$("form").submit(function checkInputs() {
		let usernameLength = $("#username").val().trim().length;
		let passwordLength = $("#password").val().trim().length;
		console.log(usernameLength);
		if (usernameLength == 0) {
			alert('Please enter a username in the textbox.');
			return false;
		} else if (passwordLength == 0) {
			alert('Please enter a password in the textbox.');
			return false;
		}
	});
	</script>
</html>