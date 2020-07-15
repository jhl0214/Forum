package board.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.dao.DAO;
import board.dto.DTO;
import board.util.Pagination;

public class ListCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String sort = request.getParameter("sort");
		
		DAO dao = new DAO();
		
		List<DTO> dtos = null;
		
		if (sort == null || sort.equals("Latest")) {
			dtos = dao.listSortByLatest();
		} else if (sort.equals("Oldest")) {
			dtos = dao.listSortByOldest();
		} else if (sort.equals("Views Low to High")) {
			dtos = dao.listSortByViewsAsc();
		} else if (sort.equals("Views High to Low")) {
			dtos = dao.listSortByViewsDesc();
		}  else if (sort.equals("Name Ascending")) {
			dtos = dao.listSortByNameAsc();
		} else if (sort.equals("Name Descending")) {
			dtos = dao.listSortByNameDesc();
		} else if (sort.equals("Title Ascending")) {
			dtos = dao.listSortByTitleAsc();
		} else if (sort.equals("Title Descending")) {
			dtos = dao.listSortByTitleDesc();
		}
		
		
		int curPage = (request.getParameter("page") == null || request.getParameter("page").equals("null")) ? 1 : Integer.parseInt(request.getParameter("page"));
		int postsPerPage = (request.getParameter("postsPerPage") == null || request.getParameter("postsPerPage").equals("null")) ? 10 : Integer.parseInt(request.getParameter("postsPerPage"));
		
		int curBlock = (int) Math.ceil((double) curPage / (double) 5);
		int prevPage = curPage - 1;
		int nextPage = curPage + 1;
		int totalPosts = dtos.size();
		int totalPages = (int) Math.ceil((double) totalPosts / (double) postsPerPage);
		int totalBlocks = (int) Math.ceil((double) totalPages / (double) 5);
		int start = (curBlock - 1) * postsPerPage + 1;
		int end = (curBlock - 1) * postsPerPage + postsPerPage;
		
		
		
		Pagination pagination = new Pagination(curPage, curBlock, prevPage, nextPage, totalPosts, totalPages, totalBlocks, postsPerPage, start, end);
//		System.out.println(totalPosts + " " + curPage + " " + postsPerPage + " " + start + " " + end + " " + totalPages);
		
		if (curPage == totalPages) {
			dtos = dtos.subList(postsPerPage * (curPage - 1), totalPosts);
		} else {
			dtos = dtos.subList(postsPerPage * (curPage - 1), postsPerPage * (curPage - 1) + postsPerPage);
		}
		pagination.setStart(start);
		pagination.setEnd(end);
		
		request.setAttribute("pagination", pagination);
		request.setAttribute("list", dtos);
		request.setAttribute("sort", sort);
	}

}
