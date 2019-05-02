package Electronics.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Electronics.model.CPUProcessor;
import Electronics.model.ElectronicProductDetails;
import Electronics.model.GraphicCard;

public class GraphicCardDao extends ElectronicProductDetailsDao {
	// Single pattern: instantiation is limited to one object.
	private static GraphicCardDao instance = null;
	protected GraphicCardDao() {
		super();
	}
	public static GraphicCardDao getInstance() {
		if(instance == null) {
			instance = new GraphicCardDao();
		}
		return instance;
	}

	public GraphicCard create(GraphicCard graphiccard) throws SQLException {
		// Insert into the superclass table first.
		create(new ElectronicProductDetails(graphiccard.getWeb_scraper_order(), graphiccard.getProductName(),
				graphiccard.getName_hef(),graphiccard.getPrice(),graphiccard.getRating(),graphiccard.getNumber_of_Reviews()));
		String insertGraphicCard = "INSERT INTO GraphicCard(web_scraper_order,Size,Image,"
				+ "Manufacturer,DDR_GDDR,Capacity) "
				+ "VALUES(?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertGraphicCard);
			insertStmt.setString(1, graphiccard.getWeb_scraper_order());
			insertStmt.setString(2, graphiccard.getSize());
			insertStmt.setString(3, graphiccard.getImage());
			insertStmt.setString(4, graphiccard.getManufacturer());
			insertStmt.setString(5, graphiccard.getDDR_GDDR());
			insertStmt.executeUpdate();
			return graphiccard;
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


	public GraphicCard getGraphicCardByWeb_scraper_order(String web_scraper_order) throws SQLException {
		// To build an BlogUser object, we need the Persons record, too.
				String selectGraphicCard = 
						"SELECT GraphicCard.web_scraper_order AS web_scraper_order,"
						+ "ProductName,Name_hef,Price,Rating,Number_of_Reviews"
						+ "GraphicCard.Size AS Size,"
						+ "GraphicCard.Image AS Image,"
						+ "GraphicCard.Manufacturer AS Manufacturer,"
						+ "GraphicCard.DDR_GDDR AS DDR_GDDR,"						
						+ "GraphicCard.Capacity AS Capacity,"
						+ " FROM ElectronicProductDetails "
						+ "INNER JOIN GraphicCard"
						+ " ON GraphicCard.web_scraper_order=ElectronicProductDetails.web_scraper_order "
						+ "WHERE GraphicCard.web_scraper_order=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectGraphicCard);
			selectStmt.setString(1,web_scraper_order);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String resultWeb_scraper_order = results.getString("web_scraper_order");
				String Size = results.getString("Size");
				String Image = results.getString("Image");
				String Manufacturer = results.getString("Manufacturer");
				String DDR_GDDR = results.getString("DDR_GDDR");
				GraphicCard graphiccard = new GraphicCard(resultWeb_scraper_order,ProductName,Name_hef,
						Price,Rating,Number_of_Reviews,Size,Image,Manufacturer,DDR_GDDR);
				return graphiccard;
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


	public List<GraphicCard> getHardDiskByManufacturer(String manufacturer)throws SQLException {
		List<GraphicCard> GraphicCards = new ArrayList<GraphicCard>();
		String selectGraphicCard = 
				"SELECT GraphicCard.web_scraper_order AS web_scraper_order,"
				+ "ProductName,Name_hef,Price,Rating,Number_of_Reviews"
				+ "GraphicCard.Size AS Size,"
				+ "GraphicCard.Image AS Image,"
				+ "GraphicCard.Manufacturer AS Manufacturer,"
				+ "GraphicCard.DDR_GDDR AS DDR_GDDR,"						
				+ "GraphicCard.Capacity AS Capacity,"
				+ " FROM ElectronicProductDetails "
				+ "INNER JOIN GraphicCard"
				+ " ON GraphicCard.web_scraper_order=ElectronicProductDetails.web_scraper_order "
				+ "WHERE GraphicCard.Size=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectGraphicCard);
			selectStmt.setString(1, manufacturer);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String resultWeb_scraper_order = results.getString("web_scraper_order");
				String Size = results.getString("Size");
				String Image = results.getString("Image");
				String Manufacturer = results.getString("Manufacturer");
				String DDR_GDDR = results.getString("DDR_GDDR");
				GraphicCard graphiccard = new GraphicCard(resultWeb_scraper_order,ProductName,Name_hef,
						Price,Rating,Number_of_Reviews,Size,Image,Manufacturer,DDR_GDDR);
				GraphicCards.add(graphiccard);
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
		return GraphicCards;
	}
	public List<GraphicCard> getDesktopByDDR_GDDR(boolean ddr_gddr )throws SQLException {
		List<GraphicCard> GraphicCards = new ArrayList<GraphicCard>();
		String selectGraphicCard = 
				"SELECT GraphicCard.web_scraper_order AS web_scraper_order,"
				+ "ProductName,Name_hef,Price,Rating,Number_of_Reviews"
				+ "GraphicCard.Size AS Size,"
				+ "GraphicCard.Image AS Image,"
				+ "GraphicCard.Manufacturer AS Manufacturer,"
				+ "GraphicCard.DDR_GDDR AS DDR_GDDR,"						
				+ "GraphicCard.Capacity AS Capacity,"
				+ " FROM ElectronicProductDetails "
				+ "INNER JOIN GraphicCard"
				+ " ON GraphicCard.web_scraper_order=ElectronicProductDetails.web_scraper_order "
				+ "WHERE GraphicCard.DDR_GDDR=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectGraphicCard);
			selectStmt.setBoolean(1, ddr_gddr);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String resultWeb_scraper_order = results.getString("web_scraper_order");
				String Size = results.getString("Size");
				String Image = results.getString("Image");
				String Manufacturer = results.getString("Manufacturer");
				String DDR_GDDR = results.getString("DDR_GDDR");
				GraphicCard graphiccard = new GraphicCard(resultWeb_scraper_order,ProductName,Name_hef,
						Price,Rating,Number_of_Reviews,Size,Image,Manufacturer,DDR_GDDR);
				GraphicCards.add(graphiccard);
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
		return GraphicCards;
	}
	
	public List<GraphicCard> getGraphicCardByParameters(String size, String manufacturer, String ddr_gddr)throws SQLException {
		List<GraphicCard> graphiccards = new ArrayList<GraphicCard>();
		String selectGpu = 
				"SELECT GraphicCard.web_scraper_order AS web_scraper_order,"
				+ "ProductName,Name_href,Price,Rating,Number_of_Reviews,"
				+ "GraphicCard.Size AS Size,"
				+ "GraphicCard.Image AS Image,"
				+ "GraphicCard.Manufacturer AS Manufacturer,"
				+ "GraphicCard.DDR_GDDR AS DDR_GDDR "						
				+ " FROM ElectronicProductDetails "
				+ "INNER JOIN GraphicCard"
				+ " ON GraphicCard.web_scraper_order=ElectronicProductDetails.web_scraper_order "
				+ "WHERE GraphicCard.Size LIKE ? "
		        + "and GraphicCard.Manufacturer LIKE ? "
		        + "and GraphicCard.DDR_GDDR LIKE ?; ";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectGpu);
			selectStmt.setString(1, "%"+size+"%");
			selectStmt.setString(2, "%"+manufacturer+"%");
			selectStmt.setString(3, "%"+ddr_gddr+"%");		
			results = selectStmt.executeQuery();
			while(results.next()) {
				String resultWeb_scraper_order = results.getString("web_scraper_order");
				String ProductName = results.getString("ProductName");
				String Name_hef = results.getString("Name_href");
				double Price = results.getDouble("Price");
				double Rating = results.getDouble("Rating");
				int Number_of_Reviews = results.getInt("Number_of_Reviews");
				String Size = results.getString("Size");
				String Image = results.getString("Image");
				String Manufacturer = results.getString("Manufacturer");
				String DDR_GDDR = results.getString("DDR_GDDR");
				GraphicCard graphiccard = new GraphicCard(resultWeb_scraper_order,ProductName,Name_hef,
						Price,Rating,Number_of_Reviews,Size,Image,Manufacturer,DDR_GDDR);
				graphiccards.add(graphiccard);
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
		return graphiccards;
	}

	public GraphicCard delete(GraphicCard graphiccard) throws SQLException {
		String deleteGraphicCard = "DELETE FROM GraphicCard WHERE web_scraper_order=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteGraphicCard);
			deleteStmt.setString(1, graphiccard.getWeb_scraper_order());
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
}
