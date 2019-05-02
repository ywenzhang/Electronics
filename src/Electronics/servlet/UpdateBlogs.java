package Electronics.servlet;

import Electronics.dal.*;

import Electronics.model.*;

import java.io.IOException;
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


@WebServlet("/updateblogs")
public class UpdateBlogs extends HttpServlet {
	protected CommentsDao commentsDao;
	protected BlogsDao blogsDao;
	protected UsersDao usersDao;
	@Override
	public void init() throws ServletException {
		blogsDao = BlogsDao.getInstance();
		commentsDao = CommentsDao.getInstance();
		usersDao = UsersDao.getInstance();

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
            List<Comments> comments = new ArrayList<Comments>();
            try {
            comments=commentsDao.getCommentsByBlogId(blogId);
            req.setAttribute("comments", comments);}
            catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            } 
        }    
        req.getRequestDispatcher("/UpdateBlog.jsp").forward(req, resp);
	}
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
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
        			messages.put("success", "UserName does not exist. No update to perform.");
        		} else {
        		String newContent = req.getParameter("newContent");
    			UsersDao usersDao = UsersDao.getInstance();
    			Users user = usersDao.getUserByUserName (userName);
    	        	blog =blogsDao.updateContent(blog, newContent);
            		req.setAttribute("blog", blog);
            		req.setAttribute("user", user);
    	        	messages.put("success", "Successfully updated Blog" + blogId);
        	        }
        		}
        	 catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
            List<Comments> comments = new ArrayList<Comments>();
            try {
            comments=commentsDao.getCommentsByBlogId(blogId);
            req.setAttribute("comments", comments);}
            catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            } 
        }    
        req.getRequestDispatcher("/BlogsDetail.jsp").forward(req, resp);
    }
}
