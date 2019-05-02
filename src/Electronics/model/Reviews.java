package Electronics.model;

import java.math.BigDecimal;
import java.util.Date;

public class Reviews {
	protected int reviewId;
	protected Date created;
	protected BigDecimal rating;
	protected Users user;
	protected ElectronicProductDetails electronicProductDetail;
	protected String content;
	public Reviews(int reviewId, Date created, BigDecimal rating, Users user,
			ElectronicProductDetails electronicProductDetail, String content) {
		super();
		this.reviewId = reviewId;
		this.created = created;
		this.rating = rating;
		this.user = user;
		this.electronicProductDetail = electronicProductDetail;
		this.content = content;
	}
	public Reviews(int reviewId) {
		super();
		this.reviewId = reviewId;
	}
	public Reviews(Date created, BigDecimal rating, Users user, ElectronicProductDetails electronicProductDetail,
			String content) {
		super();
		this.created = created;
		this.rating = rating;
		this.user = user;
		this.electronicProductDetail = electronicProductDetail;
		this.content = content;
	}
	public int getReviewId() {
		return reviewId;
	}
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public BigDecimal getRating() {
		return rating;
	}
	public void setRating(BigDecimal rating) {
		this.rating = rating;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public ElectronicProductDetails getElectronicProductDetail() {
		return electronicProductDetail;
	}
	public void setElectronicProductDetail(ElectronicProductDetails electronicProductDetail) {
		this.electronicProductDetail = electronicProductDetail;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	

}