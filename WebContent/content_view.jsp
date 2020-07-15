<%@page import="board.dto.ReplyDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="board.dto.DTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
	request.setCharacterEncoding("UTF-8");
%>

<%
	// Check if user is logged in
	String username = (String) session.getAttribute("username");
	if (username == null) {
		response.sendRedirect("login.jsp");
		return;
	}
%>
    
<%
	DTO dto = (DTO) request.getAttribute("content_view");
	ArrayList<String> viewed = (ArrayList<String>) session.getAttribute("viewed");
	if (viewed == null) {
		viewed = new ArrayList<String>();
		viewed.add(String.valueOf(dto.getId()));
	} else if (!viewed.contains(String.valueOf(dto.getId()))) {
		viewed.add(String.valueOf(dto.getId()));
	}

	session.setAttribute("viewed", viewed);
%>

    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>View Post</title>
		<link href="content_view.css" rel="stylesheet">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	</head>
	<script>
		function deletePostConfirm() {
			let del = confirm("Delete this post?");
			if (del == true) {
				window.location.href = "delete.do?id=" + ${content_view.id} + "&page=" + ${param.page} + "&postsPerPage=" + ${param.postsPerPage};
			} else {
				return false;
			}
		}
		
		function deleteImageConfirm() {
			let id = $(this).attr("id");
			let imgName = $(this).siblings().attr("alt");
			console.log(imgName);
			let del = confirm("Delete this image?");
			if (del == true) {
				window.location.href = "deleteImage.do?id=" + id + "&postId=${content_view.id}&imgName=" + imgName;
			} else {
				return false;
			}
		}
		
		function checkReply() {
			let addReply = confirm("Add this reply?");
			if (addReply == true) {
				let replyLength = $("#replyContent").val().trim().length;
				if (replyLength == 0) {
					alert('Please write in the text box');
					return false;
				} else if (replyLength > 300) {
					alert('Maximum length should be <= 300');
					return false;
				}
			} else {
				return false;
			}
		}
	</script>
	<body>
		<table>
			<tr>
				<td> Post ID </td>
				<td> <input type="text" name="id" size = "10" value="${content_view.id}" readonly> </td>
			</tr>
			<tr>
				<td> Name </td>
				<td> <input type="text" name="name" size = "50" value="${content_view.name}" readonly> </td>
			</tr>
			<tr>
				<td> Title </td>
				<td> <input type="text" name="title" size = "50" value="${content_view.title}" readonly> </td>
			</tr>
			<tr>
				<td> Content </td>
				<td> <textarea name="content" rows="10" cols="50" style="resize:none" readonly>${content_view.content}</textarea> </td>
			</tr>
		</table>
		
		<div id="img-container">
			<c:forEach items="${images}" var="image">
				<div class="image">
					<img src="${pageContext.request.contextPath}/images/${image.imgName}" alt="${image.imgName}" />
					<c:if test="${image.username == username || username == 'admin'}">
						<button class="deleteButton deleteImageButton" id="${image.id}">Delete</button>
					</c:if>
					<hr>
				</div>
			</c:forEach>
		</div>

		<div id="button_container">
			<c:if test="${(content_view.name == username) || (username == 'admin')}">
				<button type=button onClick="window.location.href='modify_view.do?id=${content_view.id}&page=${param.page}&postsPerPage=${param.postsPerPage}'">Modify</button>
				<button type=button onClick=deletePostConfirm()>Delete</button>
			</c:if>
			<button type="button" onClick="window.location.href='list.do?page=${param.page}&postsPerPage=${param.postsPerPage}'">List</button>
		</div>
		
		<div id="replyContainer">
			<div id="writeReply">
				<form action="reply.do" method="post">
					<input name="postId" value="${content_view.id}" type="hidden">
					<textarea id="replyContent" name="replyContent" rows="4" cols="60" style="resize:none"></textarea>
					<input type="hidden" name="page" value="${param.page}">
					<input type="hidden" name="postsPerPage" value="${param.postsPerPage}">
					<button>Reply</button>
				</form>
			</div>
			<div id="showReply">
				<c:forEach items="${replies}" var="reply">
					<div class="reply">
						<div class="replyInfo">
							<h3>${reply.username}</h3>
							<p>${reply.content}</p>
							<p class="postDate">${reply.postDate}</p>
						</div>
						<c:if test="${(reply.username == username) || (username == 'admin')}">
							<button class="deleteButton" onclick="window.location.href='deleteReply.do?id=${reply.id}&postId=${reply.postId}&page=${param.page}&postsPerPage=${param.postsPerPage}'">Delete</button>
						</c:if>
						<hr>
					</div>
				</c:forEach>
			</div>
		</div>
		
		<script>
			$(document).ready($("form").submit(checkReply));
		</script>
		<script>
			$(document).ready($(".deleteImageButton").click(deleteImageConfirm));
		</script>
	</body>
</html>