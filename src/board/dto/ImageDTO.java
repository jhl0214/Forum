package board.dto;

public class ImageDTO {
	int id;
	int postId;
	String username;
	String imgName;
	String imgPath;
	
	public ImageDTO(int id, int postId, String username, String imgName, String imgPath) {
		this.id = id;
		this.postId = postId;
		this.username = username;
		this.imgName = imgName;
		this.imgPath = imgPath;
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

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	
}
