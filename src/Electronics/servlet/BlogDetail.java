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


@WebServlet("/blogdetail")
public class BlogDetail extends HttpServlet {
	
	protected BlogsDao blogsDao;
	protected CommentsDao commentsDao;
	
	@Override
	public void init() throws ServletException {
		blogsDao = BlogsDao.getInstance();
		commentsDao = CommentsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {        
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        int blogId = Integer.parseInt(req.getParameter("blogId"));
        Blogs blog= new Blogs(blogId);
        List<Comments> comments = new ArrayList<Comments>();
        	try {
        		 blog = blogsDao.getBlogById(blogId);
        		 comments=commentsDao.getCommentsByBlogId(blogId);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);}
        	messages.put("success", "Displaying results for " + blogId);
        	messages.put("previousAuthor", Integer.toString(blogId));
        req.setAttribute("blog", blog);
        req.setAttribute("comments", comments);
        req.getRequestDispatcher("/BlogsDetail.jsp").forward(req, resp);
	}
}


