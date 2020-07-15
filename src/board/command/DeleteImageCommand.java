package board.command;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.dao.DAO;

public class DeleteImageCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String imgName = request.getParameter("imgName");

		DAO dao = new DAO();
		dao.deleteImageById(id);
		
		// Remove image file from the folder
		String path = "C:\\Users\\Juhyeon\\Desktop\\Java coding\\Board\\images\\";
		File f = new File(path + imgName);
//		System.out.println(f.getPath());
		try {
			f.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
