package Electronics.model;

import java.math.BigDecimal;
import java.util.Date;

public class Comments {

	protected int commentId;
	protected Blogs blog;
	protected Users user;
	protected BigDecimal vote;
	protected String content;
	protected Date createdTime;
	public Comments(int commentId, Blogs blog, Users user, BigDecimal vote, String content, Date createdTime) {
		super();
		this.commentId = commentId;
		this.blog = blog;
		this.user = user;
		this.vote = vote;
		this.content = content;
		this.createdTime = createdTime;
	}
	public Comments(Blogs blog, Users user, BigDecimal vote, String content, Date createdTime) {
		super();
		this.blog = blog;
		this.user = user;
		this.vote = vote;
		this.content = content;
		this.createdTime = createdTime;
	}
	public Comments(int commentId) {
		super();
		this.commentId = commentId;
	}
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public Blogs getBlog() {
		return blog;
	}
	public void setBlog(Blogs blog) {
		this.blog = blog;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public BigDecimal getVote() {
		return vote;
	}
	public void setVote(BigDecimal vote) {
		this.vote = vote;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
}