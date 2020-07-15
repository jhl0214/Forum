<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<%
	String username = (String) session.getAttribute("username");
	if (username == null) {
		response.sendRedirect("login.jsp");
	}
%>    

<%
	request.setCharacterEncoding("UTF-8");
%>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>View Post</title>
		<link href="modify_view.css" rel="stylesheet">
		<link href='https://fonts.googleapis.com/css?family=Cabin' rel='stylesheet'>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	</head>
	<body>
		<form action="modify.do" method="post" enctype="multipart/form-data">
			<table>
				<tr>
					<td> Post ID </td>
					<td> <input type="text" name="id" size = "50" value="${content_view.id}" readonly> </td>
				</tr>
				<tr>
					<td> Name </td>
					<td> <input type="text" name="name" size = "50" value="${content_view.name}" readonly> </td>
				</tr>
				<tr>
					<td> Title </td>
					<td> <input type="text" name="title" size = "50" value="${content_view.title}"> </td>
				</tr>
				<tr>
					<td> Content </td>
					<td> <textarea name="content" rows="10" cols="50" style="resize:none">${content_view.content}</textarea> </td>
				</tr>
				<tr>
					<td> Image 1: </td>
					<td> <input type="file" name="fileName" size = "50" multiple="multiple" accept="image/gif, image/jpg, image/jpeg, image/png"> </td>
				</tr>
				<tr>
					<td> Image 2: </td>
					<td> <input type="file" name="fileName2" size = "50" multiple="multiple" accept="image/gif, image/jpg, image/jpeg, image/png"> </td>
				</tr>
				<tr>
					<td> Image 3: </td>
					<td> <input type="file" name="fileName3" size = "50" multiple="multiple" accept="image/gif, image/jpg, image/jpeg, image/png"> </td>
				</tr>
			</table>
			<input type="hidden" name="page" value="${param.page}">
			<input type="hidden" name="postsPerPage" value="${param.postsPerPage}">
			
			<div id="button_container">
				<button type="submit">Confirm</button>
				<button type="button" onClick="window.location.href='content_view.do?id=${content_view.id}&page=${param.page}&postsPerPage=${param.postsPerPage}'">Cancel</button>
				<button type="button" onClick="window.location.href='list.do?page=${param.page}&postsPerPage=${param.postsPerPage}'">List</button>
			</div>
		</form>
		
		<script>
			$("form").submit(function() {
				let empty = 0;
				$('input[type=text], textarea').each(function() {
					if ($(this).val().trim() == "") {
						empty += 1;
					}
				});
				if (empty != 0) {
					alert("Please fill in the blanks");
					return false;
				}
			});
		</script>
	</body>
</html>