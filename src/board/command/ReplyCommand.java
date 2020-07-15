package board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.dao.DAO;

public class ReplyCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String postId = (String) request.getParameter("postId");
		String username = (String) request.getSession().getAttribute("username");
		String content = (String) request.getParameter("replyContent");

		DAO dao = new DAO();
		dao.writeReply(postId, username, content);
	}

}
