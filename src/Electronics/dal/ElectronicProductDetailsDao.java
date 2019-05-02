package Electronics.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Electronics.model.ElectronicProductDetails;

public class ElectronicProductDetailsDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static ElectronicProductDetailsDao instance = null;
	protected ElectronicProductDetailsDao() {
		connectionManager = new ConnectionManager();
	}
	public static ElectronicProductDetailsDao getInstance() {
		if(instance == null) {
			instance = new ElectronicProductDetailsDao();
		}
		return instance;
	}

	/**
	 * Save the Persons instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	protected String web_scraper_order;
	protected String ProductName;
	protected String Name_hef;
	protected double Price;
	protected double Rating;
	protected int Number_of_Reviews;
	public ElectronicProductDetails create(ElectronicProductDetails ElectronicProductDetails) throws SQLException {
		String insertElectronicProductDetails = "INSERT INTO ElectronicProductDetails(web_scraper_order,ProductName,Name_href,Price,Rating,Number_of_Reviews) "
				+ "VALUES(?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertElectronicProductDetails);
			insertStmt.setString(1, ElectronicProductDetails.getWeb_scraper_order());
			insertStmt.setString(2, ElectronicProductDetails.getProductName());
			insertStmt.setString(3, ElectronicProductDetails.getName_hef());
			insertStmt.setDouble(4, ElectronicProductDetails.getPrice());
			insertStmt.setDouble(5, ElectronicProductDetails.getRating());
			insertStmt.setInt(6, ElectronicProductDetails.getNumber_of_Reviews());
			insertStmt.executeUpdate();
			return ElectronicProductDetails;
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

	public ElectronicProductDetails updatePrice(ElectronicProductDetails electronicProductDetails, double newPrice) throws SQLException {
		String updatePrice = "UPDATE ElectronicProductDetails SET Price=? WHERE web_scraper_order=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updatePrice);
			updateStmt.setDouble(1,newPrice);
			updateStmt.setString(2, electronicProductDetails.getWeb_scraper_order());
			updateStmt.executeUpdate();
			
			// Update the person param before returning to the caller.
			electronicProductDetails.setPrice(newPrice);
			return electronicProductDetails;
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
	
	public ElectronicProductDetails delete(ElectronicProductDetails electronicProductDetails) throws SQLException {
		String deleteProduct = "DELETE FROM ElectronicProductDetails WHERE web_scraper_order=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteProduct);
			deleteStmt.setString(1, electronicProductDetails.getWeb_scraper_order());
			deleteStmt.executeUpdate();
			// Return null so the caller can no longer operate on the Persons instance.
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

	public ElectronicProductDetails getProductByScraperOrder(String web_scraper_order) throws SQLException {
		String selectProduct = "SELECT web_scraper_order,ProductName,Name_href,Price,Rating,Number_of_Reviews"
				+ " FROM ElectronicProductDetails WHERE web_scraper_order=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectProduct);
			selectStmt.setString(1, web_scraper_order);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String resultWeb_scraper_order = results.getString("web_scraper_order");
				String ProductName= results.getString("ProductName");
				String Name_hef= results.getString("Name_href");
				double Price= results.getDouble("Price");
				double Rating= results.getDouble("Rating");
				int Number_of_Reviews = results.getInt("Number_of_Reviews");
				ElectronicProductDetails electronicProductDetails = new ElectronicProductDetails(resultWeb_scraper_order,ProductName,Name_hef,Price,Rating,Number_of_Reviews);
				return electronicProductDetails ;
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
}
