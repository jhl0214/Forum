package board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.dao.DAO;

public class LoginCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		HttpSession session = request.getSession();
		
		DAO dao = new DAO();
		// -1 = not member, 0 = wrong password, 1 = is_member
		int res = dao.verifyLogin(username, password);
		
		if (res == 1) {
			session.setAttribute("username", username);
			session.setMaxInactiveInterval(3600);
		}
		
		session.setAttribute("loginValid", res);
	}

}
