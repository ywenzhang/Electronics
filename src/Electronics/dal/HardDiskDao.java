package Electronics.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Electronics.model.HardDisk;
import Electronics.model.ElectronicProductDetails;
import Electronics.model.GraphicCard;

public class HardDiskDao extends ElectronicProductDetailsDao {
	// Single pattern: instantiation is limited to one object.
	private static HardDiskDao instance = null;
	protected HardDiskDao() {
		super();
	}
	public static HardDiskDao getInstance() {
		if(instance == null) {
			instance = new HardDiskDao();
		}
		return instance;
	}

	public HardDisk create(HardDisk harddisk) throws SQLException {
		// Insert into the superclass table first.
		create(new ElectronicProductDetails(harddisk.getWeb_scraper_order(), harddisk.getProductName(),
				harddisk.getName_hef(),harddisk.getPrice(),harddisk.getRating(),harddisk.getNumber_of_Reviews()));
		String insertHardDisk = "INSERT INTO HardDisk(web_scraper_order,Image,"
				+ "Manufacturer,RAM_SATA,Capacity, HardDiskType) "
				+ "VALUES(?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertHardDisk);
			insertStmt.setString(1, harddisk.getWeb_scraper_order());
			insertStmt.setString(2, harddisk.getImage());
			insertStmt.setString(3, harddisk.getManufacturer());
			insertStmt.setBoolean(4, harddisk.isRAM_SATA());
			insertStmt.setString(5, harddisk.getCapacity());
			insertStmt.setInt(6, harddisk.getHard_Disk_Type());
			insertStmt.executeUpdate();
			return harddisk;
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


	public HardDisk getHardDiskByWeb_scraper_order(String web_scraper_order) throws SQLException {
		// To build an BlogUser object, we need the Persons record, too.
				String selectHardDisk = 
						"SELECT HardDisk.web_scraper_order AS web_scraper_order,"
						+ "ProductName,Name_hef,Price,Rating,Number_of_Reviews"
						+ "HardDisk.Image AS Image,"
						+ "HardDisk.Manufacturer AS Manufacturer,"
						+ "HardDisk.RAM_SATA AS RAM_SATA,"
						+ "HardDisk.Capacity AS Capacity,"
						+ "HardDisk.HardDiskType AS HardDiskType,"
						+ " FROM ElectronicProductDetails "
						+ "INNER JOIN HardDisk"
						+ " ON HardDisk.web_scraper_order=ElectronicProductDetails.web_scraper_order "
						+ "WHERE HardDisk.web_scraper_order=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectHardDisk);
			selectStmt.setString(1,web_scraper_order);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String resultWeb_scraper_order = results.getString("web_scraper_order");
				String Image = results.getString("Image");
				String Manufacturer = results.getString("Manufacturer");
				boolean RAM_SATA = results.getBoolean("RAM_SATA");
				String Capacity = results.getString("Capacity");
				int HardDiskType = results.getInt("HardDiskType");
				HardDisk harddisk = new HardDisk(resultWeb_scraper_order,ProductName,Name_hef,
						Price,Rating,Number_of_Reviews,Image,Manufacturer,RAM_SATA,Capacity,HardDiskType);
				return harddisk;
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


	public List<HardDisk> getHardDiskByManufacturer(String manufacturer)throws SQLException {
		List<HardDisk> HardDisks = new ArrayList<HardDisk>();
				String selectHardDisk = 
				"SELECT HardDisk.web_scraper_order AS web_scraper_order,"
				+ "ProductName,Name_hef,Price,Rating,Number_of_Reviews"
				+ "HardDisk.Image AS Image,"
				+ "HardDisk.Manufacturer AS Manufacturer,"
				+ "HardDisk.RAM_SATA AS RAM_SATA,"
				+ "HardDisk.Capacity AS Capacity,"
				+ "HardDisk.HardDiskType AS HardDiskType,"
				+ " FROM ElectronicProductDetails "
				+ "INNER JOIN HardDisk"
				+ " ON HardDisk.web_scraper_order=ElectronicProductDetails.web_scraper_order "
				+ "WHERE HardDisk.Manufacturer=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectHardDisk);
			selectStmt.setString(1, manufacturer);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String resultWeb_scraper_order = results.getString("web_scraper_order");
				String Image = results.getString("Image");
				String Manufacturer = results.getString("Manufacturer");
				boolean RAM_SATA = results.getBoolean("RAM_SATA");
				String Capacity = results.getString("Capacity");
				int HardDiskType = results.getInt("HardDiskType");
				HardDisk harddisk = new HardDisk(resultWeb_scraper_order,ProductName,Name_hef,
						Price,Rating,Number_of_Reviews,Image,Manufacturer,RAM_SATA,Capacity,HardDiskType);
				HardDisks.add(harddisk);
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
		return HardDisks;
	}
	public List<HardDisk> getDesktopByRAM_SATA(boolean ram_sata )throws SQLException {
		List<HardDisk> HardDisks = new ArrayList<HardDisk>();
		String selectHardDisk = 
			"SELECT HardDisk.web_scraper_order AS web_scraper_order,"
			+ "ProductName,Name_hef,Price,Rating,Number_of_Reviews"
			+ "HardDisk.Image AS Image,"
			+ "HardDisk.Manufacturer AS Manufacturer,"
			+ "HardDisk.RAM_SATA AS RAM_SATA,"
			+ "HardDisk.Capacity AS Capacity,"
			+ "HardDisk.HardDiskType AS HardDiskType,"
			+ " FROM ElectronicProductDetails "
			+ "INNER JOIN HardDisk"
			+ " ON HardDisk.web_scraper_order=ElectronicProductDetails.web_scraper_order "
			+ "WHERE HardDisk.Manufacturer=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectHardDisk);
			selectStmt.setBoolean(1, ram_sata);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String resultWeb_scraper_order = results.getString("web_scraper_order");
				String Image = results.getString("Image");
				String Manufacturer = results.getString("Manufacturer");
				boolean RAM_SATA = results.getBoolean("RAM_SATA");
				String Capacity = results.getString("Capacity");
				int HardDiskType = results.getInt("HardDiskType");
				HardDisk harddisk = new HardDisk(resultWeb_scraper_order,ProductName,Name_hef,
						Price,Rating,Number_of_Reviews,Image,Manufacturer,RAM_SATA,Capacity,HardDiskType);
				HardDisks.add(harddisk);
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
		return HardDisks;
	}
	
	public List<HardDisk> getLaptopByMemory_Size(String capacity)throws SQLException {
		List<HardDisk> HardDisks = new ArrayList<HardDisk>();
		String selectHardDisk = 
				"SELECT HardDisk.web_scraper_order AS web_scraper_order,"
				+ "ProductName,Name_hef,Price,Rating,Number_of_Reviews"
				+ "HardDisk.Image AS Image,"
				+ "HardDisk.Manufacturer AS Manufacturer,"
				+ "HardDisk.RAM_SATA AS RAM_SATA,"
				+ "HardDisk.Capacity AS Capacity,"
				+ "HardDisk.HardDiskType AS HardDiskType,"
				+ " FROM ElectronicProductDetails "
				+ "INNER JOIN HardDisk"
				+ " ON HardDisk.web_scraper_order=ElectronicProductDetails.web_scraper_order "
				+ "WHERE HardDisk.Capacity=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectHardDisk);
			selectStmt.setString(1, capacity);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String resultWeb_scraper_order = results.getString("web_scraper_order");
				String Image = results.getString("Image");
				String Manufacturer = results.getString("Manufacturer");
				boolean RAM_SATA = results.getBoolean("RAM_SATA");
				String Capacity = results.getString("Capacity");
				int HardDiskType = results.getInt("HardDiskType");
				HardDisk harddisk = new HardDisk(resultWeb_scraper_order,ProductName,Name_hef,
						Price,Rating,Number_of_Reviews,Image,Manufacturer,RAM_SATA,Capacity,HardDiskType);
				HardDisks.add(harddisk);
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
		return HardDisks;
	}
	public List<HardDisk> getDesktopByHard_Disk_Size(String harddisktype)throws SQLException {
		List<HardDisk> HardDisks = new ArrayList<HardDisk>();
		String selectHardDisk = 
				"SELECT HardDisk.web_scraper_order AS web_scraper_order,"
				+ "ProductName,Name_hef,Price,Rating,Number_of_Reviews"
				+ "HardDisk.Image AS Image,"
				+ "HardDisk.Manufacturer AS Manufacturer,"
				+ "HardDisk.RAM_SATA AS RAM_SATA,"
				+ "HardDisk.Capacity AS Capacity,"
				+ "HardDisk.HardDiskType AS HardDiskType,"
				+ " FROM ElectronicProductDetails "
				+ "INNER JOIN HardDisk"
				+ " ON HardDisk.web_scraper_order=ElectronicProductDetails.web_scraper_order "
				+ "WHERE HardDisk.HardDiskType=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectHardDisk);
			selectStmt.setString(1, harddisktype);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String resultWeb_scraper_order = results.getString("web_scraper_order");
				String Image = results.getString("Image");
				String Manufacturer = results.getString("Manufacturer");
				boolean RAM_SATA = results.getBoolean("RAM_SATA");
				String Capacity = results.getString("Capacity");
				int HardDiskType = results.getInt("HardDiskType");
				HardDisk harddisk = new HardDisk(resultWeb_scraper_order,ProductName,Name_hef,
						Price,Rating,Number_of_Reviews,Image,Manufacturer,RAM_SATA,Capacity,HardDiskType);
				HardDisks.add(harddisk);
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
		return HardDisks;
	}
	
	public List<HardDisk> getHardDiskByParameters(String manufacturer, String capacity)throws SQLException {
		List<HardDisk> harddisks = new ArrayList<HardDisk>();
		String selectHarddisk = 
				"SELECT HardDisk.web_scraper_order AS web_scraper_order,"
				+ "ProductName,Name_href,Price,Rating,Number_of_Reviews,"
				+ "HardDisk.Image AS Image,"
				+ "HardDisk.Manufacturer AS Manufacturer,"
				+ "HardDisk.RAM_SATA AS RAM_SATA,"
				+ "HardDisk.Capacity AS Capacity,"
				+ "HardDisk.HardDiskType AS HardDiskType"
				+ " FROM ElectronicProductDetails "
				+ "INNER JOIN HardDisk"
				+ " ON HardDisk.web_scraper_order=ElectronicProductDetails.web_scraper_order "
				+ "WHERE HardDisk.Manufacturer LIKE ? "
		        + "and HardDisk.Capacity LIKE ? ;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectHarddisk);
			selectStmt.setString(1, "%"+manufacturer+"%");
			selectStmt.setString(2, "%"+capacity+"%");			
			results = selectStmt.executeQuery();
			while(results.next()) {
				String resultWeb_scraper_order = results.getString("web_scraper_order");
				String ProductName = results.getString("ProductName");
				String Name_hef = results.getString("Name_href");
				double Price = results.getDouble("Price");
				double Rating = results.getDouble("Rating");
				int Number_of_Reviews = results.getInt("Number_of_Reviews");
				String Image = results.getString("Image");
				String Manufacturer = results.getString("Manufacturer");
				boolean RAM_SATA = results.getBoolean("RAM_SATA");
				String Capacity = results.getString("Capacity");
				int HardDiskType = results.getInt("HardDiskType");
				HardDisk harddisk = new HardDisk(resultWeb_scraper_order,ProductName,Name_hef,
						Price,Rating,Number_of_Reviews,Image,Manufacturer,RAM_SATA,
						Capacity, HardDiskType);
				harddisks.add(harddisk);
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
		return harddisks;
	}
	
	public HardDisk delete(HardDisk harddisk) throws SQLException {
		String deleteHarddisk = "DELETE FROM HardDisk WHERE web_scraper_order=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteHarddisk);
			deleteStmt.setString(1, harddisk.getWeb_scraper_order());
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
