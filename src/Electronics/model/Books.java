package Electronics.model;

import java.math.BigDecimal;

public class Books {
protected String web_scraper_order;
protected String title;
protected String author;
protected BigDecimal rating;
protected int number_of_Ratings;
protected int number_of_Reviews;
public Books(String web_scraper_order, String title, String author, BigDecimal rating, int number_of_Ratings,
		int number_of_Reviews) {
	super();
	this.web_scraper_order = web_scraper_order;
	this.title = title;
	this.author = author;
	this.rating = rating;
	this.number_of_Ratings = number_of_Ratings;
	this.number_of_Reviews = number_of_Reviews;
}
public Books(String title, String author, BigDecimal rating, int number_of_Ratings, int number_of_Reviews) {
	super();
	this.title = title;
	this.author = author;
	this.rating = rating;
	this.number_of_Ratings = number_of_Ratings;
	this.number_of_Reviews = number_of_Reviews;
}
public Books(String web_scraper_order) {
	super();
	this.web_scraper_order = web_scraper_order;
}
public String getWeb_scraper_order() {
	return web_scraper_order;
}
public void setWeb_scraper_order(String web_scraper_order) {
	this.web_scraper_order = web_scraper_order;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getAuthor() {
	return author;
}
public void setAuthor(String author) {
	this.author = author;
}
public BigDecimal getRating() {
	return rating;
}
public void setRating(BigDecimal rating) {
	this.rating = rating;
}
public int getNumber_of_Ratings() {
	return number_of_Ratings;
}
public void setNumber_of_Ratings(int number_of_Ratings) {
	this.number_of_Ratings = number_of_Ratings;
}
public int getNumber_of_Reviews() {
	return number_of_Reviews;
}
public void setNumber_of_Reviews(int number_of_Reviews) {
	this.number_of_Reviews = number_of_Reviews;
}


}
