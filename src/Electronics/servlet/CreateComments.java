package Electronics.servlet;

import Electronics.dal.*;
import Electronics.model.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DateFormat;
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
import javax.servlet.http.HttpSession;

@WebServlet("/createcomment")
public class CreateComments extends HttpServlet {
	
	protected CommentsDao commentsDao;
	protected BlogsDao blogsDao;
	protected UsersDao usersDao;
	@Override
	public void init() throws ServletException {
		commentsDao = CommentsDao.getInstance();
		blogsDao= BlogsDao.getInstance();
		usersDao= UsersDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        HttpSession session = req.getSession();
        String userName = (String) session.getAttribute("username");
        int blogId = Integer.parseInt(req.getParameter("blogId"));
        String testblogId=String.valueOf(blogId);
        if (testblogId == null || testblogId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid Product ID.");
        } else {
        	try {
        		Blogs blog = blogsDao.getBlogById(blogId);
        		if(blog == null) {
        			messages.put("success", "blog does not exist. No update to perform.");
        		} else {
    			Users user = usersDao.getUserByUserName(userName);
            		req.setAttribute("blog", blog);
            		req.setAttribute("user", user);
        	        }
        		}
        	 catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        req.getRequestDispatcher("/CreateComments.jsp").forward(req, resp);
	}}
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
            int blogId = Integer.parseInt(req.getParameter("blogId"));
        	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    		Date createdTime = new Date();
        	Double rating = Double.parseDouble(req.getParameter("vote"));
        	BigDecimal vote=BigDecimal.valueOf(rating);			
        	UsersDao usersDao = UsersDao.getInstance();
        	BlogsDao blogsDao = BlogsDao.getInstance();
			Users user = usersDao.getUserByUserName (userName);
			Blogs blog = blogsDao.getBlogById(blogId);
			Comments comment = new Comments(blog,user,vote,content,createdTime);
			comment= commentsDao.create(comment);
	        	int commentId=comment.getCommentId();
	        	messages.put("success", "Successfully created commentId" + commentId);
	            req.setAttribute("comment", comment);
	            req.setAttribute("blog", blog);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        } 
        List<Comments> comments = new ArrayList<Comments>();
        try {
        int blogId = Integer.parseInt(req.getParameter("blogId"));
        comments=commentsDao.getCommentsByBlogId(blogId);
        req.setAttribute("comments", comments);}
        catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        } 
        req.getRequestDispatcher("/BlogsDetail.jsp").forward(req, resp);
}
    }

