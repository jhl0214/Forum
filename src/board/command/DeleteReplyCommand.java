package board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.dao.DAO;

public class DeleteReplyCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// primary key id in oracle from table mvc_board_reply
		String id = (String) request.getParameter("id");
		String postId = (String) request.getParameter("postId");
		
		DAO dao = new DAO();
		dao.deleteReply(id);
		
		request.setAttribute("postId", postId);
	}

}
