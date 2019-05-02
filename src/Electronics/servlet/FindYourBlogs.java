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


@WebServlet("/findyourblogs")
public class FindYourBlogs extends HttpServlet {
	private static final long serialVersionUID = 1L;	
	protected BlogsDao blogsDao;
	protected UsersDao usersDao;
	@Override
	public void init() throws ServletException {
		blogsDao = BlogsDao.getInstance();
		usersDao = UsersDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
        List<Blogs> blogs = new ArrayList<Blogs>();  
 
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        HttpSession session = req.getSession();
        String userName = (String) session.getAttribute("username");
        Users user;
		try {
			user = usersDao.getUserByUserName(userName);
			req.setAttribute("user", user); 
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid UserName.");
        } else {
        	try {
        		blogs = blogsDao.getBlogsByUserName(userName);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + userName);
        	messages.put("previousUserName", userName);
        }
        req.setAttribute("blogs", blogs); 
        req.getRequestDispatcher("/FindYourBlogs.jsp").forward(req, resp);
	}
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Blogs> blogs = new ArrayList<Blogs>();
        HttpSession session = req.getSession();
        String userName = (String) session.getAttribute("username");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid UserName.");
        } else {
        	try {
        		blogs = blogsDao.getBlogsByUserName(userName);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for UserName " + userName);
        }
        req.setAttribute("blogs",blogs);         
        req.getRequestDispatcher("/FindYourBlogs.jsp").forward(req, resp);
    }
}
