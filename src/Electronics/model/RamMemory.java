package Electronics.model;

public class RamMemory extends ElectronicProductDetails{
	//protected String web_scraper_order;
	protected String ddr;	
	protected String Memory_Size;
	protected int MemoryType;
		
	public RamMemory(String web_scraper_order, String ProductName, String Name_hef, double Price, double Rating, int Number_of_Reviews, String ddr, String Memory_Size, int MemoryType) {
		super(web_scraper_order, ProductName, Name_hef, Price, Rating,Number_of_Reviews);
		this.ddr = ddr;
		this.Memory_Size= Memory_Size;
		this.MemoryType = MemoryType;
		
		}
	
	public RamMemory(String web_scraper_order) {
		super(web_scraper_order);
	}
	
	/*public String getWeb_scraper_order() {
		return web_scraper_order;
	}

	public void setWeb_scraper_order(String web_scraper_order) {
		this.web_scraper_order = web_scraper_order;
	}
	*/
	public String getDDR() {
		return ddr;
	}
	public void setDDR(String ddr) {
		this.ddr = ddr;
	}

	public String getMemory_Size() {
		return Memory_Size;
	}

	public void setMemory_Size(String memory_Size) {
		Memory_Size = memory_Size;
	}
	

	public int getMemoryType() {
		return MemoryType;
	}

	public void setMemoryType(int memoryType) {
		MemoryType = memoryType;
	}

	
	
}
		

