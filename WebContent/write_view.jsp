<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
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
		<title>Write</title>
		<link href="write_view.css" rel="stylesheet">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	</head>
	<body>
		<form action="write.do" method="post" enctype="multipart/form-data">
			<table>
				<tr>
					<td> Name </td>
					<td> <input type="text" name="name" size = "50" value="${username}" readonly> </td>
				</tr>
				<tr>
					<td> Title </td>
					<td> <input type="text" name="title" size = "50"> </td>
				</tr>
				<tr>
					<td> Content </td>
					<td> <textarea name="content" rows="10" cols="50" style="resize:none"></textarea> </td>
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
			<div id="button_container">
				<button id="submit" type="submit" style="margin-right: 30px">Write</button> 
				<button type="button" onClick="window.location.href='list.do'">Cancel</button>
			</div>
		</form>
		
		<script>
		
			function checkFileExtensions() {
				let acceptedFormat = ["gif", "jpg", "jpeg", "png"];
				let acceptFormat = true;
				$('input[type=file]').each(function() {
					let extension = $(this).val().split(".")[1];

					if (!acceptedFormat.includes(extension.toLowerCase())) {
						alert("You can only upload .gif, .jpg, .jpeg, .png");
						acceptFormat = false;
						return false;
					}
				});
				
				return acceptFormat;
			}
		
			$("form").submit(function(e) {

				let writePost = confirm("Write this post?");
				if (!writePost) {
					return false;
				}
				
				let empty = 0;
				$('input[type=text], textarea').each(function() {
					if ($(this).val() == "") {
						empty += 1;
					}
				});
				if (empty != 0) {
					alert("Please fill in the blanks");
					return false;
				}
				
				let acceptFormats = checkFileExtensions();
				
				if (!acceptFormats) {
					return false;
				}
			});
		</script>
	</body>
</html>