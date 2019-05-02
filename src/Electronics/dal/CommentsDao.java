package Electronics.dal;

import Electronics.model.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentsDao {
	protected ConnectionManager connectionManager;

	private static CommentsDao instance = null;
	protected CommentsDao() {
		connectionManager = new ConnectionManager();
	}
	public static CommentsDao getInstance() {
		if(instance == null) {
			instance = new CommentsDao();
		}
		return instance;
	}

	public Comments create(Comments comment) throws SQLException {
		String insertReview =
			"INSERT INTO Comments(BlogId,UserName,Content,CreatedTime,Vote) " +
			"VALUES(?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertReview,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setTimestamp(4, new Timestamp(comment.getCreatedTime().getTime()));
			insertStmt.setString(2, comment.getUser().getUsername());
			insertStmt.setInt(1, comment.getBlog().getBlogId());
			insertStmt.setString(3, comment.getContent());
			insertStmt.setBigDecimal(5, comment.getVote());
			insertStmt.executeUpdate();
			resultKey = insertStmt.getGeneratedKeys();
			int commentId = -1;
			if(resultKey.next()) {
				commentId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			comment.setCommentId(commentId);
			return comment;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
			if(resultKey != null) {
				resultKey.close();
			}
		}
	}
	public Comments updateContent(Comments comment, String newContent) throws SQLException {
		String updateReview = "UPDATE Comments SET Content=?,CreatedTime=? WHERE CommentId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateReview);
			updateStmt.setString(1, newContent);
			Date newCreatedTimestamp = new Date();
			updateStmt.setTimestamp(2, new Timestamp(newCreatedTimestamp.getTime()));
			updateStmt.setInt(3, comment.getCommentId());
			updateStmt.executeUpdate();
			comment.setContent(newContent);
			comment.setCreatedTime(newCreatedTimestamp);
			return comment;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}

	public Comments delete(Comments comment) throws SQLException {
		String deleteComment = "DELETE FROM Comments WHERE CommentId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteComment);
			deleteStmt.setInt(1, comment.getCommentId());
			deleteStmt.executeUpdate();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}
	public Comments getCommentById(int commentId) throws SQLException {
		String selectComment =
			"SELECT *" +
			"FROM Comments " +
			"WHERE CommentId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectComment);
			selectStmt.setInt(1, commentId);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			BlogsDao blogsDao=BlogsDao.getInstance();
			if(results.next()) {
				String content = results.getString("Content");
				String userName = results.getString("UserName");
				Date createdTime =  new Date(results.getTimestamp("CreatedTime").getTime());
				int blogId=results.getInt("BlogId");
				Blogs blog=blogsDao.getBlogById(blogId);;
				Users user = usersDao.getUserByUserName (userName);
				BigDecimal vote=results.getBigDecimal("Vote");
				Comments comment = new Comments(commentId,blog,user,vote,content,createdTime);
				return comment;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}
	public List<Comments> getCommentsByUserName(String userName) throws SQLException {
		List<Comments> comments = new ArrayList<Comments>();
		String selectComments =
				"SELECT *" +
						"FROM Comments " +
						"WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectComments);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			BlogsDao blogsDao=BlogsDao.getInstance();
			while(results.next()) {
				String content = results.getString("Content");
				int commentId = results.getInt("CommentId");
				Date createdTime =  new Date(results.getTimestamp("CreatedTime").getTime());
				int blogId=results.getInt("BlogId");
				BigDecimal vote=results.getBigDecimal("Vote");
				Blogs blog=blogsDao.getBlogById(blogId);;
				Users user = usersDao.getUserByUserName (userName);
				Comments comment = new Comments(commentId,blog,user,vote,content,createdTime);
				comments.add(comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return comments;
	}
	public List<Comments> getCommentsByBlogId(int blogId) throws SQLException {
		List<Comments> comments = new ArrayList<Comments>();
		String selectComments =
				"SELECT *" +
						"FROM Comments " +
						"WHERE BlogId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectComments);
			selectStmt.setInt(1, blogId);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			BlogsDao blogsDao=BlogsDao.getInstance();
			while(results.next()) {
				String content = results.getString("Content");
				int commentId = results.getInt("CommentId");
				Date createdTime =  new Date(results.getTimestamp("CreatedTime").getTime());
				String userName=results.getString("UserName");
				BigDecimal vote=results.getBigDecimal("Vote");
				Blogs blog=blogsDao.getBlogById(blogId);;
				Users user = usersDao.getUserByUserName (userName);
				Comments comment = new Comments(commentId,blog,user,vote,content,createdTime);
				comments.add(comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return comments;
	}
}
