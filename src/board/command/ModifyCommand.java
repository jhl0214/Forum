package board.command;

import java.io.File;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.dao.DAO;

public class ModifyCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		String path = "C:\\Users\\Juhyeon\\Desktop\\Java coding\\Board\\images";

		// If the path doesn't exist, create directory
		File saveDir = new File(path);
		if (!saveDir.exists()) {
			saveDir.mkdirs();
		}
		
		int size = 1024 * 1024 * 20; // 20MB;
		String file = "";
		String oriFile = "";
		
		try {
			MultipartRequest multi = new MultipartRequest(request, path, size, "UTF-8", new DefaultFileRenamePolicy());
			
			String id = multi.getParameter("id");
			String name = multi.getParameter("name");
			String title = multi.getParameter("title");
			String content = multi.getParameter("content");
			String page = multi.getParameter("page");
			String postsPerPage = multi.getParameter("postsPerPage");
			
			request.setAttribute("id", id);
			request.setAttribute("page", page);
			request.setAttribute("postsPerPage", postsPerPage);
			
			DAO dao = new DAO();
			dao.modify(id, name, title, content);
			// 데이터베이스에 post 입력
			
			// Form 안에서 <input type="file" name="file"> name속성 값을 불러옴.
			// 여러가지 파일 없로드 시, 모든 name속성 값들을 불러옴.
			Enumeration files = multi.getFileNames();
			
			// 파일 이름, 경로, post ID를 데이터베이스에 입력
			while (files.hasMoreElements()) {
				// input type="file" names.
				// <input type="file" name="file1"> then str = file1.
				String str = (String) files.nextElement();
				
				// 파일 저장 이름. 이미 같은 파일 이름이 있을 경우 file 변수는 뒤에 숫자가 붙거나 함.
				file = multi.getFilesystemName(str);
				if (file != null)
					dao.uploadImage(Integer.parseInt(id), name, file, path + "\\" + file);
				// 파일의 원본 명.
				oriFile = multi.getOriginalFileName(str);
			}

		} catch (Exception e) {
			System.out.println("Error while creating a post.");
			e.printStackTrace();
		}
	}

}
