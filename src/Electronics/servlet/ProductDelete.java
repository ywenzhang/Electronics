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

@WebServlet("/productdelete")
public class ProductDelete extends HttpServlet{
	protected ElectronicProductDetailsDao productDao;
	
	@Override
	public void init() throws ServletException {
		productDao = productDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Provide a title and render the JSP.
        messages.put("title", "Delete Product");        
        req.getRequestDispatcher("/ProductDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String web_scraper_order = req.getParameter("web_scraper_order");
        if (web_scraper_order == null || web_scraper_order.trim().isEmpty()) {
            messages.put("title", "Invalid web_scraper_order");
            messages.put("disableSubmit", "true");
        } else {
        	// Delete the BlogUser.
        	ElectronicProductDetails product = new ElectronicProductDetails(web_scraper_order);
	        try {
	        	product = productDao.delete(product);
	        	// Update the message.
		        if (product == null) {
		            messages.put("title", "Successfully deleted " + web_scraper_order);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete " + web_scraper_order);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/ProductDelete.jsp").forward(req, resp);
    }
}
