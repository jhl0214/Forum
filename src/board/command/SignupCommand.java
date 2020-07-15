package board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.dao.DAO;

public class SignupCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		DAO dao = new DAO();
		boolean res = dao.signUp(username, password);
		
		if (res) {
			request.setAttribute("signUp", "success");
		} else {
			request.setAttribute("signUp", "fail");
		}
		
	}

}
