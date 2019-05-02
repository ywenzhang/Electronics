package Electronics.tools;
import java.sql.SQLException;
import java.util.List;

import Electronics.model.*;
import Electronics.dal.*;



public class Inserter {
	public static void main(String[] args) throws SQLException {
//		// DAO instances.
		ElectronicProductDetailsDao productDao = ElectronicProductDetailsDao.getInstance();
		LaptopDao laptopDao = LaptopDao.getInstance();
		DesktopDao desktopDao = DesktopDao.getInstance();
		GraphicCardDao graphicCardDao = GraphicCardDao.getInstance();
        // INSERT objects from our model
//		Laptop product1 = new Laptop("1", "laptop1", "www.amazon.com",500.00,4.5,100,"apple","13","Windows10","i7","2G","500 GB",1,2,3);
//		product1 = laptopDao.create(product1);
//		Laptop product2 = new Laptop("2", "laptop2", "www.amazon.com",500.00,4.5,100,"apple","13","Windows10","i7","2G","500 GB",1,2,3);
//		product2 = laptopDao.create(product2);
//		Laptop product3 = new Laptop("3", "laptop3", "www.amazon.com",500.00,4.5,100,"apple","13","Windows10","i7","2G","500 GB",1,2,3);
//		product3 = laptopDao.create(product3);	
//		
//		ElectronicProductDetails product4 = new ElectronicProductDetails("4", "desktop1", "www.amazon.com",500.00,4.5,100);
//     	product4 = productDao.create(product4);
//		ElectronicProductDetails product5 = new ElectronicProductDetails("5", "desktop2", "www.amazon.com",500.00,4.5,100);
//		product5 = productDao.create(product5);
//		ElectronicProductDetails product6 = new ElectronicProductDetails("6", "desktop3", "www.amazon.com",500.00,4.5,100);
//		product6 = productDao.create(product6);	
//		
//		ElectronicProductDetails product7 = new ElectronicProductDetails("7", "graphiccard1", "www.amazon.com",500.00,4.5,100);
//		product7 = productDao.create(product7);
//		ElectronicProductDetails product8 = new ElectronicProductDetails("8", "grapiccard2", "www.amazon.com",500.00,4.5,100);
//		product8 = productDao.create(product8);
//		ElectronicProductDetails product9 = new ElectronicProductDetails("9", "graphiccard3", "www.amazon.com",500.00,4.5,100);
//		product9 = productDao.create(product9);	
//		
//		ElectronicProductDetails product10 = new ElectronicProductDetails("10", "harddisk1", "www.amazon.com",500.00,4.5,100);
//		product10 = productDao.create(product10);
//		ElectronicProductDetails product11 = new ElectronicProductDetails("11", "harddisk2", "www.amazon.com",500.00,4.5,100);
//		product11 = productDao.create(product11);
//		ElectronicProductDetails product12 = new ElectronicProductDetails("12", "harddisk3", "www.amazon.com",500.00,4.5,100);	
//		product12 = productDao.create(product12);
		
		// INSERT objects from our model
//		Desktop product1 = new Desktop("11", "desktop1", "www.amazon.com",500.00,4.5,100,"Windows10","i7","2G","500 GB",1,2,3,"other");
//		product1 = desktopDao.create(product1);
//		System.out.printf(product1.getOperating_System());
//		
		Desktop product2 = new Desktop("12", "desktop2", "www.amazon.com",500.00,4.5,100,"Windows10","i7","2G","500 GB",1,2,3,"other");
		product2 = desktopDao.create(product2);
//		Desktop product3 = new Desktop("13", "desktop3", "www.amazon.com",500.00,4.5,100,"Windows10","i7","2G","500 GB",1,2,3,"other");
//		product3 = desktopDao.create(product3);
//		GraphicCard product1 = new GraphicCard("14", "Graphiccard1", "www.amazon.com",500.00,4.5,100,64,"Image","NV","1");
//		product1 = graphicCardDao.create(product1);		
		
		
		// Read
//		Laptop laptop = laptopDao.getLaptopByWeb_scraper_order("2");
//		System.out.format("Laptop Details: id:%s name:%s url:%s price:%s rating:%s reviews:%s\n brand:%s screen:%s op:%s cpu:%s memory:%s harddisk:%s hardtype:%s cputype:%s memorytype:%s\n",
//				laptop.getWeb_scraper_order(),laptop.getProductName(),laptop.getName_hef(),laptop.getPrice(),laptop.getRating(),laptop.getNumber_of_Reviews(),
//				laptop.getProducer(),laptop.getDisplay_Size(),laptop.getOperating_System(),laptop.getCPU_Model_Family(),laptop.getMemory_Size(),laptop.getHard_Disk_Size(),
//				laptop.getMemory_Type(),laptop.getCPU_Type(),laptop.getHard_Disk_Type());
//		List<Laptop> laptopsP = laptopDao.getLaptopByProducer("apple");
//		for(Laptop l:laptopsP) {
//			System.out.format("Laptop Details: id:%s name:%s url:%s price:%s rating:%s reviews:%s\n brand:%s screen:%s op:%s cpu:%s memory:%s harddisk:%s hardtype:%s cputype:%s memorytype:%s\n",
//					l.getWeb_scraper_order(),laptop.getProductName(),l.getName_hef(),l.getPrice(),l.getRating(),l.getNumber_of_Reviews(),
//					l.getProducer(),l.getDisplay_Size(),l.getOperating_System(),l.getCPU_Model_Family(),l.getMemory_Size(),l.getHard_Disk_Size(),
//					l.getMemory_Type(),l.getCPU_Type(),l.getHard_Disk_Type());
//		}
//		List<Laptop> laptopsS = laptopDao.getLaptopByDisplay_Size("13");
//		for(Laptop l:laptopsS) {
//			System.out.format("Laptop Details: id:%s name:%s url:%s price:%s rating:%s reviews:%s\n brand:%s screen:%s op:%s cpu:%s memory:%s harddisk:%s hardtype:%s cputype:%s memorytype:%s\n",
//					l.getWeb_scraper_order(),laptop.getProductName(),l.getName_hef(),l.getPrice(),l.getRating(),l.getNumber_of_Reviews(),
//					l.getProducer(),l.getDisplay_Size(),l.getOperating_System(),l.getCPU_Model_Family(),l.getMemory_Size(),l.getHard_Disk_Size(),
//					l.getMemory_Type(),l.getCPU_Type(),l.getHard_Disk_Type());
//		}
//		List<Laptop> laptopsO = laptopDao.getLaptopByOperating_System("Windows10");
//		for(Laptop l:laptopsO) {
//			System.out.format("Laptop Details: id:%s name:%s url:%s price:%s rating:%s reviews:%s\n brand:%s screen:%s op:%s cpu:%s memory:%s harddisk:%s hardtype:%s cputype:%s memorytype:%s\n",
//					l.getWeb_scraper_order(),laptop.getProductName(),l.getName_hef(),l.getPrice(),l.getRating(),l.getNumber_of_Reviews(),
//					l.getProducer(),l.getDisplay_Size(),l.getOperating_System(),l.getCPU_Model_Family(),l.getMemory_Size(),l.getHard_Disk_Size(),
//					l.getMemory_Type(),l.getCPU_Type(),l.getHard_Disk_Type());
//		}
//		List<Laptop> laptopsC = laptopDao.getLaptopByCPU_Model_Family("Windows10");
//		for(Laptop l:laptopsC) {
//			System.out.format("Laptop Details: id:%s name:%s url:%s price:%s rating:%s reviews:%s\n brand:%s screen:%s op:%s cpu:%s memory:%s harddisk:%s hardtype:%s cputype:%s memorytype:%s\n",
//					l.getWeb_scraper_order(),laptop.getProductName(),l.getName_hef(),l.getPrice(),l.getRating(),l.getNumber_of_Reviews(),
//					l.getProducer(),l.getDisplay_Size(),l.getOperating_System(),l.getCPU_Model_Family(),l.getMemory_Size(),l.getHard_Disk_Size(),
//					l.getMemory_Type(),l.getCPU_Type(),l.getHard_Disk_Type());
//		}
//		List<Laptop> laptopsM = laptopDao.getLaptopByMemory_Size("Windows10");
//		for(Laptop l:laptopsM) {
//			System.out.format("Laptop Details: id:%s name:%s url:%s price:%s rating:%s reviews:%s\n brand:%s screen:%s op:%s cpu:%s memory:%s harddisk:%s hardtype:%s cputype:%s memorytype:%s\n",
//					l.getWeb_scraper_order(),laptop.getProductName(),l.getName_hef(),l.getPrice(),l.getRating(),l.getNumber_of_Reviews(),
//					l.getProducer(),l.getDisplay_Size(),l.getOperating_System(),l.getCPU_Model_Family(),l.getMemory_Size(),l.getHard_Disk_Size(),
//					l.getMemory_Type(),l.getCPU_Type(),l.getHard_Disk_Type());
//		}
//		List<Laptop> laptopsH = laptopDao.getLaptopByHard_Disk_Size("Windows10");
//		for(Laptop l:laptopsH) {
//			System.out.format("Laptop Details: id:%s name:%s url:%s price:%s rating:%s reviews:%s\n brand:%s screen:%s op:%s cpu:%s memory:%s harddisk:%s hardtype:%s cputype:%s memorytype:%s\n",
//					l.getWeb_scraper_order(),laptop.getProductName(),l.getName_hef(),l.getPrice(),l.getRating(),l.getNumber_of_Reviews(),
//					l.getProducer(),l.getDisplay_Size(),l.getOperating_System(),l.getCPU_Model_Family(),l.getMemory_Size(),l.getHard_Disk_Size(),
//					l.getMemory_Type(),l.getCPU_Type(),l.getHard_Disk_Type());
//		}
		
		List<Laptop> laptopsP1 = laptopDao.getLaptopByParameters("apple","13","Windows10","i7","2G","500 GB");
		for(Laptop l:laptopsP1) {
			System.out.format("Laptop Details: id:%s name:%s url:%s price:%s rating:%s reviews:%s\n brand:%s screen:%s op:%s cpu:%s memory:%s harddisk:%s hardtype:%s cputype:%s memorytype:%s\n",
					l.getWeb_scraper_order(),l.getProductName(),l.getName_hef(),l.getPrice(),l.getRating(),l.getNumber_of_Reviews(),
					l.getProducer(),l.getDisplay_Size(),l.getOperating_System(),l.getCPU_Model_Family(),l.getMemory_Size(),l.getHard_Disk_Size(),
					l.getMemory_Type(),l.getCPU_Type(),l.getHard_Disk_Type());
		}
		
		List<Desktop> desktopsP1 = desktopDao.getDesktopByParameters("Windows10","i7","2G","500 GB");
		for(Desktop l:desktopsP1) {
			System.out.format("Laptop Details: id:%s name:%s url:%s price:%s rating:%s reviews:%s\n op:%s cpu:%s memory:%s harddisk:%s hardtype:%s cputype:%s memorytype:%s other:%s\n",
					l.getWeb_scraper_order(),l.getProductName(),l.getName_hef(),l.getPrice(),l.getRating(),l.getNumber_of_Reviews(),
					l.getOperating_System(),l.getCPU_Model_Family(),l.getMemory_Size(),l.getHard_Disk_Size(),
					l.getMemory_Type(),l.getCPU_Type(),l.getHard_Disk_Type(),l.getOther());
		}
		
//		ElectronicProductDetails p1 = productDao.getProductByScraperOrder("1");
//		System.out.format("Reading user: w:%s p:%s n:%s p:%s r:%s n:%s \n",
//				p1.getWeb_scraper_order(), p1.getProductName(), p1.getName_hef(),p1.getPrice(),p1.getRating(),p1.getNumber_of_Reviews());		
		
//		ElectronicProductDetails products =productDao.getRecommendationById(1);
//		System.out.format("Reading Recommendation: id:%s username:%s restId:%s \n",
//				recommendations.getRecommendationId(),recommendations.getUserName(),recommendations.getRestaurantId());	
//		List<Recommendations> recommendationl=recommendationsDao.getRecommendationsByUserName("mjw");
//		for(Recommendations r : recommendationl) {
//			System.out.format("Looping Recommendation(UserName): id:%s username:%s restId:%s \n",
//			recommendations.getRecommendationId(),recommendations.getUserName(),recommendations.getRestaurantId());
//		}	


		//Update
//		productDao.updatePrice(product1, 600);
		//Delete
		//ElectronicProductDetails user0 = productDao.delete(product1);
	}
}
