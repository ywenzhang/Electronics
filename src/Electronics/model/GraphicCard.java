package Electronics.model;

public class GraphicCard extends ElectronicProductDetails{
	//protected String web_scraper_order;
	protected String Size;
	protected String Image;
	protected String Manufacturer;
	protected String DDR_GDDR;
	public GraphicCard(String web_scraper_order, String ProductName, String Name_hef, 
			double Price, double Rating, int Number_of_Reviews,String Size,String Image,String Manufacturer,String DDR_GDDR) {
		super(web_scraper_order, ProductName, Name_hef, Price, Rating,Number_of_Reviews);
		this.Size = Size;
		this.Image= Image;
		this.Manufacturer = Manufacturer;
		this.DDR_GDDR = DDR_GDDR;
	}
	
	public GraphicCard(String web_scraper_order) {
		super(web_scraper_order);
	}
	/*public String getWeb_scraper_order() {
		return web_scraper_order;
	}

	public void setWeb_scraper_order(String web_scraper_order) {
		this.web_scraper_order = web_scraper_order;
	}*/

	public String getSize() {
		return Size;
	}

	public void setSize(String size) {
		Size = size;
	}

	public String getImage() {
		return Image;
	}

	public void setImage(String image) {
		Image = image;
	}

	public String getManufacturer() {
		return Manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		Manufacturer = manufacturer;
	}

	public String getDDR_GDDR() {
		return DDR_GDDR;
	}

	public void setDDR_GDDR(String dDR_GDDR) {
		DDR_GDDR = dDR_GDDR;
	}

}
