<%@page import="java.util.List"%>
<%@page import="board.dto.DTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
	// Check if user is logged in
	String username = (String) session.getAttribute("username");
	if (username == null) {
		response.sendRedirect("login.jsp");
		return;
	}
%>

<%
	List<DTO> list = (List<DTO>) request.getAttribute("list");
	ArrayList<Integer> postIDs = new ArrayList<Integer>();
	for (DTO dto : list) {
		postIDs.add(dto.getId());
	}
	session.setAttribute("deletePostIDs", postIDs);
%>

<%
	request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Board Posts</title>
		<link rel="stylesheet" href="list.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	</head>
	<script>
		function confirmDeleteAll() {
			let deleteAll = confirm("Delete all the posts?");
			if (!deleteAll) {
				return false;
			} else {
				window.location.href = "deleteAll.do";
			}
		}
		
		$(document).ready(function() {
			$('#sort').change(function() {
				window.location.href = "list.do?sort=" + $(this).val(); 
			});
		});
		
		$(document).ready(function() {
			$('option').each(function() {
				if ($(this).val() == "${sort}") {
					$(this).attr("selected", "selected");
				}
			})
		})
	</script>
	<body>
		<h1>BOARD</h1>
		<h2>Welcome ${username}!</h2>
		<c:if test="${username == 'admin'}">
			<button type="button" onclick=confirmDeleteAll()>Delete All</button>
		</c:if>
		
		<div class="container"><button type="button" onclick="window.location.href='logout.jsp'">Log Out</button></div>
		<div id="sort-select-container">
			<select name='sort' id='sort'>
			  <option>Latest</option>
			  <option>Oldest</option>
			  <option>Views Low to High</option>
			  <option>Views High to Low</option>
			  <option>Name Ascending</option>
			  <option>Name Descending</option>
			  <option>Title Ascending</option>
			  <option>Title Descending</option>
			</select>
		</div>
		<table>
			<tr style="background-color: '#87ceeb'">
				<th>Post ID</th>
				<th>Name</th>
				<th>Title</th>
				<th>Date</th>
				<th>Views</th>
			</tr>
			<c:forEach items="${list}" var="dto">
				<tr>
					<td>${dto.id}</td>
					<td>${dto.name}</td>
					<td><a href="content_view.do?id=${dto.id}&page=${pagination.getCurPage()}&postsPerPage=${pagination.getPostsPerPage()}">${dto.title}</a></td>
					<td>${dto.postDate}</td>
					<td>${dto.views}</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="5"><a class="btn" href="write_view.do">Create a post</a></td>
			</tr>
		</table>
			<div id="page-btn">
				<c:if test="${pagination.getCurBlock() > 1}">
						<a href="list.do?page=${pagination.getStart() - 1}&postsPerPage=${pagination.getPostsPerPage()}&sort=${sort}">Prev</a>
					</c:if>
				<c:forEach begin="${pagination.getStart()}" end="${pagination.getEnd()}" var="idx">
					<c:choose>
						<c:when test="${pagination.getCurPage() == idx}">
							<a class="page-selected" href="list.do?page=${idx}&postsPerPage=${pagination.getPostsPerPage()}&sort=${sort}">${idx}</a>
						</c:when>
						<c:otherwise>
							<a href="list.do?page=${idx}&postsPerPage=${pagination.getPostsPerPage()}&sort=${sort}">${idx}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>		
				<c:if test="${pagination.getCurBlock() != pagination.getTotalBlocks()}">
					<a href="list.do?page=${pagination.getEnd() + 1}&postsPerPage=${pagination.getPostsPerPage()}&sort=${sort}">Next</a>
				</c:if>
			</div>
	</body>
	
	<script>
		$("#page-btn a").click(function(e) {
			$("#page-btn a").each(function() {
				if (this == e.target) {
					$(this).addClass("page-selected");
				} else {
					$(this).removeClass("page-selected");
				}
			});
		});
	</script>
</html>