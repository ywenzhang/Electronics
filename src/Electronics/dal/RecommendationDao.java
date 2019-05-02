package Electronics.dal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Electronics.model.*;
public class RecommendationDao{
	
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static RecommendationDao instance = null;
	protected RecommendationDao() {
		connectionManager = new ConnectionManager();
	}
	public static RecommendationDao getInstance() {
		if(instance == null) {
			instance = new RecommendationDao();
		}
		return instance;
	}
	
	public List<Desktop> getDesktopBudget(String low, String high)throws SQLException {
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
				+ "WHERE Price>=? and Price<=? "
				+ "ORDER BY Rating*Number_of_Reviews DESC "
				+ "LIMIT 10;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectDesktop);
			int lowN = Integer.parseInt(low);
			int highN = Integer.parseInt(high);
			selectStmt.setInt(1, lowN);
			selectStmt.setInt(2, highN);
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
	public List<Desktop> getDesktopTop(int num)throws SQLException {
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
				+ "ORDER BY Rating*Number_of_Reviews DESC "
				+ "LIMIT ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectDesktop);
			selectStmt.setInt(1, num);
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
	public List<Laptop> getLaptopBudget(String low, String high)throws SQLException {
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
				+ "WHERE Price>=? and Price<=? "
				+ "ORDER BY Rating*Number_of_Reviews DESC "
				+ "LIMIT 10;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectLaptop);
			int lowN = Integer.parseInt(low);
			int highN = Integer.parseInt(high);
			selectStmt.setInt(1, lowN);
			selectStmt.setInt(2, highN);
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
	public List<Laptop> getLaptopTop(int num)throws SQLException {
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
				+ "ORDER BY Rating*Number_of_Reviews DESC "
				+ "LIMIT ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectLaptop);
			selectStmt.setInt(1, num);
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
	public List<CPUProcessor> getCPUProcessorByBudget(String low, String high)throws SQLException {
		List<CPUProcessor> cpuprocessors = new ArrayList<CPUProcessor>();
		String selectCpu = 
				"SELECT CPUProcessor.web_scraper_order AS web_scraper_order,"
				+ "ProductName,Name_href,Price,Rating,Number_of_Reviews,"
				+ "CPUProcessor.Image AS Image,"
				+ "CPUProcessor.Series AS Series,"
				+ "CPUProcessor.Cache AS Cache,"
				+ "CPUProcessor.CacheSize AS CacheSize,"
				+ "CPUProcessor.CacheType AS CacheType,"
				+ "CPUProcessor.CPUType AS CPUType"
				+ " FROM ElectronicProductDetails "
				+ "INNER JOIN CPUProcessor"
				+ " ON CPUProcessor.web_scraper_order=ElectronicProductDetails.web_scraper_order "
				+ "WHERE Price>=? and Price<=? "
				+ "ORDER BY Rating*Number_of_Reviews DESC "
				+ "LIMIT 10;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCpu);
			int lowN = Integer.parseInt(low);
			int highN = Integer.parseInt(high);
			selectStmt.setInt(1, lowN);
			selectStmt.setInt(2, highN);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String resultWeb_scraper_order = results.getString("web_scraper_order");
				String ProductName = results.getString("ProductName");
				String Name_hef = results.getString("Name_href");
				double Price = results.getDouble("Price");
				double Rating = results.getDouble("Rating");
				int Number_of_Reviews = results.getInt("Number_of_Reviews");
				String Image = results.getString("Image");
				String Series = results.getString("Series");
				String Cache = results.getString("Cache");
				String CacheSize = results.getString("CacheSize");
				int CacheType = results.getInt("CacheType");
				int CPUType = results.getInt("CPUType");
				CPUProcessor cpuprocessor = new CPUProcessor(resultWeb_scraper_order,ProductName,Name_hef,
						Price,Rating,Number_of_Reviews,Image,Series,Cache,CacheSize,
						CacheType,CPUType);
				cpuprocessors.add(cpuprocessor);
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
		return cpuprocessors;
	}
	public List<CPUProcessor> getCPUProcessorTop(int num)throws SQLException {
		List<CPUProcessor> cpuprocessors = new ArrayList<CPUProcessor>();
		String selectCpu = 
				"SELECT CPUProcessor.web_scraper_order AS web_scraper_order,"
				+ "ProductName,Name_href,Price,Rating,Number_of_Reviews,"
				+ "CPUProcessor.Image AS Image,"
				+ "CPUProcessor.Series AS Series,"
				+ "CPUProcessor.Cache AS Cache,"
				+ "CPUProcessor.CacheSize AS CacheSize,"
				+ "CPUProcessor.CacheType AS CacheType,"
				+ "CPUProcessor.CPUType AS CPUType"
				+ " FROM ElectronicProductDetails "
				+ "INNER JOIN CPUProcessor"
				+ " ON CPUProcessor.web_scraper_order=ElectronicProductDetails.web_scraper_order "
				+ "ORDER BY Rating*Number_of_Reviews DESC "
				+ "LIMIT ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCpu);
			selectStmt.setInt(1, num);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String resultWeb_scraper_order = results.getString("web_scraper_order");
				String ProductName = results.getString("ProductName");
				String Name_hef = results.getString("Name_href");
				double Price = results.getDouble("Price");
				double Rating = results.getDouble("Rating");
				int Number_of_Reviews = results.getInt("Number_of_Reviews");
				String Image = results.getString("Image");
				String Series = results.getString("Series");
				String Cache = results.getString("Cache");
				String CacheSize = results.getString("CacheSize");
				int CacheType = results.getInt("CacheType");
				int CPUType = results.getInt("CPUType");
				CPUProcessor cpuprocessor = new CPUProcessor(resultWeb_scraper_order,ProductName,Name_hef,
						Price,Rating,Number_of_Reviews,Image,Series,Cache,CacheSize,
						CacheType,CPUType);
				cpuprocessors.add(cpuprocessor);
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
		return cpuprocessors;
	}
	public List<RamMemory> getRamMemoryByBudget(String low, String high)throws SQLException {
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
						+ "WHERE Price>=? and Price<=? "
						+ "ORDER BY Rating*Number_of_Reviews DESC "
						+ "LIMIT 10;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRamMemory);
			int lowN = Integer.parseInt(low);
			int highN = Integer.parseInt(high);
			selectStmt.setInt(1, lowN);
			selectStmt.setInt(2, highN);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String resultWeb_scraper_order = results.getString("web_scraper_order");
				String ProductName = results.getString("ProductName");
				String Name_hef = results.getString("Name_href");
				double Price = results.getDouble("Price");
				double Rating = results.getDouble("Rating");
				int Number_of_Reviews = results.getInt("Number_of_Reviews");
				System.out.print(results.getString("DDR"));
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
	public List<RamMemory> getRamMemoryTop(int num)throws SQLException {
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
						+ "ORDER BY Rating*Number_of_Reviews DESC "
						+ "LIMIT ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRamMemory);
			selectStmt.setInt(1,num);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String resultWeb_scraper_order = results.getString("web_scraper_order");
				String ProductName = results.getString("ProductName");
				String Name_hef = results.getString("Name_href");
				double Price = results.getDouble("Price");
				double Rating = results.getDouble("Rating");
				int Number_of_Reviews = results.getInt("Number_of_Reviews");
				System.out.print(results.getString("DDR"));
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
	public List<GraphicCard> getGraphicCardByBudget(String low, String high)throws SQLException {
		List<GraphicCard> graphiccards = new ArrayList<GraphicCard>();
		String selectGpu = 
				"SELECT GraphicCard.web_scraper_order AS web_scraper_order,"
				+ "ProductName,Name_href,Price,Rating,Number_of_Reviews,"
				+ "GraphicCard.Size AS Size,"
				+ "GraphicCard.Image AS Image,"
				+ "GraphicCard.Manufacturer AS Manufacturer,"
				+ "GraphicCard.DDR_GDDR AS DDR_GDDR,"						
				+ "GraphicCard.Size AS Size"
				+ " FROM ElectronicProductDetails "
				+ "INNER JOIN GraphicCard"
				+ " ON GraphicCard.web_scraper_order=ElectronicProductDetails.web_scraper_order "
				+ "WHERE Price>=? and Price<=? "
				+ "ORDER BY Rating*Number_of_Reviews DESC "
				+ "LIMIT 10;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectGpu);
			int lowN = Integer.parseInt(low);
			int highN = Integer.parseInt(high);
			selectStmt.setInt(1, lowN);
			selectStmt.setInt(2, highN);
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
	public List<GraphicCard> getGraphicCardTop(int num)throws SQLException {
		List<GraphicCard> graphiccards = new ArrayList<GraphicCard>();
		String selectGpu = 
				"SELECT GraphicCard.web_scraper_order AS web_scraper_order,"
				+ "ProductName,Name_href,Price,Rating,Number_of_Reviews,"
				+ "GraphicCard.Size AS Size,"
				+ "GraphicCard.Image AS Image,"
				+ "GraphicCard.Manufacturer AS Manufacturer,"
				+ "GraphicCard.DDR_GDDR AS DDR_GDDR,"						
				+ "GraphicCard.Size AS Size"
				+ " FROM ElectronicProductDetails "
				+ "INNER JOIN GraphicCard"
				+ " ON GraphicCard.web_scraper_order=ElectronicProductDetails.web_scraper_order "
				+ "ORDER BY Rating*Number_of_Reviews DESC "
				+ "LIMIT ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectGpu);
			selectStmt.setInt(1, num);
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
	public List<HardDisk> getHardDiskByBudget(String low, String high)throws SQLException {
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
				+ "WHERE Price>=? and Price<=? "
				+ "ORDER BY Rating*Number_of_Reviews DESC "
				+ "LIMIT 10;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectHarddisk);
			int lowN = Integer.parseInt(low);
			int highN = Integer.parseInt(high);
			selectStmt.setInt(1, lowN);
			selectStmt.setInt(2, highN);
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
	public List<HardDisk> getHardDiskTop(int num)throws SQLException {
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
				+ "ORDER BY Rating*Number_of_Reviews DESC "
				+ "LIMIT ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectHarddisk);
			selectStmt.setInt(1, num);
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

}
