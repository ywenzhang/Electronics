package Electronics.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Electronics.model.Desktop;
import Electronics.model.ElectronicProductDetails;
public class DesktopDao extends ElectronicProductDetailsDao{
	// Single pattern: instantiation is limited to one object.
	private static DesktopDao instance = null;
	protected DesktopDao() {
		super();
	}
	public static DesktopDao getInstance() {
		if(instance == null) {
			instance = new DesktopDao();
		}
		return instance;
	}

	public Desktop create(Desktop desktop) throws SQLException {
		// Insert into the superclass table first.
		create(new ElectronicProductDetails(desktop.getWeb_scraper_order(), desktop.getProductName(),
				desktop.getName_hef(),desktop.getPrice(),desktop.getRating(),desktop.getNumber_of_Reviews()));
		String insertDesktop = "INSERT INTO Desktop(web_scraper_order,Operating_System,"
				+ "CPU_Model_Family,Memory_Size,HardDisk_Size,Memory_Type,CPU_Type,Hard_Disk_Type,Other) "
				+ "VALUES(?,?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertDesktop);
			insertStmt.setString(1, desktop.getWeb_scraper_order());
			insertStmt.setString(2, desktop.getOperating_System());
			insertStmt.setString(3, desktop.getCPU_Model_Family());
			insertStmt.setString(4, desktop.getMemory_Size());
			insertStmt.setString(5, desktop.getHard_Disk_Size());
			insertStmt.setInt(6, desktop.getMemory_Type());
			insertStmt.setInt(7, desktop.getCPU_Type());
			insertStmt.setInt(8, desktop.getHard_Disk_Type());
			insertStmt.setString(9, desktop.getOther());
			insertStmt.executeUpdate();
			return desktop;
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


	public Desktop getDesktopByWeb_scraper_order(String web_scraper_order) throws SQLException {
		// To build an BlogUser object, we need the Persons record, too.
				String selectDesktop = 
						"SELECT Desktop.web_scraper_order AS web_scraper_order,"
						+ "ProductName,Name_hef,Price,Rating,Number_of_Reviews"
						+ "Desktop.Operating_System AS Operating_System,"
						+ "Desktop.CPU_Model_Family AS CPU_Model_Family,"
						+ "Desktop.Memory_Size AS Memory_Size,"
						+ "Desktop.Hard_Disk_Size AS Hard_Disk_Size,"
						+ "Desktop.Memory_Type AS Memory_Type,"
						+ "Desktop.CPU_Type AS CPU_Type,"
						+ "Desktop.Hard_Disk_Type AS Hard_Disk_Type"
						+ "Desktop.Other AS Other"
						+ " FROM ElectronicProductDetails "
						+ "INNER JOIN Desktop"
						+ " ON Desktop.web_scraper_order=ElectronicProductDetails.web_scraper_order "
						+ "WHERE Desktop.web_scraper_order=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectDesktop);
			selectStmt.setString(1,web_scraper_order);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String resultWeb_scraper_order = results.getString("web_scraper_order");
				String ProductName = results.getString("ProductName");
				String Name_hef = results.getString("Name_hef");
				double Price = results.getDouble("Price");
				double Rating = results.getDouble("Rating");
				int Number_of_Reviews = results.getInt("Number_of_Reviews");
				String Operating_System = results.getString("Operating_System");
				String CPU_Model_Family = results.getString("CPU_Model_Family");
				String Memory_Size = results.getString("Memory_Size");
				String Hard_Disk_Size = results.getString("Hard_Disk_Size");
				int Memory_Type = results.getInt("Memory_Type");
				int CPU_Type = results.getInt("CPU_Type");
				int Hard_Disk_Type = results.getInt("Hard_Disk_Type");
				String Other = results.getString("Other");
				Desktop desktop = new Desktop(resultWeb_scraper_order,ProductName,Name_hef,
						Price,Rating,Number_of_Reviews,Operating_System,CPU_Model_Family,
						Memory_Size,Hard_Disk_Size,Memory_Type,CPU_Type,Hard_Disk_Type,Other);
				return desktop;
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


	public List<Desktop> getDesktopByOperating_System(String operating_system)throws SQLException {
		List<Desktop> Desktops = new ArrayList<Desktop>();
		String selectDesktop = 
				"SELECT Desktop.web_scraper_order AS web_scraper_order,"
				+ "ProductName,Name_hef,Price,Rating,Number_of_Reviews"
				+ "Desktop.Operating_System AS Operating_System,"
				+ "Desktop.CPU_Model_Family AS CPU_Model_Family,"
				+ "Desktop.Memory_Size AS Memory_Size,"
				+ "Desktop.Hard_Disk_Size AS Hard_Disk_Size,"
				+ "Desktop.Memory_Type AS Memory_Type,"
				+ "Desktop.CPU_Type AS CPU_Type,"
				+ "Desktop.Hard_Disk_Type AS Hard_Disk_Type"
				+ "Desktop.Other AS Other"
				+ " FROM ElectronicProductDetails "
				+ "INNER JOIN Desktop"
				+ " ON Desktop.web_scraper_order=ElectronicProductDetails.web_scraper_order "
				+ "WHERE Desktop.Operating_System=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectDesktop);
			selectStmt.setString(1, operating_system);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String resultWeb_scraper_order = results.getString("web_scraper_order");
				String ProductName = results.getString("ProductName");
				String Name_hef = results.getString("Name_hef");
				double Price = results.getDouble("Price");
				double Rating = results.getDouble("Rating");
				int Number_of_Reviews = results.getInt("Number_of_Reviews");
				String Operating_System = results.getString("Operating_System");
				String CPU_Model_Family = results.getString("CPU_Model_Family");
				String Memory_Size = results.getString("Memory_Size");
				String Hard_Disk_Size = results.getString("Hard_Disk_Size");
				int Memory_Type = results.getInt("Memory_Type");
				int CPU_Type = results.getInt("CPU_Type");
				int Hard_Disk_Type = results.getInt("Hard_Disk_Type");
				String Other = results.getString("Other");
				Desktop desktop = new Desktop(resultWeb_scraper_order,ProductName,Name_hef,
						Price,Rating,Number_of_Reviews,Operating_System,CPU_Model_Family,
						Memory_Size,Hard_Disk_Size,Memory_Type,CPU_Type,Hard_Disk_Type,Other);
				Desktops.add(desktop);
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
		return Desktops;
	}
	public List<Desktop> getDesktopByCPU_Model_Family(String cpu_model_family)throws SQLException {
		List<Desktop> Desktops = new ArrayList<Desktop>();
		String selectDesktop = 
				"SELECT Desktop.web_scraper_order AS web_scraper_order,"
				+ "ProductName,Name_hef,Price,Rating,Number_of_Reviews"
				+ "Desktop.Operating_System AS Operating_System,"
				+ "Desktop.CPU_Model_Family AS CPU_Model_Family,"
				+ "Desktop.Memory_Size AS Memory_Size,"
				+ "Desktop.Hard_Disk_Size AS Hard_Disk_Size,"
				+ "Desktop.Memory_Type AS Memory_Type,"
				+ "Desktop.CPU_Type AS CPU_Type,"
				+ "Desktop.Hard_Disk_Type AS Hard_Disk_Type"
				+ "Desktop.Other AS Other"
				+ " FROM ElectronicProductDetails "
				+ "INNER JOIN Desktop"
				+ " ON Desktop.web_scraper_order=ElectronicProductDetails.web_scraper_order "
				+ "WHERE Desktop.CPU_Model_Family=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectDesktop);
			selectStmt.setString(1, cpu_model_family);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String resultWeb_scraper_order = results.getString("web_scraper_order");
				String ProductName = results.getString("ProductName");
				String Name_hef = results.getString("Name_hef");
				double Price = results.getDouble("Price");
				double Rating = results.getDouble("Rating");
				int Number_of_Reviews = results.getInt("Number_of_Reviews");
				String Operating_System = results.getString("Operating_System");
				String CPU_Model_Family = results.getString("CPU_Model_Family");
				String Memory_Size = results.getString("Memory_Size");
				String Hard_Disk_Size = results.getString("Hard_Disk_Size");
				int Memory_Type = results.getInt("Memory_Type");
				int CPU_Type = results.getInt("CPU_Type");
				int Hard_Disk_Type = results.getInt("Hard_Disk_Type");
				String Other = results.getString("Other");
				Desktop desktop = new Desktop(resultWeb_scraper_order,ProductName,Name_hef,
						Price,Rating,Number_of_Reviews,Operating_System,CPU_Model_Family,
						Memory_Size,Hard_Disk_Size,Memory_Type,CPU_Type,Hard_Disk_Type,Other);
				Desktops.add(desktop);
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
		return Desktops;
	}
	
	public List<Desktop> getLaptopByMemory_Size(String memory_size)throws SQLException {
		List<Desktop> Desktops = new ArrayList<Desktop>();
		String selectDesktop = 
				"SELECT Desktop.web_scraper_order AS web_scraper_order,"
				+ "ProductName,Name_hef,Price,Rating,Number_of_Reviews"
				+ "Desktop.Operating_System AS Operating_System,"
				+ "Desktop.CPU_Model_Family AS CPU_Model_Family,"
				+ "Desktop.Memory_Size AS Memory_Size,"
				+ "Desktop.Hard_Disk_Size AS Hard_Disk_Size,"
				+ "Desktop.Memory_Type AS Memory_Type,"
				+ "Desktop.CPU_Type AS CPU_Type,"
				+ "Desktop.Hard_Disk_Type AS Hard_Disk_Type"
				+ "Desktop.Other AS Other"
				+ " FROM ElectronicProductDetails "
				+ "INNER JOIN Desktop"
				+ " ON Desktop.web_scraper_order=ElectronicProductDetails.web_scraper_order "
				+ "WHERE Desktop.Memory_Size=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectDesktop);
			selectStmt.setString(1, memory_size);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String resultWeb_scraper_order = results.getString("web_scraper_order");
				String ProductName = results.getString("ProductName");
				String Name_hef = results.getString("Name_hef");
				double Price = results.getDouble("Price");
				double Rating = results.getDouble("Rating");
				int Number_of_Reviews = results.getInt("Number_of_Reviews");
				String Operating_System = results.getString("Operating_System");
				String CPU_Model_Family = results.getString("CPU_Model_Family");
				String Memory_Size = results.getString("Memory_Size");
				String Hard_Disk_Size = results.getString("Hard_Disk_Size");
				int Memory_Type = results.getInt("Memory_Type");
				int CPU_Type = results.getInt("CPU_Type");
				int Hard_Disk_Type = results.getInt("Hard_Disk_Type");
				String Other = results.getString("Other");
				Desktop desktop = new Desktop(resultWeb_scraper_order,ProductName,Name_hef,
						Price,Rating,Number_of_Reviews,Operating_System,CPU_Model_Family,
						Memory_Size,Hard_Disk_Size,Memory_Type,CPU_Type,Hard_Disk_Type,Other);
				Desktops.add(desktop);
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
		return Desktops;
	}
	public List<Desktop> getDesktopByHard_Disk_Size(String hard_disk_size)throws SQLException {
		List<Desktop> Desktops = new ArrayList<Desktop>();
		String selectDesktop = 
				"SELECT Desktop.web_scraper_order AS web_scraper_order,"
				+ "ProductName,Name_hef,Price,Rating,Number_of_Reviews"
				+ "Desktop.Operating_System AS Operating_System,"
				+ "Desktop.CPU_Model_Family AS CPU_Model_Family,"
				+ "Desktop.Memory_Size AS Memory_Size,"
				+ "Desktop.Hard_Disk_Size AS Hard_Disk_Size,"
				+ "Desktop.Memory_Type AS Memory_Type,"
				+ "Desktop.CPU_Type AS CPU_Type,"
				+ "Desktop.Hard_Disk_Type AS Hard_Disk_Type"
				+ "Desktop.Other AS Other"
				+ " FROM ElectronicProductDetails "
				+ "INNER JOIN Desktop"
				+ " ON Desktop.web_scraper_order=ElectronicProductDetails.web_scraper_order "
				+ "WHERE Desktop.Memory_Size=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectDesktop);
			selectStmt.setString(1, hard_disk_size);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String resultWeb_scraper_order = results.getString("web_scraper_order");
				String ProductName = results.getString("ProductName");
				String Name_hef = results.getString("Name_hef");
				double Price = results.getDouble("Price");
				double Rating = results.getDouble("Rating");
				int Number_of_Reviews = results.getInt("Number_of_Reviews");
				String Operating_System = results.getString("Operating_System");
				String CPU_Model_Family = results.getString("CPU_Model_Family");
				String Memory_Size = results.getString("Memory_Size");
				String Hard_Disk_Size = results.getString("Hard_Disk_Size");
				int Memory_Type = results.getInt("Memory_Type");
				int CPU_Type = results.getInt("CPU_Type");
				int Hard_Disk_Type = results.getInt("Hard_Disk_Type");
				String Other = results.getString("Other");
				Desktop desktop = new Desktop(resultWeb_scraper_order,ProductName,Name_hef,
						Price,Rating,Number_of_Reviews,Operating_System,CPU_Model_Family,
						Memory_Size,Hard_Disk_Size,Memory_Type,CPU_Type,Hard_Disk_Type,Other);
				Desktops.add(desktop);
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
		return Desktops;
	}
	
	public List<Desktop> getDesktopByMemory_Type(int memory_type)throws SQLException {
		List<Desktop> Desktops = new ArrayList<Desktop>();
		String selectDesktop = 
				"SELECT Desktop.web_scraper_order AS web_scraper_order,"
				+ "ProductName,Name_hef,Price,Rating,Number_of_Reviews"
				+ "Desktop.Operating_System AS Operating_System,"
				+ "Desktop.CPU_Model_Family AS CPU_Model_Family,"
				+ "Desktop.Memory_Size AS Memory_Size,"
				+ "Desktop.Hard_Disk_Size AS Hard_Disk_Size,"
				+ "Desktop.Memory_Type AS Memory_Type,"
				+ "Desktop.CPU_Type AS CPU_Type,"
				+ "Desktop.Hard_Disk_Type AS Hard_Disk_Type"
				+ "Desktop.Other AS Other"
				+ " FROM ElectronicProductDetails "
				+ "INNER JOIN Desktop"
				+ " ON Desktop.web_scraper_order=ElectronicProductDetails.web_scraper_order "
				+ "WHERE Desktop.Memory_Size=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectDesktop);
			selectStmt.setInt(1, memory_type);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String resultWeb_scraper_order = results.getString("web_scraper_order");
				String ProductName = results.getString("ProductName");
				String Name_hef = results.getString("Name_hef");
				double Price = results.getDouble("Price");
				double Rating = results.getDouble("Rating");
				int Number_of_Reviews = results.getInt("Number_of_Reviews");
				String Operating_System = results.getString("Operating_System");
				String CPU_Model_Family = results.getString("CPU_Model_Family");
				String Memory_Size = results.getString("Memory_Size");
				String Hard_Disk_Size = results.getString("Hard_Disk_Size");
				int Memory_Type = results.getInt("Memory_Type");
				int CPU_Type = results.getInt("CPU_Type");
				int Hard_Disk_Type = results.getInt("Hard_Disk_Type");
				String Other = results.getString("Other");
				Desktop desktop = new Desktop(resultWeb_scraper_order,ProductName,Name_hef,
						Price,Rating,Number_of_Reviews,Operating_System,CPU_Model_Family,
						Memory_Size,Hard_Disk_Size,Memory_Type,CPU_Type,Hard_Disk_Type,Other);
				Desktops.add(desktop);
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
		return Desktops;
	}
	public List<Desktop> getDesktopByCPU_Type(int cpu_type)throws SQLException {
		List<Desktop> Desktops = new ArrayList<Desktop>();
		String selectDesktop = 
				"SELECT Desktop.web_scraper_order AS web_scraper_order,"
				+ "ProductName,Name_hef,Price,Rating,Number_of_Reviews"
				+ "Desktop.Operating_System AS Operating_System,"
				+ "Desktop.CPU_Model_Family AS CPU_Model_Family,"
				+ "Desktop.Memory_Size AS Memory_Size,"
				+ "Desktop.Hard_Disk_Size AS Hard_Disk_Size,"
				+ "Desktop.Memory_Type AS Memory_Type,"
				+ "Desktop.CPU_Type AS CPU_Type,"
				+ "Desktop.Hard_Disk_Type AS Hard_Disk_Type"
				+ "Desktop.Other AS Other"
				+ " FROM ElectronicProductDetails "
				+ "INNER JOIN Desktop"
				+ " ON Desktop.web_scraper_order=ElectronicProductDetails.web_scraper_order "
				+ "WHERE Desktop.Memory_Size=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectDesktop);
			selectStmt.setInt(1, cpu_type);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String resultWeb_scraper_order = results.getString("web_scraper_order");
				String ProductName = results.getString("ProductName");
				String Name_hef = results.getString("Name_hef");
				double Price = results.getDouble("Price");
				double Rating = results.getDouble("Rating");
				int Number_of_Reviews = results.getInt("Number_of_Reviews");
				String Operating_System = results.getString("Operating_System");
				String CPU_Model_Family = results.getString("CPU_Model_Family");
				String Memory_Size = results.getString("Memory_Size");
				String Hard_Disk_Size = results.getString("Hard_Disk_Size");
				int Memory_Type = results.getInt("Memory_Type");
				int CPU_Type = results.getInt("CPU_Type");
				int Hard_Disk_Type = results.getInt("Hard_Disk_Type");
				String Other = results.getString("Other");
				Desktop desktop = new Desktop(resultWeb_scraper_order,ProductName,Name_hef,
						Price,Rating,Number_of_Reviews,Operating_System,CPU_Model_Family,
						Memory_Size,Hard_Disk_Size,Memory_Type,CPU_Type,Hard_Disk_Type,Other);
				Desktops.add(desktop);
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
		return Desktops;
	}
	public List<Desktop> getDesktopByHard_Disk_Type(int hard_disk_type)throws SQLException {
		List<Desktop> Desktops = new ArrayList<Desktop>();
		String selectLaptop = 
				"SELECT Laptop.web_scraper_order AS web_scraper_order,"
				+ "ProductName,Name_hef,Price,Rating,Number_of_Reviews"
				+ "Laptop.Producer AS Producer,"
				+ "Laptop.Display_Size AS Display_Size,"
				+ "Laptop.Operating_System AS Operating_System,"
				+ "Laptop.CPU_Model_Family AS CPU_Model_Family,"
				+ "Laptop.Memory_Size AS Memory_Size,"
				+ "Laptop.Hard_Disk_Size AS Hard_Disk_Size,"
				+ "Laptop.Memory_Type AS Memory_Type,"
				+ "Laptop.CPU_Type AS CPU_Type,"
				+ "Laptop.Hard_Disk_Type AS Hard_Disk_Type"
				+ " FROM ElectronicProductDetails "
				+ "INNER JOIN Laptop"
				+ " ON Laptop.web_scraper_order=ElectronicProductDetails.web_scraper_order "
				+ "WHERE Laptop.Hard_Disk_Type=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectLaptop);
			selectStmt.setInt(1, hard_disk_type);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String resultWeb_scraper_order = results.getString("web_scraper_order");
				String ProductName = results.getString("ProductName");
				String Name_hef = results.getString("Name_hef");
				double Price = results.getDouble("Price");
				double Rating = results.getDouble("Rating");
				int Number_of_Reviews = results.getInt("Number_of_Reviews");
				String Operating_System = results.getString("Operating_System");
				String CPU_Model_Family = results.getString("CPU_Model_Family");
				String Memory_Size = results.getString("Memory_Size");
				String Hard_Disk_Size = results.getString("Hard_Disk_Size");
				int Memory_Type = results.getInt("Memory_Type");
				int CPU_Type = results.getInt("CPU_Type");
				int Hard_Disk_Type = results.getInt("Hard_Disk_Type");
				String Other = results.getString("Other");
				Desktop desktop = new Desktop(resultWeb_scraper_order,ProductName,Name_hef,
						Price,Rating,Number_of_Reviews,Operating_System,CPU_Model_Family,
						Memory_Size,Hard_Disk_Size,Memory_Type,CPU_Type,Hard_Disk_Type,Other);
				Desktops.add(desktop);
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
		return Desktops;
	}
	
	public List<Desktop> getDesktopByParameters(String operating, String CPU_Model_family,
			String memory_size, String Hard_DiskSize)throws SQLException {
		List<Desktop> desktops = new ArrayList<Desktop>();
		String selectDesktop = 
				"SELECT Desktop.web_scraper_order AS web_scraper_order,"
				+ "ProductName,Name_href,Price,Rating,Number_of_Reviews,"
				+ "Desktop.Operating_System AS Operating_System,"
				+ "Desktop.CPU_Model_Family AS CPU_Model_Family,"
				+ "Desktop.Memory_Size AS Memory_Size,"
				+ "Desktop.HardDisk_Size AS Hard_Disk_Size,"
				+ "Desktop.Memory_Type AS Memory_Type,"
				+ "Desktop.CPU_Type AS CPU_Type,"
				+ "Desktop.Hard_Disk_Type AS Hard_Disk_Type,"
				+ "Desktop.Other AS Other"
				+ " FROM ElectronicProductDetails "
				+ "INNER JOIN Desktop"
				+ " ON Desktop.web_scraper_order=ElectronicProductDetails.web_scraper_order "
				+ "WHERE Desktop.Operating_System LIKE ? "
		        + "and Desktop.CPU_Model_Family LIKE ? "
		        + "and Desktop.Memory_Size LIKE ? "
	            + "and Desktop.HardDisk_Size LIKE ? ;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectDesktop);
			selectStmt.setString(1, "%"+operating+"%");
			selectStmt.setString(2, "%"+CPU_Model_family+"%");
			selectStmt.setString(3, "%"+memory_size+"%");
			selectStmt.setString(4, "%"+Hard_DiskSize+"%");
			results = selectStmt.executeQuery();
			while(results.next()) {
				String resultWeb_scraper_order = results.getString("web_scraper_order");
				String ProductName = results.getString("ProductName");
				String Name_hef = results.getString("Name_href");
				double Price = results.getDouble("Price");
				double Rating = results.getDouble("Rating");
				int Number_of_Reviews = results.getInt("Number_of_Reviews");
				String Operating_System = results.getString("Operating_System");
				String CPU_Model_Family = results.getString("CPU_Model_Family");
				String Memory_Size = results.getString("Memory_Size");
				String Hard_Disk_Size = results.getString("Hard_Disk_Size");
				int Memory_Type = results.getInt("Memory_Type");
				int CPU_Type = results.getInt("CPU_Type");
				int Hard_Disk_Type = results.getInt("Hard_Disk_Type");
				String Other = results.getString("Other");
				Desktop desktop = new Desktop(resultWeb_scraper_order,ProductName,Name_hef,
						Price,Rating,Number_of_Reviews,Operating_System,CPU_Model_Family,
						Memory_Size,Hard_Disk_Size,Memory_Type,CPU_Type,Hard_Disk_Type,Other);
				desktops.add(desktop);
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
		return desktops;
	}
	
	public Desktop delete(Desktop desktop) throws SQLException {
		String deleteDesktop = "DELETE FROM Desktop WHERE web_scraper_order=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteDesktop);
			deleteStmt.setString(1, desktop.getWeb_scraper_order());
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
