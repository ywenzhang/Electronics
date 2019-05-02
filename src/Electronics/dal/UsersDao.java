package Electronics.dal;
import Electronics.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Electronics.dal.ConnectionManager;
import Electronics.dal.UsersDao;
import Electronics.model.Users;

//added by Yihao Li

public class UsersDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static UsersDao instance = null;
	protected UsersDao() {
		connectionManager = new ConnectionManager();
	}
	public static UsersDao getInstance() {
		if(instance == null) {
			instance = new UsersDao();
		}
		return instance;
	}
	
	public Users create(Users user) throws SQLException {
		Users resultUser =  null;
		resultUser = getUserByUserName(user.getUsername());
		if (resultUser ==null) {
		String insertUser = "INSERT INTO Users(Username,Password,Firstname,Lastname,Email,Phone) VALUES(?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertUser);
			// PreparedStatement allows us to substitute specific types into the query template.
			// For an overview, see:
			// http://docs.oracle.com/javase/tutorial/jdbc/basics/prepared.html.
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// For nullable fields, you can check the property first and then call setNull()
			// as applicable.
			insertStmt.setString(1, user.getUsername());
			insertStmt.setString(2, user.getPassword());
			insertStmt.setString(3, user.getFirstname());
			insertStmt.setString(4, user.getLastname());
			insertStmt.setString(5, user.getEmail());
			insertStmt.setString(6, user.getPhoneNumber());
			// Note that we call executeUpdate(). This is used for a INSERT/UPDATE/DELETE
			// statements, and it returns an int for the row counts affected (or 0 if the
			// statement returns nothing). For more information, see:
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// I'll leave it as an exercise for you to write UPDATE/DELETE methods.
			insertStmt.executeUpdate();
			
			// Note 1: if this was an UPDATE statement, then the person fields should be
			// updated before returning to the caller.
			// Note 2: there are no auto-generated keys, so no update to perform on the
			// input param person.
			return user;
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
		else {
			return null;
		}
	}
	
	public Users delete(Users user) throws SQLException {
		String deleteUser = "DELETE FROM Users WHERE Username=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteUser);
			deleteStmt.setString(1, user.getUsername());
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
	
	public Users getUserByUserName(String username) throws SQLException {
		String selectUser = "SELECT Username,Password,Firstname,Lastname,Email,Phone FROM Users WHERE Username=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUser);
			selectStmt.setString(1, username);
			// Note that we call executeQuery(). This is used for a SELECT statement
			// because it returns a result set. For more information, see:
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// http://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html
			results = selectStmt.executeQuery();
			// You can iterate the result set (although the example below only retrieves 
			// the first record). The cursor is initially positioned before the row.
			// Furthermore, you can retrieve fields by name and by type.
			if(results.next()) {
				String resultUsername = results.getString("Username");
				String password = results.getString("Password");
				String firstname = results.getString("Firstname");
				String lastname = results.getString("Lastname");
				String email = results.getString("Email");
				String phonenumber = results.getString("Phone");
				Users user = new Users(resultUsername, password, firstname, lastname, email, phonenumber);
				return user;
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
	
	public List<Users> getUsersFromFirstname(String firstname) throws SQLException {
		List<Users> users = new ArrayList<Users>();
		String selectUers =
			"SELECT Username,Password,Firstname,Lastname,Email,Phone FROM Users WHERE Firstname=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUers);
			selectStmt.setString(1, firstname);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String username = results.getString("Username");
				String password = results.getString("Password");
				String resultFirstname = results.getString("Firstname");
				String lastname = results.getString("Lastname");
				String email = results.getString("Email");
				String phonenumber = results.getString("Phone");
				Users user = new Users(username, password, resultFirstname, lastname, email, phonenumber);
				users.add(user);
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
		return users;
	}
	
	public Users updatePassword(Users user, String newPassword) throws SQLException {
		String updateUser = "UPDATE Users SET Password =? WHERE Username=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateUser);
			updateStmt.setString(1, newPassword);
			updateStmt.setString(2, user.getUsername());
			updateStmt.executeUpdate();
			
			// Update the person param before returning to the caller.
			user.setPassword(newPassword);
			return user;
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
	
	public Users getUserByParameters(String username, String password) throws SQLException {
		String selectUser = "SELECT Username,Password,Firstname,Lastname,Email,Phone FROM Users WHERE Username=? and Password=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUser);
			selectStmt.setString(1, username);
			selectStmt.setString(2, password);
			// Note that we call executeQuery(). This is used for a SELECT statement
			// because it returns a result set. For more information, see:
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// http://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html
			results = selectStmt.executeQuery();
			// You can iterate the result set (although the example below only retrieves 
			// the first record). The cursor is initially positioned before the row.
			// Furthermore, you can retrieve fields by name and by type.
			if(results.next()) {
				String resultUsername = results.getString("Username");
				String resultpassword = results.getString("Password");
				String firstname = results.getString("Firstname");
				String lastname = results.getString("Lastname");
				String email = results.getString("Email");
				String phonenumber = results.getString("Phone");
				Users user = new Users(resultUsername, resultpassword, firstname, lastname, email, phonenumber);
				return user;
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
