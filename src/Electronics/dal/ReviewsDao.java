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
public class ReviewsDao {
	protected ConnectionManager connectionManager;

	private static ReviewsDao instance = null;
	protected ReviewsDao() {
		connectionManager = new ConnectionManager();
	}
	public static ReviewsDao getInstance() {
		if(instance == null) {
			instance = new ReviewsDao();
		}
		return instance;
	}
	public Reviews create(Reviews review) throws SQLException {
		String insertReview =
			"INSERT INTO Reviews(Created,Rating,UserName,Web_scraper_order,Content) " +
			"VALUES(?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertReview,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setTimestamp(1, new Timestamp(review.getCreated().getTime()));
			insertStmt.setBigDecimal(2,review.getRating());
			insertStmt.setString(3, review.getUser().getUsername());
			insertStmt.setString(4, review.getElectronicProductDetail().getWeb_scraper_order());
			insertStmt.setString(5, review.getContent());
			insertStmt.executeUpdate();
			resultKey = insertStmt.getGeneratedKeys();
			int reviewId = -1;
			if(resultKey.next()) {
				reviewId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			review.setReviewId(reviewId);
			return review;
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
	public Reviews updateContent(Reviews review, String newContent) throws SQLException {
		String updateReview = "UPDATE Reviews SET Content=?,Created=? WHERE ReviewId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateReview);
			updateStmt.setString(1, newContent);
			Date newCreatedTimestamp = new Date();
			updateStmt.setTimestamp(2, new Timestamp(newCreatedTimestamp.getTime()));
			updateStmt.setInt(3, review.getReviewId());
			updateStmt.executeUpdate();
			review.setContent(newContent);
			review.setCreated(newCreatedTimestamp);
			return review;
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

	public Reviews delete(Reviews review) throws SQLException {
		String deleteReview = "DELETE FROM Reviews WHERE ReviewId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteReview);
			deleteStmt.setInt(1, review.getReviewId());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the BlogPosts instance.
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
	public Reviews getReviewById(int reviewId) throws SQLException {
		String selectReview =
			"SELECT *" +
			"FROM Reviews " +
			"WHERE ReviewId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReview);
			selectStmt.setInt(1, reviewId);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			ElectronicProductDetailsDao electronicProductDetailsDao=ElectronicProductDetailsDao.getInstance();
			if(results.next()) {
				String content = results.getString("Content");
				BigDecimal rating = results.getBigDecimal("Rating");
				String userName = results.getString("UserName");
				Date created =  new Date(results.getTimestamp("Created").getTime());
				String web_scraper_order=results.getString("Web_scraper_order");
				ElectronicProductDetails electronicProductDetail=electronicProductDetailsDao.getProductByScraperOrder(web_scraper_order);;
				Users user = usersDao.getUserByUserName (userName);
				Reviews review = new Reviews(reviewId,created,rating,user,electronicProductDetail,content);
				return review;
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

	public List<Reviews> getReviewsByUserName(String userName) throws SQLException {
		List<Reviews> reviews = new ArrayList<Reviews>();
		String selectReviews =
			"SELECT *" +
			"FROM Reviews " +
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
			ElectronicProductDetailsDao electronicProductDetailsDao=ElectronicProductDetailsDao.getInstance();
			while(results.next()) {
				int reviewId = results.getInt("ReviewId");
				String content = results.getString("Content");
				BigDecimal rating = results.getBigDecimal("Rating");
				Date created =  new Date(results.getTimestamp("Created").getTime());
				String web_scraper_order=results.getString("Web_scraper_order");
				ElectronicProductDetails electronicProductDetail=electronicProductDetailsDao.getProductByScraperOrder(web_scraper_order);
				Users user = usersDao.getUserByUserName (userName);
				Reviews review = new Reviews(reviewId,created,rating,user,electronicProductDetail,content);
				reviews.add(review);
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
		return reviews;
	}
	public List<Reviews> getReviewsByElectronicProductDetailId(String web_scraper_order) throws SQLException {
		List<Reviews> reviews = new ArrayList<Reviews>();
		String selectReviews =
			"SELECT *" +
			"FROM Reviews " +
			"WHERE web_scraper_order=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReviews);
			selectStmt.setString(1, web_scraper_order);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			ElectronicProductDetailsDao electronicProductDetailsDao=ElectronicProductDetailsDao.getInstance();
			while(results.next()) {
				int reviewId = results.getInt("ReviewId");
				String content = results.getString("Content");
				String userName = results.getString("UserName");
				BigDecimal rating = results.getBigDecimal("Rating");
				Date created =  new Date(results.getTimestamp("Created").getTime());
				ElectronicProductDetails electronicProductDetail=electronicProductDetailsDao.getProductByScraperOrder(web_scraper_order);
				Users user = usersDao.getUserByUserName (userName);
				Reviews review = new Reviews(reviewId,created,rating,user,electronicProductDetail,content);
				reviews.add(review);
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
		return reviews;
	}
}
