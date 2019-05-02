package Electronics.model;

public class HardDisk extends ElectronicProductDetails{
	//protected String web_scraper_order;
	protected String Image;
	protected String Manufacturer;
	protected boolean RAM_SATA;
	protected String Capacity;
	protected int Hard_Disk_Type;
	
	public HardDisk(String web_scraper_order, String ProductName, String Name_hef, 
			double Price, double Rating, int Number_of_Reviews,String Image,
			String Manufacturer,boolean RAM_SATA,String Capacity,int Hard_Disk_Type) {
		super(web_scraper_order, ProductName, Name_hef, Price, Rating,Number_of_Reviews);
		this.Image = Image;
		this.Manufacturer = Manufacturer;
		this.RAM_SATA = RAM_SATA;
		this.Capacity = Capacity;
		this.Hard_Disk_Type = Hard_Disk_Type;
	}
	
	public HardDisk(String web_scraper_order) {
		super(web_scraper_order);
	}
	public String getWeb_scraper_order() {
		return web_scraper_order;
	}

	public void setWeb_scraper_order(String web_scraper_order) {
		this.web_scraper_order = web_scraper_order;
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

	public boolean isRAM_SATA() {
		return RAM_SATA;
	}

	public void setRAM_SATA(boolean rAM_SATA) {
		RAM_SATA = rAM_SATA;
	}

	public String getCapacity() {
		return Capacity;
	}

	public void setCapacity(String capacity) {
		Capacity = capacity;
	}

	public int getHard_Disk_Type() {
		return Hard_Disk_Type;
	}

	public void setHard_Disk_Type(int hard_Disk_Type) {
		Hard_Disk_Type = hard_Disk_Type;
	}
}
