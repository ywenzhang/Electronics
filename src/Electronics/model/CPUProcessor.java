package Electronics.model;

public class CPUProcessor extends ElectronicProductDetails
{
	//protected String web_scraper_order;
	protected String Image;
	protected String Series;
	protected String Cache;
	protected String CacheSize;
	protected int CacheType;
	protected int CPUType;
	
	public CPUProcessor(String web_scraper_order, String ProductName, String Name_hef, 
			double Price, double Rating, int Number_of_Reviews,String Image,
			String Series,String Cache,String CacheSize,int CacheType, int CPUType) {
		super(web_scraper_order, ProductName, Name_hef, Price, Rating,Number_of_Reviews);
		this.Image = Image;
		this.Series= Series;
		this.Cache = Cache;
		this.CacheSize = CacheSize;
		this.CacheType = CacheType;
		this.CPUType = CPUType;
	}
	
	public CPUProcessor(String web_scraper_order) {
		super(web_scraper_order);
	}
	
	/*public String getWeb_scraper_order() {
		return web_scraper_order;
	}

	public void setWeb_scraper_order(String web_scraper_order) {
		this.web_scraper_order = web_scraper_order;
	}
	*/
	public String getImage() {
		return Image;
	}

	public void setImage(String image) {
		Image = image;
	}

	public String getSeries() {
		return Series;
	}

	public void setSeries(String series) {
		Series = series;
	}

	public String getCache() {
		return Cache;
	}

	public void setCache(String cache) {
		Cache = cache;
	}

	public String getCacheSize() {
		return CacheSize;
	}

	public void setCacheSize(String cacheSize) {
		CacheSize = cacheSize;
	}

	public int getCacheType() {
		return CacheType;
	}

	public void setCacheType(int cacheType) {
		CacheType = cacheType;
	}

	public int getCPUType() {
		return CPUType;
	}

	public void setCPUType(int cPUType) {
		CPUType = cPUType;
	}
}
