package Electronics.model;
//added by YihaoLi
import java.util.Date;

public class Admin extends Users 
{
	protected Date lastLoginDate;
	protected StatusLevel statusLevel;
	
	public enum StatusLevel {
		novice, intermediate, advanced
	}
	public Admin(String username, String password, String firstname, String lastname, String email, String phonenumber, Date lastLoginDate, StatusLevel statusLevel) 
	{
		super(username, password, firstname, lastname, email, phonenumber);
		this.lastLoginDate = lastLoginDate;
		this.statusLevel = statusLevel;
	}
	
	public Date getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	public StatusLevel getStatusLevel() {
		return statusLevel;
	}
	public void setStatusLevel(StatusLevel statusLevel) {
		this.statusLevel = statusLevel;
	}
	
}
