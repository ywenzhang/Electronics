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


@WebServlet("/deletecomment")
public class DeleteComments extends HttpServlet {
	
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
        int commentId = Integer.parseInt(req.getParameter("commentId"));
        int blogId = Integer.parseInt(req.getParameter("blogId"));
        String testcommentId=String.valueOf(commentId);
        if (testcommentId == null || testcommentId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid Product ID.");
        } 
        else {       	
        		try {
					Blogs blog = blogsDao.getBlogById(blogId);
	        		Comments comment= commentsDao.getCommentById(commentId);
	        		req.setAttribute("blog", blog);
            		req.setAttribute("comment", comment);
        	    req.getRequestDispatcher("/DeleteComment.jsp").forward(req, resp);
                } catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
	}
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        HttpSession session = req.getSession();
        String userName = (String) session.getAttribute("username");  
        String decision = req.getParameter("decision");
        int commentId = Integer.parseInt(req.getParameter("commentId"));
        int blogId = Integer.parseInt(req.getParameter("blogId"));
        String testcommentId=String.valueOf(commentId);
        List<Comments> comments = new ArrayList<Comments>(); 
        if (decision.equals("yes")) {
        if (testcommentId == null || testcommentId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid Comment ID.");
        } else {
        	try {
        		Comments comment = commentsDao.getCommentById(commentId);
        		if(comment == null) {
        			messages.put("success", "Comment does not exist. No update to perform.");
        		} 
        		else {
        			comment=commentsDao.delete(comment);
        			Users user = usersDao.getUserByUserName (userName);
            		Blogs blog = blogsDao.getBlogById(blogId);
            		comments=commentsDao.getCommentsByBlogId(blogId);
               		req.setAttribute("comments", comments);
            		req.setAttribute("blog", blog);
            		req.setAttribute("user", user);
    	        	messages.put("success", "Successfully Deleted Comment " + commentId);
        		}}
        	catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);}}}
        	
        else{
            messages.put("success", "Nothing is deleted" + decision);
        }
        req.getRequestDispatcher("/BlogsDetail.jsp").forward(req, resp);
        }}


