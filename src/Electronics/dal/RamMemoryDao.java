package Electronics.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Electronics.model.*;


public class RamMemoryDao extends ElectronicProductDetailsDao {
	// Single pattern: instantiation is limited to one object.
	private static RamMemoryDao instance = null;
	protected RamMemoryDao() {
		super();
	}
	public static RamMemoryDao getInstance() {
		if(instance == null) {
			instance = new RamMemoryDao();
		}
		return instance;
	}
	
	public RamMemory create(RamMemory rammemory) throws SQLException {
		// Insert into the superclass table first.
		create(new ElectronicProductDetails(rammemory.getWeb_scraper_order(), rammemory.getProductName(),
				rammemory.getName_hef(),rammemory.getPrice(),rammemory.getRating(),rammemory.getNumber_of_Reviews()));
		String insertRamMemory = "INSERT INTO RamMemory(web_scraper_order,DDR,"
				+ "Memory_Size,MemoryType) "
				+ "VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertRamMemory);
			insertStmt.setString(1, rammemory.getWeb_scraper_order());
			insertStmt.setString(2, rammemory.getDDR());
			//System.out.println(rammemory.getDDR().name());
			insertStmt.setString(3, rammemory.getMemory_Size());
			//System.out.println(rammemory.getMemory_Size());
			insertStmt.setInt(4, rammemory.getMemoryType());
			
			insertStmt.executeUpdate();
			return rammemory;
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
	
	
	public RamMemory getRamMemoryByWeb_scraper_order(String web_scraper_order) throws SQLException {
				String selectRamMemory = 
						"SELECT RamMemory.web_scraper_order AS web_scraper_order,"
						+ "ProductName,Name_hef,Price,Rating,Number_of_Reviews"
						+ "RamMemory.DDR AS DDR,"
						+ "RamMemory.Memory_Size AS Memory_Size,"
						+ "RamMemory.MemoryType AS MemoryType,"
						+ " FROM ElectronicProductDetails "
						+ "INNER JOIN RamMemory"
						+ " ON RamMemory.web_scraper_order=ElectronicProductDetails.web_scraper_order "
						+ "WHERE RamMemory.web_scraper_order=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRamMemory);
			selectStmt.setString(1,web_scraper_order);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String resultWeb_scraper_order = results.getString("web_scraper_order");
				String ddr = results.getString("DDR");
				String Memory_Size = results.getString("Memory_Size");
				int MemoryType = results.getInt("MemoryType");
				RamMemory rammemory = new RamMemory(resultWeb_scraper_order,ProductName,Name_hef,
						Price,Rating,Number_of_Reviews,ddr,Memory_Size,MemoryType);
				return rammemory;
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
	
	public List<RamMemory> getRamMemoryByMemory_Size(String memory_size)throws SQLException {
		List<RamMemory> RamMemorys = new ArrayList<RamMemory>();
				String selectRamMemory = 
						"SELECT RamMemory.web_scraper_order AS web_scraper_order,"
								+ "ProductName,Name_hef,Price,Rating,Number_of_Reviews"
								+ "RamMemory.DDR AS DDR,"
								+ "RamMemory.Memory_Size AS Memory_Size,"
								+ "RamMemory.MemoryType AS MemoryType,"
								+ " FROM ElectronicProductDetails "
								+ "INNER JOIN RamMemory"
								+ " ON RamMemory.web_scraper_order=ElectronicProductDetails.web_scraper_order "
				+ "WHERE RamMemory.Memory_Size=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRamMemory);
			selectStmt.setString(1, memory_size);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String resultWeb_scraper_order = results.getString("web_scraper_order");
				String ddr = results.getString("DDR");
				String Memory_Size = results.getString("Memory_Size");
				int MemoryType = results.getInt("MemoryType");
				RamMemory rammemory = new RamMemory(resultWeb_scraper_order,ProductName,Name_hef,
						Price,Rating,Number_of_Reviews,ddr,Memory_Size,MemoryType);
				RamMemorys.add(rammemory);
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
		return RamMemorys;
	}
	
	public List<RamMemory> getRamMemoryByMemoryType(int memorytype)throws SQLException {
		List<RamMemory> RamMemorys = new ArrayList<RamMemory>();
				String selectRamMemory = 
						"SELECT RamMemory.web_scraper_order AS web_scraper_order,"
								+ "ProductName,Name_href,Price,Rating,Number_of_Reviews"
								+ "RamMemory.DDR AS DDR,"
								+ "RamMemory.Memory_Size AS Memory_Size,"
								+ "RamMemory.MemoryType AS MemoryType,"
								+ " FROM RamMemory "
								+ "INNER JOIN ElectronicProductDetails"
								+ " ON RamMemory.web_scraper_order=ElectronicProductDetails.web_scraper_order "
				+ "WHERE RamMemory.MemoryType=? ;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRamMemory);
			selectStmt.setInt(1, memorytype);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String resultWeb_scraper_order = results.getString("web_scraper_order");
				String ProductName = results.getString("ProductName");
				String Name_hef = results.getString("Name_hef");
				double Price = results.getDouble("Price");
				double Rating = results.getDouble("Rating");
				int Number_of_Reviews = results.getInt("Number_of_Reviews");
				String dDR = results.getString("DDR");
				String Memory_Size = results.getString("Memory_Size");
				int MemoryType = results.getInt("MemoryType");
				RamMemory rammemory = new RamMemory(resultWeb_scraper_order,ProductName,Name_hef,
						Price,Rating,Number_of_Reviews,dDR,Memory_Size,MemoryType);
				RamMemorys.add(rammemory);
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
		return RamMemorys;
	}
	
	public List<RamMemory> getRamMemoryByParameters(String memory_Size, String DDR)throws SQLException {
		List<RamMemory> RamMemorys = new ArrayList<RamMemory>();
		String selectRamMemory = 
				"SELECT RamMemory.web_scraper_order AS web_scraper_order,"
						+ "ProductName,Name_href,Price,Rating,Number_of_Reviews,"
						+ "RamMemory.DDR AS DDR,"
						+ "RamMemory.Memory_Size AS Memory_Size,"
						+ "RamMemory.MemoryType AS MemoryType"
						+ " FROM ElectronicProductDetails "
						+ "INNER JOIN RamMemory"
						+ " ON RamMemory.web_scraper_order=ElectronicProductDetails.web_scraper_order "
						+ "WHERE RamMemory.Memory_Size LIKE ? "
						+ "and RamMemory.MemoryType LIKE ? ;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRamMemory);
			selectStmt.setString(1, "%"+memory_Size+"%");
			selectStmt.setString(2,"%"+DDR+"%");
			results = selectStmt.executeQuery();
			while(results.next()) {
				String resultWeb_scraper_order = results.getString("web_scraper_order");
				String ProductName = results.getString("ProductName");
				String Name_hef = results.getString("Name_href");
				double Price = results.getDouble("Price");
				double Rating = results.getDouble("Rating");
				int Number_of_Reviews = results.getInt("Number_of_Reviews");
				String dDR = results.getString("DDR");
				String Memory_Size = results.getString("Memory_Size");
				int MemoryType = results.getInt("MemoryType");
				RamMemory rammemory = new RamMemory(resultWeb_scraper_order,ProductName,Name_hef,
						Price,Rating,Number_of_Reviews,dDR,Memory_Size,MemoryType);
				RamMemorys.add(rammemory);
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
		return RamMemorys;
	}
	
	
	public RamMemory delete(RamMemory rammemory) throws SQLException {
		String deleteRammemory = "DELETE FROM RamMemory WHERE web_scraper_order=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteRammemory);
			deleteStmt.setString(1, rammemory.getWeb_scraper_order());
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
