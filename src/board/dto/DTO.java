package board.dto;

import java.sql.Timestamp;

public class DTO {
	int id;
	String name;
	String title;
	String content;
	Timestamp postDate;
	int views;

	
	public DTO(int id, String name, String title, String content, Timestamp postDate, int views) {
		this.id = id;
		this.name = name;
		this.title = title;
		this.content = content;
		this.postDate = postDate;
		this.views = views;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}
	
}
