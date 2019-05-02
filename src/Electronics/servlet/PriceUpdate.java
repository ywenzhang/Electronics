package Electronics.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Electronics.dal.ElectronicProductDetailsDao;
import Electronics.model.ElectronicProductDetails;
@WebServlet("/priceupdate")
public class PriceUpdate extends HttpServlet{
	protected ElectronicProductDetailsDao productDao;
	
	@Override
	public void init() throws ServletException {
		productDao = ElectronicProductDetailsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String web_scraper_order = req.getParameter("web_scraper_order");
        if (web_scraper_order == null || web_scraper_order.trim().isEmpty()) {
            messages.put("success", "Please enter a valid web_scraper_order.");
        } else {
        	try {
        		ElectronicProductDetails product = productDao.getProductByScraperOrder(web_scraper_order);     		
        		if(product == null) {
        			messages.put("success", "web_scraper_order does not exist.");
        		}
        		req.setAttribute("product", product);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/ProductUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String web_scraper_order = req.getParameter("web_scraper_order");
        if (web_scraper_order == null || web_scraper_order.trim().isEmpty()) {
            messages.put("success", "Please enter a valid web_scraper_order.");
        } else {
        	try {
        		ElectronicProductDetails product = productDao.getProductByScraperOrder(web_scraper_order);
        		if(product == null) {
        			messages.put("success", "web_scraper_order does not exist. No update to perform.");
        		} else {
        			String newPrice = req.getParameter("newPrice");
        			//System.out.println(newPrice);
        			if (newPrice == null || newPrice.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid Price.");
        	        } else {
        	        	Double newPrice1 = Double.valueOf(newPrice);
        	        	product = productDao.updatePrice(product, newPrice1);
        	        	messages.put("success", "Successfully updated " + web_scraper_order);
        	        }
        		}
        		req.setAttribute("product", product);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/ProductUpdate.jsp").forward(req, resp);
    }
}
