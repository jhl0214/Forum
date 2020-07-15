package board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import board.dto.DTO;
import board.dto.ImageDTO;
import board.dto.MemberDTO;
import board.dto.ReplyDTO;

public class DAO {
	
	private static int WRONG_PASSWORD = 0;
	private static int NOT_MEMBER = -1;
	private static int IS_MEMBER = 1;
	
	DataSource dataSource;
	
	public DAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int write(String name, String title, String content) {
		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		ResultSet rs = null;
		int i = 0;
		int nextVal = -1;
		
		try {
			con = dataSource.getConnection();
			
			// Get mvc_board_seq.nextval
			String nextValSql = "SELECT MVC_BOARD_SEQ.NEXTVAL FROM DUAL";
			ps = con.prepareStatement(nextValSql);
			nextVal = -1;
			rs = ps.executeQuery();
			if (rs.next()) {
				nextVal = rs.getInt(1);
			} else {
				System.out.println("Could not get nextValSql");
				throw new Exception();
			}
			
			String sql = "INSERT INTO MVC_BOARD (id, name, title, content, views) VALUES(?, ?, ?, ?, 0)";
			ps2 = con.prepareStatement(sql);
			ps2.setInt(1, nextVal);
			ps2.setString(2, name);
			ps2.setString(3, title);
			ps2.setString(4, content);
			
			i = ps2.executeUpdate();
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) con.close();
				if (ps != null) ps.close();
				if (ps2 != null) ps2.close();
				if (rs != null) rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		if (i > 0 && nextVal > 0) {
			System.out.println("Successfully created a post.");
			System.out.println("****************************************");
		} else {
			System.out.println("Error while creating a post.");
			System.out.println("****************************************");
		}
		
		return nextVal;
	}
	
	public ArrayList<DTO> list() {
		ArrayList<DTO> dtos = new ArrayList<DTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			String sql = "SELECT ID, NAME, TITLE, CONTENT, POSTDATE AS \"DATE\", VIEWS FROM MVC_BOARD ORDER BY ID DESC";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Timestamp date = rs.getTimestamp("date");
				int views = rs.getInt("views");
				
				DTO dto = new DTO(id, name, title, content, date, views);
				dtos.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) con.close();
				if (ps != null) ps.close();
				if (rs != null) rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return dtos;
	}
	
	public ArrayList<DTO> listSortByOldest() {
		ArrayList<DTO> dtos = new ArrayList<DTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			String sql = "SELECT ID, NAME, TITLE, CONTENT, POSTDATE AS \"DATE\", VIEWS FROM MVC_BOARD ORDER BY ID ASC";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Timestamp date = rs.getTimestamp("date");
				int views = rs.getInt("views");
				
				DTO dto = new DTO(id, name, title, content, date, views);
				dtos.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) con.close();
				if (ps != null) ps.close();
				if (rs != null) rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return dtos;
	}
	
	public ArrayList<DTO> listSortByLatest() {
		ArrayList<DTO> dtos = new ArrayList<DTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			String sql = "SELECT ID, NAME, TITLE, CONTENT, POSTDATE AS \"DATE\", VIEWS FROM MVC_BOARD ORDER BY ID DESC";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Timestamp date = rs.getTimestamp("date");
				int views = rs.getInt("views");
				
				DTO dto = new DTO(id, name, title, content, date, views);
				dtos.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) con.close();
				if (ps != null) ps.close();
				if (rs != null) rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return dtos;
	}
	
	public ArrayList<DTO> listSortByViewsAsc() {
		ArrayList<DTO> dtos = new ArrayList<DTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			String sql = "SELECT ID, NAME, TITLE, CONTENT, POSTDATE AS \"DATE\", VIEWS FROM MVC_BOARD ORDER BY VIEWS ASC, ID ASC";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Timestamp date = rs.getTimestamp("date");
				int views = rs.getInt("views");
				
				DTO dto = new DTO(id, name, title, content, date, views);
				dtos.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) con.close();
				if (ps != null) ps.close();
				if (rs != null) rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return dtos;
	}
	
	public ArrayList<DTO> listSortByViewsDesc() {
		ArrayList<DTO> dtos = new ArrayList<DTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			String sql = "SELECT ID, NAME, TITLE, CONTENT, POSTDATE AS \"DATE\", VIEWS FROM MVC_BOARD ORDER BY VIEWS DESC, ID ASC";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Timestamp date = rs.getTimestamp("date");
				int views = rs.getInt("views");
				
				DTO dto = new DTO(id, name, title, content, date, views);
				dtos.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) con.close();
				if (ps != null) ps.close();
				if (rs != null) rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return dtos;
	}
	
	public ArrayList<DTO> listSortByNameAsc() {
		ArrayList<DTO> dtos = new ArrayList<DTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			String sql = "SELECT ID, NAME, TITLE, CONTENT, POSTDATE AS \"DATE\", VIEWS FROM MVC_BOARD ORDER BY NAME ASC, ID ASC";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Timestamp date = rs.getTimestamp("date");
				int views = rs.getInt("views");
				
				DTO dto = new DTO(id, name, title, content, date, views);
				dtos.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) con.close();
				if (ps != null) ps.close();
				if (rs != null) rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return dtos;
	}
	
	public ArrayList<DTO> listSortByNameDesc() {
		ArrayList<DTO> dtos = new ArrayList<DTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			String sql = "SELECT ID, NAME, TITLE, CONTENT, POSTDATE AS \"DATE\", VIEWS FROM MVC_BOARD ORDER BY NAME DESC, ID ASC";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Timestamp date = rs.getTimestamp("date");
				int views = rs.getInt("views");
				
				DTO dto = new DTO(id, name, title, content, date, views);
				dtos.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) con.close();
				if (ps != null) ps.close();
				if (rs != null) rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return dtos;
	}
	
	public ArrayList<DTO> listSortByTitleAsc() {
		ArrayList<DTO> dtos = new ArrayList<DTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			String sql = "SELECT ID, NAME, TITLE, CONTENT, POSTDATE AS \"DATE\", VIEWS FROM MVC_BOARD ORDER BY TITLE ASC, ID ASC";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Timestamp date = rs.getTimestamp("date");
				int views = rs.getInt("views");
				
				DTO dto = new DTO(id, name, title, content, date, views);
				dtos.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) con.close();
				if (ps != null) ps.close();
				if (rs != null) rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return dtos;
	}
	
	public ArrayList<DTO> listSortByTitleDesc() {
		ArrayList<DTO> dtos = new ArrayList<DTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			String sql = "SELECT ID, NAME, TITLE, CONTENT, POSTDATE AS \"DATE\", VIEWS FROM MVC_BOARD ORDER BY TITLE DESC, ID ASC";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Timestamp date = rs.getTimestamp("date");
				int views = rs.getInt("views");
				
				DTO dto = new DTO(id, name, title, content, date, views);
				dtos.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) con.close();
				if (ps != null) ps.close();
				if (rs != null) rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return dtos;
	}
	
	public DTO contentView(String postId) {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DTO dto = null;
		
		try {
			con = dataSource.getConnection();
			String sql = "SELECT * FROM MVC_BOARD WHERE ID=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(postId));
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Timestamp date = rs.getTimestamp("postDate");
				int views = rs.getInt("views");

				
				dto = new DTO(id, name, title, content, date, views);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) con.close();
				if (ps != null) ps.close();
				if (rs != null) rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return dto;
	}
	
	public void modify(String postId, String name, String title, String content) {
		Connection con = null;
		PreparedStatement ps = null;
		int i = 0;
		
		try {
			con = dataSource.getConnection();
			
			String sql = "UPDATE MVC_BOARD SET name=?, title=?, content=? WHERE ID=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, title);
			ps.setString(3, content);
			ps.setInt(4, Integer.parseInt(postId));
			
			i = ps.executeUpdate();
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) con.close();
				if (ps != null) ps.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		if (i > 0) {
			System.out.println("Successfully modified a post ID: " + postId);
			System.out.println("****************************************");
		} else {
			System.out.println("Error while modifying a post ID: " + postId);
			System.out.println("****************************************");
		}
	}
	
	public void delete(String postId) {
		Connection con = null;
		PreparedStatement ps = null;
		int i = 0;
		
		try {
			con = dataSource.getConnection();
			
			String sql = "DELETE FROM MVC_BOARD WHERE ID=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(postId));
			i = ps.executeUpdate();
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) con.close();
				if (ps != null) ps.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		if (i > 0) {
			System.out.println("Successfully deleted a post ID: " + postId);
			System.out.println("****************************************");
		} else {
			System.out.println("Error while deleting a post ID: " + postId);
			System.out.println("****************************************");
		}
	}
	
	public void increaseView(String postId) {
		Connection con = null;
		PreparedStatement ps = null;
		int i = 0;
		
		try {
			con = dataSource.getConnection();
			
			String sql = "UPDATE MVC_BOARD SET views=views+1 WHERE ID=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(postId));		
			i = ps.executeUpdate();
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) con.close();
				if (ps != null) ps.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		if (i > 0) {
			System.out.println("Successfully increased a hit for post ID: " + postId);
			System.out.println("****************************************");
		} else {
			System.out.println("Error while increasing a hit for post ID:" + postId);
			System.out.println("****************************************");
		}
	}
	
	public MemberDTO getMember(String username) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		MemberDTO memDto = null;
		
		try {
			con = dataSource.getConnection();
			String sql = "SELECT * FROM MVC_MEMBER WHERE USERNAME=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				String user = rs.getString("username");
				String pw = rs.getString("pw");
				
				if (user.equals(username)) {
					memDto = new MemberDTO(user, pw);
					return memDto;
				}
			}
		}  catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) con.close();
				if (ps != null) ps.close();
				if (rs != null) rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return null;
	}
	
	public boolean signUp(String username, String password) {
		Connection con = null;
		PreparedStatement ps = null;
		int res = 0;
		
		try {
			con = dataSource.getConnection();
			MemberDTO memDto = getMember(username);
			

			if (memDto == null) {
				String sql = "INSERT INTO MVC_MEMBER (ID, USERNAME, PW) VALUES(MVC_MEMBER_SEQ.NEXTVAL, ?, ?)";
				ps = con.prepareStatement(sql);
				ps.setString(1, username);
				ps.setString(2, password);
				
				res = ps.executeUpdate();
				con.commit();
			}
		}  catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) con.close();
				if (ps != null) ps.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		if (res > 0) {
			System.out.println("Sign up was Successful.");
			System.out.println("****************************************");
			return true;
		} else {
			System.out.println("Sign up failed.");
			System.out.println("****************************************");
			return false;
		}
		
	}
	
	public int verifyLogin(String username, String password) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int count = 0;
		int res = -1;
		
		try {
			con = dataSource.getConnection();
			String sql = "SELECT * FROM MVC_MEMBER WHERE USERNAME=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				count++;
				String pw = rs.getString("pw");
				
				if (!password.equals(pw)) {
					res = WRONG_PASSWORD;
					break;
				} else if (password.equals(pw)) {
					res = IS_MEMBER;
				}
			}
			if (count == 0) {
				res = NOT_MEMBER;
			}
		}  catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) con.close();
				if (ps != null) ps.close();
				if (rs != null) rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return res;
	}
	
	public ArrayList<ReplyDTO> getReplies(String postId) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<ReplyDTO> dtos = new ArrayList<ReplyDTO>();
		
		try {
			con = dataSource.getConnection();
			String sql = "SELECT * FROM MVC_BOARD_REPLY WHERE POSTID=? ORDER BY POSTDATE DESC";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(postId));
			rs = ps.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("id");
				int postIdInt = rs.getInt("postId");
				String username = rs.getString("username");
				String content = rs.getString("content");
				Timestamp postDate = rs.getTimestamp("postdate");
				dtos.add(new ReplyDTO(id, postIdInt, username, content, postDate));
			}
		}  catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) con.close();
				if (ps != null) ps.close();
				if (rs != null) rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return dtos;
	}
	
	public void writeReply(String postId, String username, String content) {
		Connection con = null;
		PreparedStatement ps = null;
		int i = 0;

		try {
			con = dataSource.getConnection();
			String sql = "INSERT INTO MVC_BOARD_REPLY (id, postid, username, content) VALUES(mvc_board_reply_seq.nextval, ?, ?, ?)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(postId));
			ps.setString(2, username);
			ps.setString(3, content);
			
			i = ps.executeUpdate();
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) con.close();
				if (ps != null) ps.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		if (i > 0) {
			System.out.println("Successfully added a reply.");
			System.out.println("****************************************");
		} else {
			System.out.println("Error while adding a reply.");
			System.out.println("****************************************");
		}
	}
	
	public void deleteReply(String id) {
		Connection con = null;
		PreparedStatement ps = null;
		int i = 0;

		try {
			con = dataSource.getConnection();
			String sql = "DELETE FROM MVC_BOARD_REPLY WHERE ID=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(id));
			
			i = ps.executeUpdate();
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) con.close();
				if (ps != null) ps.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		if (i > 0) {
			System.out.println("Successfully deleted a reply.");
			System.out.println("****************************************");
		} else {
			System.out.println("Error while deleting a reply.");
			System.out.println("****************************************");
		}
	}
	
	public void deleteReplyByPostId(String postId) {
		Connection con = null;
		PreparedStatement ps = null;
		int i = 0;
		
		try {
			con = dataSource.getConnection();
			String sql = "DELETE FROM MVC_BOARD_REPLY WHERE POSTID=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(postId));

			i = ps.executeUpdate();
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) con.close();
				if (ps != null) ps.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		if (i > 0) {
			System.out.println("Successfully deleted " + i + " replies for post: " + postId);
			System.out.println("****************************************");
		}
	}
	
	public void uploadImage(int postId, String username, String imgName, String imgPath) {
		Connection con = null;
		PreparedStatement ps = null;
		int i = 0;
		
		try {
			con = dataSource.getConnection();
			String sql = "INSERT INTO MVC_BOARD_IMAGE (id, postid, username, imgpath, imgname) VALUES(mvc_board_image_seq.nextval, ?, ?, ?, ?)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, postId);
			ps.setString(2, username);
			ps.setString(3, imgPath);
			ps.setString(4, imgName);
			
			i = ps.executeUpdate();
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) con.close();
				if (ps != null) ps.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		if (i > 0) {
			System.out.println("Successfully uploaded an image: " + imgName);
			System.out.println("****************************************");
		} else {
			System.out.println("Error while uploading an image: " + imgName);
			System.out.println("****************************************");
		}
	}
	
	public int getPostId(String name, String title, String content) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int postId = -1;
		
		try {
			con = dataSource.getConnection();
			String sql = "SELECT * FROM MVC_BOARD WHERE CONTENT=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, content);
//			ps.setString(2, title);
//			ps.setString(3, content);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("id");
//				System.out.println("found");
//				System.out.println(name.equals(rs.getString("name")));
//				System.out.println(title.equals(rs.getString("title")));
//				System.out.println(content.equals(rs.getString("content")));
//				return id;
				if (name.equals(rs.getString("name")) && title.equals(rs.getString("title")) && title.equals(rs.getString("content"))) {
					return id;
				}
			}
		}  catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) con.close();
				if (ps != null) ps.close();
				if (rs != null) rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return postId;
	}
	
	public ArrayList<ImageDTO> getImagesForPost(String postId) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<ImageDTO> dtos = new ArrayList<ImageDTO>();
		
		try {
			con = dataSource.getConnection();
			String sql = "SELECT * FROM MVC_BOARD_IMAGE WHERE POSTID=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(postId));
			rs = ps.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String imgName = rs.getString("imgName");
				String imgPath = rs.getString("imgPath");
				
				dtos.add(new ImageDTO(id, Integer.parseInt(postId), username, imgName, imgPath));
			}
		}  catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) con.close();
				if (ps != null) ps.close();
				if (rs != null) rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return dtos;
	}
	
	public void deleteImageById(String id) {
		Connection con = null;
		PreparedStatement ps = null;
		int i = 0;
		
		try {
			con = dataSource.getConnection();
			String sql = "DELETE FROM MVC_BOARD_IMAGE WHERE ID=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(id));

			i = ps.executeUpdate();
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) con.close();
				if (ps != null) ps.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		if (i > 0) {
			System.out.println("Successfully deleted the image");
			System.out.println("****************************************");
		} else {
			System.out.println("Error while deleting the image");
			System.out.println("****************************************");
		}
	}
	
	public void deleteImageByPostId(String postId) {
		Connection con = null;
		PreparedStatement ps = null;
		int i = 0;
		
		try {
			con = dataSource.getConnection();
			String sql = "DELETE FROM MVC_BOARD_IMAGE WHERE POSTID=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(postId));

			i = ps.executeUpdate();
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) con.close();
				if (ps != null) ps.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		if (i > 0) {
			System.out.println("Successfully deleted " + i + " images for post: " + postId);
			System.out.println("****************************************");
		}
	}
}
