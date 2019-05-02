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
import javax.servlet.http.HttpSession;


@WebServlet("/deleteblog")
public class DeleteBlogs extends HttpServlet {
	
	protected BlogsDao blogsDao;
	
	@Override
	public void init() throws ServletException {
		
		blogsDao = BlogsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        int blogId = Integer.parseInt(req.getParameter("blogId"));
        	try {
        		Blogs blog = blogsDao.getBlogById(blogId);
                req.setAttribute("blog",blog);   
        		}
            catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }        
        req.getRequestDispatcher("/DeleteBlogs.jsp").forward(req, resp);
	}
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        String decision = req.getParameter("decision");
        List<Blogs> blogs = new ArrayList<Blogs>();
        HttpSession session = req.getSession();
        String userName = (String) session.getAttribute("username");
        int blogId = Integer.parseInt(req.getParameter("blogId"));
        if (decision.equals("yes")) {
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid UserName.");
        } else {
        	try {
        		Blogs blog = blogsDao.getBlogById(blogId);
        		if (blog.getUser().getUsername().equals(userName))
                {blog= blogsDao.delete(blog);
        		blogs = blogsDao.getBlogsByUserName(userName);
                req.setAttribute("blogs",blogs);   
            	messages.put("success", "Successfully deleted blog " + blogId);
        		}
        		else {
                messages.put("success", "You don't have the right to delete this blog " + blogId);
        		}
        		}
        		catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }        }
        req.getRequestDispatcher("/FindYourBlogs.jsp").forward(req, resp);
    }   
        else {
            messages.put("success", "Nothing is deleted" + decision);
        }
    req.getRequestDispatcher("/FindYourBlogs.jsp").forward(req, resp);	
	}
}