package Electronics.servlet;

import Electronics.dal.*;
import Electronics.model.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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


@WebServlet("/createreview")
public class CreateReviews extends HttpServlet {
	
	protected ReviewsDao reviewsDao;
	
	@Override
	public void init() throws ServletException {
		reviewsDao = ReviewsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages); 
        req.getRequestDispatcher("/CreateReviews.jsp").forward(req, resp);
	}
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        String userName = req.getParameter("username");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("success", "Invalid UserName");
        } else 			try {
        	String content = req.getParameter("content");
        	String productId = req.getParameter("productId");
        	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        	Double rating = Double.parseDouble(req.getParameter("rating"));
        	BigDecimal ratingb=BigDecimal.valueOf(rating);
    		Date created = new Date();
			UsersDao usersDao = UsersDao.getInstance();
			ElectronicProductDetailsDao electronicProductDetailsDao=ElectronicProductDetailsDao.getInstance();
			ElectronicProductDetails electronicProductDetail=electronicProductDetailsDao.getProductByScraperOrder(productId);;
			Users user = usersDao.getUserByUserName (userName);
	        	Reviews review = new Reviews(created,ratingb,user,electronicProductDetail,content);
	        	review =reviewsDao.create(review);
	        	int reviewId=review.getReviewId();
	        	messages.put("success", "Successfully created review" + reviewId);
	            List<Reviews> reviews = new ArrayList<Reviews>();
	        	reviews = reviewsDao.getReviewsByElectronicProductDetailId(productId);
	            req.setAttribute("reviews",reviews);         
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        } 
        req.getRequestDispatcher("/FindReviews.jsp").forward(req,resp);
}
    }

