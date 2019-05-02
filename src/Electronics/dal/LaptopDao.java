package Electronics.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Electronics.model.*;

public class LaptopDao extends ElectronicProductDetailsDao {
	// Single pattern: instantiation is limited to one object.
	private static LaptopDao instance = null;
	protected LaptopDao() {
		super();
	}
	public static LaptopDao getInstance() {
		if(instance == null) {
			instance = new LaptopDao();
		}
		return instance;
	}

	public Laptop create(Laptop laptop) throws SQLException {
		// Insert into the superclass table first.
		create(new ElectronicProductDetails(laptop.getWeb_scraper_order(), laptop.getProductName(),
				laptop.getName_hef(),laptop.getPrice(),laptop.getRating(),laptop.getNumber_of_Reviews()));
		String insertLaptop = "INSERT INTO Laptop(web_scraper_order,Producer,Display_Size,Operating_System,"
				+ "CPU_Model_Family,Memory_Size,Hard_Disk_Size,Memory_Type,CPU_Type,Hard_Disk_Type) "
				+ "VALUES(?,?,?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertLaptop);
			insertStmt.setString(1, laptop.getWeb_scraper_order());
			insertStmt.setString(2, laptop.getProducer());
			insertStmt.setString(3, laptop.getDisplay_Size());
			insertStmt.setString(4, laptop.getOperating_System());
			insertStmt.setString(5, laptop.getCPU_Model_Family());
			insertStmt.setString(6, laptop.getMemory_Size());
			insertStmt.setString(7, laptop.getHard_Disk_Size());
			insertStmt.setInt(8, laptop.getMemory_Type());
			insertStmt.setInt(9, laptop.getCPU_Type());
			insertStmt.setInt(10, laptop.getHard_Disk_Type());
			insertStmt.executeUpdate();
			return laptop;
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


	public Laptop getLaptopByWeb_scraper_order(String web_scraper_order) throws SQLException {
		// To build an BlogUser object, we need the Persons record, too.
				String selectLaptop = 
						"SELECT Laptop.web_scraper_order AS web_scraper_order,"
						+ "ProductName,Name_href,Price,Rating,Number_of_Reviews,"
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
						+ "WHERE Laptop.web_scraper_order=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectLaptop);
			selectStmt.setString(1,web_scraper_order);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String resultWeb_scraper_order = results.getString("web_scraper_order");
				String ProductName = results.getString("ProductName");
				String Name_hef = results.getString("Name_href");
				double Price = results.getDouble("Price");
				double Rating = results.getDouble("Rating");
				int Number_of_Reviews = results.getInt("Number_of_Reviews");
				String Producer = results.getString("Producer");
				String Display_Size = results.getString("Display_Size");
				String Operating_System = results.getString("Operating_System");
				String CPU_Model_Family = results.getString("CPU_Model_Family");
				String Memory_Size = results.getString("Memory_Size");
				String Hard_Disk_Size = results.getString("Hard_Disk_Size");
				int Memory_Type = results.getInt("Memory_Type");
				int CPU_Type = results.getInt("CPU_Type");
				int Hard_Disk_Type = results.getInt("Hard_Disk_Type");
				Laptop laptop = new Laptop(resultWeb_scraper_order,ProductName,Name_hef,
						Price,Rating,Number_of_Reviews,Producer,Display_Size,Operating_System,CPU_Model_Family,
						Memory_Size,Hard_Disk_Size,Memory_Type,CPU_Type,Hard_Disk_Type);
				return laptop;
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

	public List<Laptop> getLaptopByProducer(String producer)throws SQLException {
		List<Laptop> laptops = new ArrayList<Laptop>();
		String selectLaptop = 
				"SELECT Laptop.web_scraper_order AS web_scraper_order,"
				+ "ProductName,Name_href,Price,Rating,Number_of_Reviews,"
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
				+ "WHERE Laptop.Producer=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectLaptop);
			selectStmt.setString(1, producer);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String resultWeb_scraper_order = results.getString("web_scraper_order");
				String ProductName = results.getString("ProductName");
				String Name_hef = results.getString("Name_href");
				double Price = results.getDouble("Price");
				double Rating = results.getDouble("Rating");
				int Number_of_Reviews = results.getInt("Number_of_Reviews");
				String Producer = results.getString("Producer");
				String Display_Size = results.getString("Display_Size");
				String Operating_System = results.getString("Operating_System");
				String CPU_Model_Family = results.getString("CPU_Model_Family");
				String Memory_Size = results.getString("Memory_Size");
				String Hard_Disk_Size = results.getString("Hard_Disk_Size");
				int Memory_Type = results.getInt("Memory_Type");
				int CPU_Type = results.getInt("CPU_Type");
				int Hard_Disk_Type = results.getInt("Hard_Disk_Type");
				Laptop laptop = new Laptop(resultWeb_scraper_order,ProductName,Name_hef,
						Price,Rating,Number_of_Reviews,Producer,Display_Size,Operating_System,CPU_Model_Family,
						Memory_Size,Hard_Disk_Size,Memory_Type,CPU_Type,Hard_Disk_Type);
				laptops.add(laptop);
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
		return laptops;
	}
	
	public List<Laptop> getLaptopByDisplay_Size(String display_size)throws SQLException {
		List<Laptop> laptops = new ArrayList<Laptop>();
		String selectLaptop = 
				"SELECT Laptop.web_scraper_order AS web_scraper_order,"
				+ "ProductName,Name_href,Price,Rating,Number_of_Reviews,"
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
				+ "WHERE Laptop.Display_Size=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectLaptop);
			selectStmt.setString(1, display_size);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String resultWeb_scraper_order = results.getString("web_scraper_order");
				String ProductName = results.getString("ProductName");
				String Name_hef = results.getString("Name_href");
				double Price = results.getDouble("Price");
				double Rating = results.getDouble("Rating");
				int Number_of_Reviews = results.getInt("Number_of_Reviews");
				String Producer = results.getString("Producer");
				String Display_Size = results.getString("Display_Size");
				String Operating_System = results.getString("Operating_System");
				String CPU_Model_Family = results.getString("CPU_Model_Family");
				String Memory_Size = results.getString("Memory_Size");
				String Hard_Disk_Size = results.getString("Hard_Disk_Size");
				int Memory_Type = results.getInt("Memory_Type");
				int CPU_Type = results.getInt("CPU_Type");
				int Hard_Disk_Type = results.getInt("Hard_Disk_Type");
				Laptop laptop = new Laptop(resultWeb_scraper_order,ProductName,Name_hef,
						Price,Rating,Number_of_Reviews,Producer,Display_Size,Operating_System,CPU_Model_Family,
						Memory_Size,Hard_Disk_Size,Memory_Type,CPU_Type,Hard_Disk_Type);
				laptops.add(laptop);
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
		return laptops;
	}
	public List<Laptop> getLaptopByOperating_System(String operating_system)throws SQLException {
		List<Laptop> laptops = new ArrayList<Laptop>();
		String selectLaptop = 
				"SELECT Laptop.web_scraper_order AS web_scraper_order,"
				+ "ProductName,Name_href,Price,Rating,Number_of_Reviews,"
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
				+ "WHERE Laptop.Operating_System=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectLaptop);
			selectStmt.setString(1, operating_system);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String resultWeb_scraper_order = results.getString("web_scraper_order");
				String ProductName = results.getString("ProductName");
				String Name_hef = results.getString("Name_href");
				double Price = results.getDouble("Price");
				double Rating = results.getDouble("Rating");
				int Number_of_Reviews = results.getInt("Number_of_Reviews");
				String Producer = results.getString("Producer");
				String Display_Size = results.getString("Display_Size");
				String Operating_System = results.getString("Operating_System");
				String CPU_Model_Family = results.getString("CPU_Model_Family");
				String Memory_Size = results.getString("Memory_Size");
				String Hard_Disk_Size = results.getString("Hard_Disk_Size");
				int Memory_Type = results.getInt("Memory_Type");
				int CPU_Type = results.getInt("CPU_Type");
				int Hard_Disk_Type = results.getInt("Hard_Disk_Type");
				Laptop laptop = new Laptop(resultWeb_scraper_order,ProductName,Name_hef,
						Price,Rating,Number_of_Reviews,Producer,Display_Size,Operating_System,CPU_Model_Family,
						Memory_Size,Hard_Disk_Size,Memory_Type,CPU_Type,Hard_Disk_Type);
				laptops.add(laptop);
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
		return laptops;
	}
	public List<Laptop> getLaptopByCPU_Model_Family(String cpu_model_family)throws SQLException {
		List<Laptop> laptops = new ArrayList<Laptop>();
		String selectLaptop = 
				"SELECT Laptop.web_scraper_order AS web_scraper_order,"
				+ "ProductName,Name_href,Price,Rating,Number_of_Reviews,"
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
				+ "WHERE Laptop.CPU_Model_Family=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectLaptop);
			selectStmt.setString(1, cpu_model_family);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String resultWeb_scraper_order = results.getString("web_scraper_order");
				String ProductName = results.getString("ProductName");
				String Name_hef = results.getString("Name_href");
				double Price = results.getDouble("Price");
				double Rating = results.getDouble("Rating");
				int Number_of_Reviews = results.getInt("Number_of_Reviews");
				String Producer = results.getString("Producer");
				String Display_Size = results.getString("Display_Size");
				String Operating_System = results.getString("Operating_System");
				String CPU_Model_Family = results.getString("CPU_Model_Family");
				String Memory_Size = results.getString("Memory_Size");
				String Hard_Disk_Size = results.getString("Hard_Disk_Size");
				int Memory_Type = results.getInt("Memory_Type");
				int CPU_Type = results.getInt("CPU_Type");
				int Hard_Disk_Type = results.getInt("Hard_Disk_Type");
				Laptop laptop = new Laptop(resultWeb_scraper_order,ProductName,Name_hef,
						Price,Rating,Number_of_Reviews,Producer,Display_Size,Operating_System,CPU_Model_Family,
						Memory_Size,Hard_Disk_Size,Memory_Type,CPU_Type,Hard_Disk_Type);
				laptops.add(laptop);
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
		return laptops;
	}
	
	public List<Laptop> getLaptopByMemory_Size(String memory_size)throws SQLException {
		List<Laptop> laptops = new ArrayList<Laptop>();
		String selectLaptop = 
				"SELECT Laptop.web_scraper_order AS web_scraper_order,"
				+ "ProductName,Name_href,Price,Rating,Number_of_Reviews,"
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
				+ "WHERE Laptop.Memory_Size=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectLaptop);
			selectStmt.setString(1, memory_size);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String resultWeb_scraper_order = results.getString("web_scraper_order");
				String ProductName = results.getString("ProductName");
				String Name_hef = results.getString("Name_href");
				double Price = results.getDouble("Price");
				double Rating = results.getDouble("Rating");
				int Number_of_Reviews = results.getInt("Number_of_Reviews");
				String Producer = results.getString("Producer");
				String Display_Size = results.getString("Display_Size");
				String Operating_System = results.getString("Operating_System");
				String CPU_Model_Family = results.getString("CPU_Model_Family");
				String Memory_Size = results.getString("Memory_Size");
				String Hard_Disk_Size = results.getString("Hard_Disk_Size");
				int Memory_Type = results.getInt("Memory_Type");
				int CPU_Type = results.getInt("CPU_Type");
				int Hard_Disk_Type = results.getInt("Hard_Disk_Type");
				Laptop laptop = new Laptop(resultWeb_scraper_order,ProductName,Name_hef,
						Price,Rating,Number_of_Reviews,Producer,Display_Size,Operating_System,CPU_Model_Family,
						Memory_Size,Hard_Disk_Size,Memory_Type,CPU_Type,Hard_Disk_Type);
				laptops.add(laptop);
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
		return laptops;
	}
	public List<Laptop> getLaptopByHard_Disk_Size(String hard_disk_size)throws SQLException {
		List<Laptop> laptops = new ArrayList<Laptop>();
		String selectLaptop = 
				"SELECT Laptop.web_scraper_order AS web_scraper_order,"
				+ "ProductName,Name_href,Price,Rating,Number_of_Reviews,"
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
				+ "WHERE Laptop.Hard_Disk_Size=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectLaptop);
			selectStmt.setString(1, hard_disk_size);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String resultWeb_scraper_order = results.getString("web_scraper_order");
				String ProductName = results.getString("ProductName");
				String Name_hef = results.getString("Name_href");
				double Price = results.getDouble("Price");
				double Rating = results.getDouble("Rating");
				int Number_of_Reviews = results.getInt("Number_of_Reviews");
				String Producer = results.getString("Producer");
				String Display_Size = results.getString("Display_Size");
				String Operating_System = results.getString("Operating_System");
				String CPU_Model_Family = results.getString("CPU_Model_Family");
				String Memory_Size = results.getString("Memory_Size");
				String Hard_Disk_Size = results.getString("Hard_Disk_Size");
				int Memory_Type = results.getInt("Memory_Type");
				int CPU_Type = results.getInt("CPU_Type");
				int Hard_Disk_Type = results.getInt("Hard_Disk_Type");
				Laptop laptop = new Laptop(resultWeb_scraper_order,ProductName,Name_hef,
						Price,Rating,Number_of_Reviews,Producer,Display_Size,Operating_System,CPU_Model_Family,
						Memory_Size,Hard_Disk_Size,Memory_Type,CPU_Type,Hard_Disk_Type);
				laptops.add(laptop);
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
		return laptops;
	}
	
	public List<Laptop> getLaptopByMemory_Type(int memory_type)throws SQLException {
		List<Laptop> laptops = new ArrayList<Laptop>();
		String selectLaptop = 
				"SELECT Laptop.web_scraper_order AS web_scraper_order,"
				+ "ProductName,Name_href,Price,Rating,Number_of_Reviews,"
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
				+ "WHERE Laptop.Memory_Type=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectLaptop);
			selectStmt.setInt(1, memory_type);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String resultWeb_scraper_order = results.getString("web_scraper_order");
				String ProductName = results.getString("ProductName");
				String Name_hef = results.getString("Name_href");
				double Price = results.getDouble("Price");
				double Rating = results.getDouble("Rating");
				int Number_of_Reviews = results.getInt("Number_of_Reviews");
				String Producer = results.getString("Producer");
				String Display_Size = results.getString("Display_Size");
				String Operating_System = results.getString("Operating_System");
				String CPU_Model_Family = results.getString("CPU_Model_Family");
				String Memory_Size = results.getString("Memory_Size");
				String Hard_Disk_Size = results.getString("Hard_Disk_Size");
				int Memory_Type = results.getInt("Memory_Type");
				int CPU_Type = results.getInt("CPU_Type");
				int Hard_Disk_Type = results.getInt("Hard_Disk_Type");
				Laptop laptop = new Laptop(resultWeb_scraper_order,ProductName,Name_hef,
						Price,Rating,Number_of_Reviews,Producer,Display_Size,Operating_System,CPU_Model_Family,
						Memory_Size,Hard_Disk_Size,Memory_Type,CPU_Type,Hard_Disk_Type);
				laptops.add(laptop);
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
		return laptops;
	}
	public List<Laptop> getLaptopByCPU_Type(int cpu_type)throws SQLException {
		List<Laptop> laptops = new ArrayList<Laptop>();
		String selectLaptop = 
				"SELECT Laptop.web_scraper_order AS web_scraper_order,"
				+ "ProductName,Name_href,Price,Rating,Number_of_Reviews,"
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
				+ "WHERE Laptop.CPU_Type=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectLaptop);
			selectStmt.setInt(1, cpu_type);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String resultWeb_scraper_order = results.getString("web_scraper_order");
				String ProductName = results.getString("ProductName");
				String Name_hef = results.getString("Name_href");
				double Price = results.getDouble("Price");
				double Rating = results.getDouble("Rating");
				int Number_of_Reviews = results.getInt("Number_of_Reviews");
				String Producer = results.getString("Producer");
				String Display_Size = results.getString("Display_Size");
				String Operating_System = results.getString("Operating_System");
				String CPU_Model_Family = results.getString("CPU_Model_Family");
				String Memory_Size = results.getString("Memory_Size");
				String Hard_Disk_Size = results.getString("Hard_Disk_Size");
				int Memory_Type = results.getInt("Memory_Type");
				int CPU_Type = results.getInt("CPU_Type");
				int Hard_Disk_Type = results.getInt("Hard_Disk_Type");
				Laptop laptop = new Laptop(resultWeb_scraper_order,ProductName,Name_hef,
						Price,Rating,Number_of_Reviews,Producer,Display_Size,Operating_System,CPU_Model_Family,
						Memory_Size,Hard_Disk_Size,Memory_Type,CPU_Type,Hard_Disk_Type);
				laptops.add(laptop);
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
		return laptops;
	}
	public List<Laptop> getLaptopByHard_Disk_Type(int hard_disk_type)throws SQLException {
		List<Laptop> laptops = new ArrayList<Laptop>();
		String selectLaptop = 
				"SELECT Laptop.web_scraper_order AS web_scraper_order,"
				+ "ProductName,Name_href,Price,Rating,Number_of_Reviews,"
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
				String Name_hef = results.getString("Name_href");
				double Price = results.getDouble("Price");
				double Rating = results.getDouble("Rating");
				int Number_of_Reviews = results.getInt("Number_of_Reviews");
				String Producer = results.getString("Producer");
				String Display_Size = results.getString("Display_Size");
				String Operating_System = results.getString("Operating_System");
				String CPU_Model_Family = results.getString("CPU_Model_Family");
				String Memory_Size = results.getString("Memory_Size");
				String Hard_Disk_Size = results.getString("Hard_Disk_Size");
				int Memory_Type = results.getInt("Memory_Type");
				int CPU_Type = results.getInt("CPU_Type");
				int Hard_Disk_Type = results.getInt("Hard_Disk_Type");
				Laptop laptop = new Laptop(resultWeb_scraper_order,ProductName,Name_hef,
						Price,Rating,Number_of_Reviews,Producer,Display_Size,Operating_System,CPU_Model_Family,
						Memory_Size,Hard_Disk_Size,Memory_Type,CPU_Type,Hard_Disk_Type);
				laptops.add(laptop);
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
		return laptops;
	}
	
	public List<Laptop> getLaptopByParameters(String producer, String display_size, String operating, String CPU_Model_family,
			String memory_size, String Hard_DiskSize)throws SQLException {
		List<Laptop> laptops = new ArrayList<Laptop>();
		String selectLaptop = 
				"SELECT Laptop.web_scraper_order AS web_scraper_order,"
				+ "ProductName,Name_href,Price,Rating,Number_of_Reviews,"
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
				+ "WHERE Laptop.Producer LIKE ? "
				+ "and Laptop.Display_Size LIKE ? "
		        + "and Laptop.Operating_System LIKE ? "
		        + "and Laptop.CPU_Model_Family LIKE ? "
		        + "and Laptop.Memory_Size LIKE ? "
	            + "and Laptop.Hard_Disk_Size LIKE ? ;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectLaptop);
			selectStmt.setString(1, "%"+producer+"%");
			selectStmt.setString(2, "%"+display_size+"%");
			selectStmt.setString(3, "%"+operating+"%");
			selectStmt.setString(4, "%"+CPU_Model_family+"%");
			selectStmt.setString(5, "%"+memory_size+"%");
			selectStmt.setString(6, "%"+Hard_DiskSize+"%");
			results = selectStmt.executeQuery();
			while(results.next()) {
				String resultWeb_scraper_order = results.getString("web_scraper_order");
				String ProductName = results.getString("ProductName");
				String Name_hef = results.getString("Name_href");
				double Price = results.getDouble("Price");
				double Rating = results.getDouble("Rating");
				int Number_of_Reviews = results.getInt("Number_of_Reviews");
				String Producer = results.getString("Producer");
				String Display_Size = results.getString("Display_Size");
				String Operating_System = results.getString("Operating_System");
				String CPU_Model_Family = results.getString("CPU_Model_Family");
				String Memory_Size = results.getString("Memory_Size");
				String Hard_Disk_Size = results.getString("Hard_Disk_Size");
				int Memory_Type = results.getInt("Memory_Type");
				int CPU_Type = results.getInt("CPU_Type");
				int Hard_Disk_Type = results.getInt("Hard_Disk_Type");
				Laptop laptop = new Laptop(resultWeb_scraper_order,ProductName,Name_hef,
						Price,Rating,Number_of_Reviews,Producer,Display_Size,Operating_System,CPU_Model_Family,
						Memory_Size,Hard_Disk_Size,Memory_Type,CPU_Type,Hard_Disk_Type);
				laptops.add(laptop);
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
		return laptops;
	}
	
	public Laptop delete(Laptop laptop) throws SQLException {
		String deleteLaptop = "DELETE FROM Laptop WHERE web_scraper_order=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteLaptop);
			deleteStmt.setString(1, laptop.getWeb_scraper_order());
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
