package Electronics.model;
//added by YihaoLi
public class Customers extends Users 
{
	protected int level;
	protected int rewards;	
	
	public Customers(String username, String password, String firstname, String lastname, String email, String phonenumber, int level, int rewards) 
	{
		super(username, password, firstname, lastname, email, phonenumber);
		this.level = level;
		this.rewards = rewards;
	}
	
	public Customers(String username) {
		super(username);
	}

	/** Getters and setters. */
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getRewards() {
		return rewards;
	}

	public void setRewards(int rewards) {
		this.rewards = rewards;
	}

}
