package Electronics.model;

public class ElectronicProductDetails {
	protected String web_scraper_order;
	protected String ProductName;
	protected String Name_hef;
	protected double Price;
	protected double Rating;
	protected int Number_of_Reviews;
	public ElectronicProductDetails(String web_scraper_order, String ProductName, String Name_hef, 
			double Price, double Rating, int Number_of_Reviews) {
		this.web_scraper_order = web_scraper_order;
		this.ProductName = ProductName;
		this.Name_hef = Name_hef;
		this.Price = Price;
		this.Rating = Rating;
		this.Number_of_Reviews = Number_of_Reviews;
	}
	public ElectronicProductDetails(String web_scraper_order) {
		this.web_scraper_order = web_scraper_order;
	}
	public String getWeb_scraper_order() {
		return web_scraper_order;
	}
	public void setWeb_scraper_order(String web_scraper_order) {
		this.web_scraper_order = web_scraper_order;
	}
	public String getProductName() {
		return ProductName;
	}
	public void setProductName(String productName) {
		ProductName = productName;
	}
	public String getName_hef() {
		return Name_hef;
	}
	public void setName_hef(String name_hef) {
		Name_hef = name_hef;
	}
	public double getPrice() {
		return Price;
	}
	public void setPrice(double price) {
		Price = price;
	}
	public double getRating() {
		return Rating;
	}
	public void setRating(double rating) {
		Rating = rating;
	}
	public int getNumber_of_Reviews() {
		return Number_of_Reviews;
	}
	public void setNumber_of_Reviews(int number_of_Reviews) {
		Number_of_Reviews = number_of_Reviews;
	}
}
