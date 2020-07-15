<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String username = (String) session.getAttribute("username");

	if (username != null) {
		response.sendRedirect("list.do");
		return;
	}
	
	int res = Integer.MIN_VALUE;
	Object obj = session.getAttribute("loginValid");
	
	res = (obj != null) ? (int) obj: res;
%>    

<%
	request.setCharacterEncoding("UTF-8");
%>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Log In</title>
		<link rel="stylesheet" href="login.css">
	</head>
	<body>
		<% 
			if (res != Integer.MIN_VALUE) {
				if (res == -1) {
					out.println("<h2 style='color: red'>User doesn't exist!</h2>");
				} else if (res == 0) {
					out.println("<h2 style='color: red'>Wrong password!</h2>");
				}
			}
		%>
		<form action="verifyLogin.do" method="post">
			<input type="text" name="username" id="username" placeholder="Username" /><br />
			<input type="password" name="password" id="password" placeholder="Password" /><br />
			<button type="submit">Log In</button>
			<button type="button" onclick="window.location.href='signup.jsp'">Sign Up</button>
		</form>
	</body>
</html>