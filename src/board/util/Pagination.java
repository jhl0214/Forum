package board.util;

public class Pagination {
	int curPage;
	int curBlock;
	int prevPage;
	int nextPage;
	int totalPosts;
	int totalPages;
	int totalBlocks;
	int postsPerPage;
	int start;
	int end;
	
	public Pagination(int curPage, int curBlock, int prevPage, int nextPage, int totalPosts, int totalPages, int totalBlocks,
			int postsPerPage, int start, int end) {
		this.curPage = curPage;
		this.curBlock = curBlock;
		this.prevPage = prevPage;
		this.nextPage = nextPage;
		this.totalPosts = totalPosts;
		this.totalPages = totalPages;
		this.totalBlocks = totalBlocks;
		this.postsPerPage = postsPerPage;
		this.start = start;
		this.end = end;
	}
	
	public Pagination() {
		// TODO Auto-generated constructor stub
	}

	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getCurBlock() {
		return curBlock;
	}
	public void setCurBlock(int curBlock) {
		this.curBlock = curBlock;
	}
	public int getPrevPage() {
		return prevPage;
	}
	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public int getTotalPosts() {
		return totalPosts;
	}
	public void setTotalPosts(int totalPosts) {
		this.totalPosts = totalPosts;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getTotalBlocks() {
		return totalBlocks;
	}
	public void setTotalBlocks(int totalBlocks) {
		this.totalBlocks = totalBlocks;
	}
	public int getPostsPerPage() {
		return postsPerPage;
	}
	public void setPostsPerPage(int postsPerPage) {
		this.postsPerPage = postsPerPage;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		if (start < 0) {
			this.start = 1;
		}
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		if (end > totalPages) {
			this.end = totalPages;
			return;
		}
		this.end = end;
	}
	
}
