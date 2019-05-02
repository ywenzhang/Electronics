package Electronics.model;

import java.math.BigDecimal;
import java.util.Date;

public class Blogs {
	protected int blogId;
	protected Date createdTime;
	protected Users user;
	protected String content;
	public Blogs(int blogId, Date createdTime, Users user, String content) {
		super();
		this.blogId = blogId;
		this.createdTime = createdTime;
		this.user = user;
		this.content = content;
	}
	public Blogs(Date createdTime, Users user, String content) {
		super();
		this.createdTime = createdTime;
		this.user = user;
		this.content = content;
	}
	public Blogs(int blogId) {
		super();
		this.blogId = blogId;
	}
	public int getBlogId() {
		return blogId;
	}
	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}