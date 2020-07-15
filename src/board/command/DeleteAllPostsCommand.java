package board.command;

import java.io.File;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.dao.DAO;
import board.dto.DTO;
import board.dto.ImageDTO;

public class DeleteAllPostsCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		DAO dao = new DAO();
		
		ArrayList<DTO> dtos = (ArrayList<DTO>) dao.list();
		
		for (DTO dto : dtos) {
			dao.delete(String.valueOf(dto.getId()));
			deleteImageAndReplyForPost(dao, String.valueOf(dto.getId()));
		}
	
	}

	private void deleteImageAndReplyForPost(DAO dao, String postId) {
		
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
