package board.frontcontroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.command.Command;
import board.command.ContentCommand;
import board.command.DeleteAllPostsCommand;
import board.command.DeleteCommand;
import board.command.DeleteImageCommand;
import board.command.DeleteReplyCommand;
import board.command.ListCommand;
import board.command.LoginCommand;
import board.command.ModifyCommand;
import board.command.ReplyCommand;
import board.command.SignupCommand;
import board.command.WriteCommand;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}
	
	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String viewPage = null;
		Command command = null;
		
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String com = uri.substring(contextPath.length());
		
		if (com.equals("/write_view.do")) {
			viewPage = "write_view.jsp";
		} else if (com.equals("/write.do")) {
			command = new WriteCommand();
			command.execute(request, response);
			viewPage = "list.do?page=1&postsPerPage=10";
			response.sendRedirect(viewPage);
			return;
		} else if (com.equals("/list.do")) {
			command = new ListCommand();
			command.execute(request, response);
			viewPage = "list.jsp";
		} else if (com.equals("/content_view.do")) {
			command = new ContentCommand();
			command.execute(request, response);
			viewPage = "content_view.jsp";
		} else if (com.equals("/modify_view.do")) {
			command = new ContentCommand();
			command.execute(request, response);
			viewPage = "modify_view.jsp";
		} else if (com.equals("/modify.do")) {
			command = new ModifyCommand();
			command.execute(request, response);
			viewPage = "content_view.do?id=" + request.getAttribute("id") + "&page=" + request.getAttribute("page") + "&postsPerPage=" + request.getAttribute("postsPerPage");
			response.sendRedirect(viewPage);
			return;
		} else if (com.equals("/delete.do")) {
			command = new DeleteCommand();
			command.execute(request, response);
			viewPage = "list.do?page=" + request.getParameter("page") + "&postsPerPage=" + request.getParameter("postsPerPage");
			response.sendRedirect(viewPage);
			return;
		} else if (com.equals("/reply.do")) {
			command = new ReplyCommand();
			command.execute(request, response);
			viewPage = "content_view.do?id=" + request.getParameter("postId") + "&page=" + request.getParameter("page") + "&postsPerPage=" + request.getParameter("postsPerPage");
			response.sendRedirect(viewPage);
			return;
		} else if (com.equals("/deleteReply.do")) {
			command = new DeleteReplyCommand();
			command.execute(request, response);
			viewPage = "content_view.do?id=" + request.getParameter("postId") + "&page=" + request.getParameter("page") + "&postsPerPage=" + request.getParameter("postsPerPage");
			response.sendRedirect(viewPage);
			return;
		} else if (com.equals("/verifyLogin.do")) {
			command = new LoginCommand();
			command.execute(request, response);
			// -1 = not member, 0 = wrong password, 1 = is_member
			int res = (int) request.getSession().getAttribute("loginValid");
			if (res == 1) {
				viewPage = "list.do?page=1&postsPerPage=10";
				response.sendRedirect(viewPage);
				return;
			} else {
				viewPage = "login.jsp";
			}
		} else if (com.equals("/signup.do")) {
			command = new SignupCommand();
			command.execute(request, response);
			String signUpSuccess = (String) request.getAttribute("signUp");
			if (signUpSuccess.equals("success")) {
				viewPage = "login.jsp";
				response.sendRedirect(viewPage);
				return;
			} else {
				viewPage = "signup.jsp";
			}
		} else if (com.equals("/deleteImage.do")) {
			command = new DeleteImageCommand();
			command.execute(request, response);
			viewPage = "content_view.do?id=" + request.getParameter("postId");
			response.sendRedirect(viewPage);
			return;
		} else if (com.equals("/deleteAll.do")) {
			command = new DeleteAllPostsCommand();
			command.execute(request, response);
			viewPage = "list.do?page=1&postsPerPage=10";
			response.sendRedirect(viewPage);
			return;
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		
	}

}
