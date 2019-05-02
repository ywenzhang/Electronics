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
public class BlogsDao {
	protected ConnectionManager connectionManager;

	private static BlogsDao instance = null;
	protected BlogsDao() {
		connectionManager = new ConnectionManager();
	}
	public static BlogsDao getInstance() {
		if(instance == null) {
			instance = new BlogsDao();
		}
		return instance;
	}
	public Blogs create(Blogs blog) throws SQLException {
		String insertReview =
			"INSERT INTO Blogs(BlogId,UserName,Content,CreatedTime) " +
			"VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertReview,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, blog.getBlogId());
			insertStmt.setString(2, blog.getUser().getUsername());
			insertStmt.setString(3, blog.getContent());
			insertStmt.setTimestamp(4, new Timestamp(blog.getCreatedTime().getTime()));
			insertStmt.executeUpdate();
			resultKey = insertStmt.getGeneratedKeys();
			int blogId = -1;
			if(resultKey.next()) {
				blogId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			blog.setBlogId(blogId);
			return blog;
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
	public Blogs updateContent(Blogs blog, String newContent) throws SQLException {
		String updateBlog = "UPDATE Blogs SET Content=? WHERE BlogId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateBlog);
			updateStmt.setString(1, newContent);
			updateStmt.setInt(2, blog.getBlogId());
			updateStmt.executeUpdate();
			blog.setContent(newContent);
			return blog;
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

	public Blogs delete(Blogs blog) throws SQLException {
		String deleteBlog = "DELETE FROM Blogs WHERE blogId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteBlog);
			deleteStmt.setInt(1, blog.getBlogId());
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
	public Blogs getBlogById(int blogId) throws SQLException {
		String selectBlog =
			"SELECT *" +
			"FROM Blogs " +
			"WHERE BlogId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBlog);
			selectStmt.setInt(1, blogId);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			if(results.next()) {
				String content = results.getString("Content");
				String userName = results.getString("UserName");
				Date createdTime =  new Date(results.getTimestamp("CreatedTime").getTime());
				Users user = usersDao.getUserByUserName (userName);
				Blogs blog = new Blogs(blogId,createdTime,user,content);
				return blog;
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

	public List<Blogs> getBlogsByUserName(String userName) throws SQLException {
		List<Blogs> blogs = new ArrayList<Blogs>();
		String selectReviews =
			"SELECT *" +
			"FROM Blogs " +
			"WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReviews);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			while(results.next()) {
				String content = results.getString("Content");
				Date createdTime =  new Date(results.getTimestamp("CreatedTime").getTime());
				int blogId=results.getInt("BlogId");
				Users user = usersDao.getUserByUserName (userName);
				Blogs blog = new Blogs(blogId,createdTime,user,content);
				blogs.add(blog);
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
		return blogs;
	}
}
