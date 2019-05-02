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

@WebServlet("/productcreate")
public class ProductCreate extends HttpServlet {
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
        //Just render the JSP.   
        req.getRequestDispatcher("/ProductCreate.jsp").forward(req, resp);
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
            messages.put("success", "Invalid web_scraper_order");
        } else {
        	// Create the BlogUser.
        	String ProductName = req.getParameter("ProductName");
        	String Name_hef = req.getParameter("Name_hef");
        	String PriceS = req.getParameter("Price");
        	double Price = Double.valueOf(PriceS);
        	String RatingS = req.getParameter("Rating");
        	double Rating = Double.valueOf(RatingS);
        	String Number_of_ReviewsS = req.getParameter("Number_of_Reviews");
        	int Number_of_Reviews = Integer.valueOf(Number_of_ReviewsS); 
	        try {
	        	// Exercise: parse the input for StatusLevel.
	        	ElectronicProductDetails product = new ElectronicProductDetails(web_scraper_order,ProductName,Name_hef,
	        			Price,Rating,Number_of_Reviews);
	        	product = productDao.create(product);
	        	messages.put("success", "Successfully created " + web_scraper_order);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/ProductCreate.jsp").forward(req, resp);
    }
}
