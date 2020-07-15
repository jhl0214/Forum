package board.dto;

import java.sql.Timestamp;

public class ReplyDTO {
	
	// id is the primary key in the mvc_board_reply table
	int id;
	int postId;
	String username;
	String content;
	Timestamp postDate;
	
	public ReplyDTO(int id, int postId, String username, String content, Timestamp postDate) {
		this.id = id;
		this.postId = postId;
		this.username = username;
		this.content = content;
		this.postDate = postDate;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getPostDate() {
		return postDate;
	}
	public void setPostDate(Timestamp postDate) {
		this.postDate = postDate;
	}
	
}
