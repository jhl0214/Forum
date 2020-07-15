package board.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.dao.DAO;
import board.dto.DTO;
import board.dto.ImageDTO;
import board.dto.ReplyDTO;

public class ContentCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		ArrayList<String> viewed = (ArrayList<String>) request.getSession().getAttribute("viewed");
		DAO dao = new DAO();
		DTO dto = dao.contentView(id);
		ArrayList<ReplyDTO> replyDtos = dao.getReplies(id);
		ArrayList<ImageDTO> imgDtos = dao.getImagesForPost(id);
		
		String username = (String) request.getSession().getAttribute("username");
		
		// If content is not already viewed by this user during current session, increase a hit
		if (viewed == null && username != null && !username.equals(dto.getName())) {
			viewed = new ArrayList<String>();
			viewed.add(String.valueOf(dto.getId()));
			dao.increaseView(id);
		} else if (viewed != null && username != null && !username.equals(dto.getName()) && !viewed.contains(String.valueOf(dto.getId()))) {
			viewed.add(String.valueOf(dto.getId()));
			dao.increaseView(id);
		}
		
		request.getSession().setAttribute("viewed", viewed);
		request.setAttribute("content_view", dto);
		request.setAttribute("replies", replyDtos);
		request.setAttribute("images", imgDtos);
	}

}
