package Electronics.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Electronics.model.*;


public class CPUProcessorDao extends ElectronicProductDetailsDao 
{
	private static CPUProcessorDao instance = null;
	protected CPUProcessorDao() {
		super();
	}
	public static CPUProcessorDao getInstance() {
		if(instance == null) {
			instance = new CPUProcessorDao();
		}
		return instance;
	}
	public CPUProcessor create(CPUProcessor cpurocessor) throws SQLException {
		// Insert into the superclass table first.
		create(new ElectronicProductDetails(cpurocessor.getWeb_scraper_order(), cpurocessor.getProductName(),
				cpurocessor.getName_hef(),cpurocessor.getPrice(),cpurocessor.getRating(),cpurocessor.getNumber_of_Reviews()));
		String insertCPUProcessor = "INSERT INTO CPUProcessor(web_scraper_order,Image,"
				+ "Series,Cache,CacheSize,CacheType,CPUType) "
				+ "VALUES(?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCPUProcessor);
			insertStmt.setString(1, cpurocessor.getWeb_scraper_order());
			insertStmt.setString(2, cpurocessor.getImage());
			insertStmt.setString(3, cpurocessor.getSeries());
			insertStmt.setString(4, cpurocessor.getCache());
			insertStmt.setString(5, cpurocessor.getCacheSize());
			insertStmt.setInt(6, cpurocessor.getCacheType());
			insertStmt.setInt(7, cpurocessor.getCPUType());

			
			insertStmt.executeUpdate();
			return cpurocessor;
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
	
	public CPUProcessor getCPUProcessorByWeb_scraper_order(String web_scraper_order) throws SQLException {
				String selectCPUProcessor = 
						"SELECT CPUProcessor.web_scraper_order AS web_scraper_order,"
						+ "ProductName,Name_hef,Price,Rating,Number_of_Reviews"
						+ "CPUProcessor.Image AS Image,"
						+ "CPUProcessor.Series AS Series,"
						+ "CPUProcessor.Cache AS Cache,"
						+ "CPUProcessor.CacheSize AS CacheSize,"
						+ "CPUProcessor.CacheType AS CacheType,"
						+ "CPUProcessor.CPUType AS CPUType"
						+ " FROM ElectronicProductDetails "
						+ "INNER JOIN CPUProcessor"
						+ " ON CPUProcessor.web_scraper_order=ElectronicProductDetails.web_scraper_order "
						+ "WHERE CPUProcessor.web_scraper_order=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCPUProcessor);
			selectStmt.setString(1,web_scraper_order);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String resultWeb_scraper_order = results.getString("web_scraper_order");
				String Image = results.getString("Image");
				String Series = results.getString("Series");
				String Cache = results.getString("Cache");
				String CacheSize = results.getString("CacheSize");
				int CacheType = results.getInt("CacheType");
				int CPUType = results.getInt("CPUType");
				CPUProcessor cpuprocessor = new CPUProcessor(resultWeb_scraper_order,ProductName,Name_hef,
						Price,Rating,Number_of_Reviews,Image,Series,Cache,CacheSize,CacheType,CPUType);
				return cpuprocessor;
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
	
	public List<CPUProcessor> getCPUProcessorBySeries(int series)throws SQLException {
		List<CPUProcessor> CPUProcessors = new ArrayList<CPUProcessor>();
				String selectCPUProcessor = 
						"SELECT CPUProcessor.web_scraper_order AS web_scraper_order,"
						+ "ProductName,Name_hef,Price,Rating,Number_of_Reviews"
						+ "CPUProcessor.Image AS Image,"
						+ "CPUProcessor.Series AS Series,"
						+ "CPUProcessor.Cache AS Cache,"
						+ "CPUProcessor.CacheSize AS CacheSize,"
						+ "CPUProcessor.CacheType AS CacheType,"
						+ "CPUProcessor.CPUType AS CPUType"
						+ " FROM ElectronicProductDetails "
						+ "INNER JOIN CPUProcessor"
						+ " ON CPUProcessor.web_scraper_order=ElectronicProductDetails.web_scraper_order "
				+ "WHERE CPUProcessor.Series=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCPUProcessor);
			selectStmt.setInt(1, series);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String resultWeb_scraper_order = results.getString("web_scraper_order");
				String Image = results.getString("Image");
				String Series = results.getString("Series");
				String Cache = results.getString("Cache");
				String CacheSize = results.getString("CacheSize");
				int CacheType = results.getInt("CacheType");
				int CPUType = results.getInt("CPUType");
				CPUProcessor cpuprocessor = new CPUProcessor(resultWeb_scraper_order,ProductName,Name_hef,
						Price,Rating,Number_of_Reviews,Image,Series,Cache,CacheSize,CacheType,CPUType);
				CPUProcessors.add(cpuprocessor);
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
		return CPUProcessors;
	}
	
	
	
	public List<CPUProcessor> getCPUProcessorByCacheSize(String cachesize)throws SQLException {
		List<CPUProcessor> CPUProcessors = new ArrayList<CPUProcessor>();
				String selectCPUProcessor = 
						"SELECT CPUProcessor.web_scraper_order AS web_scraper_order,"
						+ "ProductName,Name_hef,Price,Rating,Number_of_Reviews"
						+ "CPUProcessor.Image AS Image,"
						+ "CPUProcessor.Series AS Series,"
						+ "CPUProcessor.Cache AS Cache,"
						+ "CPUProcessor.CacheSize AS CacheSize,"
						+ "CPUProcessor.CacheType AS CacheType,"
						+ "CPUProcessor.CPUType AS CPUType"
						+ " FROM ElectronicProductDetails "
						+ "INNER JOIN CPUProcessor"
						+ " ON CPUProcessor.web_scraper_order=ElectronicProductDetails.web_scraper_order "
				+ "WHERE CPUProcessor.CacheSize=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCPUProcessor);
			selectStmt.setString(1, cachesize);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String resultWeb_scraper_order = results.getString("web_scraper_order");
				String Image = results.getString("Image");
				String Series = results.getString("Series");
				String Cache = results.getString("Cache");
				String CacheSize = results.getString("CacheSize");
				int CacheType = results.getInt("CacheType");
				int CPUType = results.getInt("CPUType");
				CPUProcessor cpuprocessor = new CPUProcessor(resultWeb_scraper_order,ProductName,Name_hef,
						Price,Rating,Number_of_Reviews,Image,Series,Cache,CacheSize,CacheType,CPUType);
				CPUProcessors.add(cpuprocessor);
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
		return CPUProcessors;
	}
	
	public List<CPUProcessor> getCPUProcessorByParameters(String series, String cache, String cacheSize)throws SQLException {
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
				+ "WHERE CPUProcessor.Series LIKE ? "
		        + "and CPUProcessor.Cache LIKE ? "
		        + "and CPUProcessor.CacheSize LIKE ? ;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCpu);
			selectStmt.setString(1, "%"+series+"%");
			selectStmt.setString(2, "%"+cache+"%");
			selectStmt.setString(3, "%"+cacheSize+"%");
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
	
	
	
	public CPUProcessor delete(CPUProcessor cpurocessor) throws SQLException {
		String deleteCPUProcessor = "DELETE FROM CPUProcessor WHERE web_scraper_order=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteCPUProcessor);
			deleteStmt.setString(1, cpurocessor.getWeb_scraper_order());
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
