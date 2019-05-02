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

public class BooksDao {
	protected ConnectionManager connectionManager;
	private static BooksDao instance = null;
	protected BooksDao() {
		connectionManager = new ConnectionManager();
	}
	public static BooksDao getInstance() {
		if(instance == null) {
			instance = new BooksDao();
		}
		return instance;
	}

	public Books create(Books book) throws SQLException {
		String insertBook =
			"INSERT INTO Reviews(web_scraper_order,Title,Author,Rating,Number_of_Ratings,Number_of_Reviews) " +
			"VALUES(?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertBook);
			insertStmt.setString(1, book.getWeb_scraper_order());
			insertStmt.setString(2,book.getTitle());
			insertStmt.setString(3, book.getAuthor());
			insertStmt.setBigDecimal(4, book.getRating());
			insertStmt.setInt(5, book.getNumber_of_Ratings());
			insertStmt.setInt(6, book.getNumber_of_Reviews());
			insertStmt.executeUpdate();
			return book;
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
		}
	}
	public Books updateRating(Books book, BigDecimal newRating) throws SQLException {
		String updateRating = "UPDATE Books SET Rating=? WHERE web_scraper_order=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateRating);
			updateStmt.setBigDecimal(1, newRating);
			updateStmt.setString(2, book.getWeb_scraper_order());
			updateStmt.executeUpdate();
			book.setRating(newRating);
			return book;
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

	public Books delete(Books book) throws SQLException {
		String deleteBook = "DELETE FROM Books WHERE web_scraper_order=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteBook);
			deleteStmt.setString(1, book.getWeb_scraper_order());
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

	public Books getBookById(String web_scraper_order) throws SQLException {
		String selectBook =
			"SELECT *" +
			"FROM Books " +
			"WHERE web_scraper_order=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBook);
			selectStmt.setString(1, web_scraper_order);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String title = results.getString("Title");
				BigDecimal rating = results.getBigDecimal("Rating");
				String author=results.getString("Author");
				int number_of_Ratings = results.getInt("Number_of_Ratings");
				int number_of_Reviews = results.getInt("Number_of_Reviews");
				Books book = new Books(web_scraper_order,title,author,rating,number_of_Ratings,number_of_Reviews);
				return book;
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
	public List<Books> getBooksByAuthor(String author) throws SQLException {
		List<Books> books = new ArrayList<Books>();
		String selectBooks =
			"SELECT *" +
			"FROM TrackersWebsiteApplication.Books " +
			"WHERE Author=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBooks);
			selectStmt.setString(1, author);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String web_scraper_order=results.getString("web_scraper_order");
				String title = results.getString("Title");
				BigDecimal rating = results.getBigDecimal("Rating");
				int number_of_Ratings = results.getInt("Number_of_Ratings");
				int number_of_Reviews = results.getInt("Number_of_Reviews");
				Books book = new Books(web_scraper_order,title,author,rating,number_of_Ratings,number_of_Reviews);
				books.add(book);
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
		return books;
	}
public List<Books> getAllBooks() throws SQLException {
	List<Books> books = new ArrayList<Books>();
	String selectBooks =
		"SELECT *" +
		"FROM Books";
	Connection connection = null;
	PreparedStatement selectStmt = null;
	ResultSet results = null;
	try {
		connection = connectionManager.getConnection();
		selectStmt = connection.prepareStatement(selectBooks);
		results = selectStmt.executeQuery();
		while(results.next()) {
			String author=results.getString("Author");
			String web_scraper_order=results.getString("web_scraper_order");
			String title = results.getString("Title");
			BigDecimal rating = results.getBigDecimal("Rating");
			int number_of_Ratings = results.getInt("Number_of_Ratings");
			int number_of_Reviews = results.getInt("Number_of_Reviews");
			Books book = new Books(web_scraper_order,title,author,rating,number_of_Ratings,number_of_Reviews);
			books.add(book);
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
	return books;
}
}
