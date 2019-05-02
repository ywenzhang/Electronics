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


@WebServlet("/createblog")
public class CreateBlog extends HttpServlet {
	
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
        req.getRequestDispatcher("/CreateBlog.jsp").forward(req, resp);
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
        	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    		Date created = new Date();
			UsersDao usersDao = UsersDao.getInstance();
			Users user = usersDao.getUserByUserName (userName);
	        	Blogs blog = new Blogs(created,user,content);
	        	blog =blogsDao.create(blog);
	        	int blogId=blog.getBlogId();
	        	messages.put("success", "Successfully created " + blogId);
	            List<Blogs> blogs = new ArrayList<Blogs>();  
        		blogs = blogsDao.getBlogsByUserName(userName);
	        	req.setAttribute("user", user);
	        	req.setAttribute("blogs", blogs);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        } 
        req.getRequestDispatcher("/FindYourBlogs.jsp").forward(req,resp);
}
    }

