package Electronics.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import Electronics.dal.*;
import Electronics.model.ElectronicProductDetails;
import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/findproduct")
public class FindProducts extends HttpServlet {
	protected ElectronicProductDetailsDao productDao;
	
	@Override
	public void init() throws ServletException {
		productDao = ElectronicProductDetailsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for string messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        req.getRequestDispatcher("/FindProduct.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		// Map for string messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        String  web_scraper_order= req.getParameter("web_scraper_order");
        ElectronicProductDetails product = new ElectronicProductDetails(web_scraper_order);
        if ( web_scraper_order == null || web_scraper_order.trim().isEmpty()) {
            messages.put("success", "Please enter a valid web_scraper_order.");
        } else {
        	// Retrieve BlogUsers, and store as a message.
        	try {
        		product = productDao.getProductByScraperOrder(web_scraper_order);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for" + web_scraper_order);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindUsers.jsp.
        	messages.put("previousweb_scraper_order", web_scraper_order);
        }
        req.setAttribute("product", product);
        
        req.getRequestDispatcher("/FindProduct.jsp").forward(req, resp);
	}
}
