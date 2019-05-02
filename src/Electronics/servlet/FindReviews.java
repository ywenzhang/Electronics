package Electronics.servlet;

import Electronics.dal.*;
import Electronics.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/findreviews")
public class FindReviews extends HttpServlet {
	
	protected ReviewsDao reviewsDao;
	
	@Override
	public void init() throws ServletException {
		reviewsDao = ReviewsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
        List<Reviews> reviews = new ArrayList<Reviews>();
        
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        String productId = req.getParameter("productId");
        if (productId == null || productId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid Product ID.");
        } else {
        	try {
        		reviews = reviewsDao.getReviewsByElectronicProductDetailId(productId);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + productId);
        	messages.put("previousAuthor", productId);
        }
        req.setAttribute("reviews", reviews); 
        req.getRequestDispatcher("/FindReviews.jsp").forward(req, resp);
	}
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Reviews> reviews = new ArrayList<Reviews>();
        String productId = req.getParameter("productId");
        if (productId == null || productId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid Product ID.");
        } else {
        	try {
        		reviews = reviewsDao.getReviewsByElectronicProductDetailId(productId);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for Product ID " + productId);
        }
        req.setAttribute("reviews",reviews);         
        req.getRequestDispatcher("/FindReviews.jsp").forward(req, resp);
    }
}
