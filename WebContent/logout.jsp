<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Log Out</title>
	</head>
	<body>
		<%
			session.invalidate();
			response.sendRedirect("login.jsp");
			System.out.println("Logout Successful.");
			System.out.println("****************************************");
		%>
	</body>
</html>