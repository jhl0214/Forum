package board.command;

import java.io.File;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.dao.DAO;
import board.dto.ImageDTO;

public class DeleteCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String postId = request.getParameter("id");
		DAO dao = new DAO();
		dao.delete(postId);
		
		// Remove image file from the folder
		String path = "C:\\Users\\Juhyeon\\Desktop\\Java coding\\Board\\images\\";
		
		ArrayList<ImageDTO> images = dao.getImagesForPost(postId);
		for (ImageDTO image : images) {
			File f = new File(path + image.getImgName());
//			System.out.println(f.getPath());
			try {
				f.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// Delete images from MVC_BOARD_IMAGES
		dao.deleteImageByPostId(postId);
		dao.deleteReplyByPostId(postId);
	}

}
