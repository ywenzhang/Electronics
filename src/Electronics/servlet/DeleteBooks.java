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

@WebServlet("/deletebooks")
public class DeleteBooks extends HttpServlet {
	
	protected BooksDao booksDao;
	
	@Override
	public void init() throws ServletException {
		booksDao = BooksDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
        List<Books> books = new ArrayList<Books>();
        Map<String, String> messages = new HashMap<String, String>();
        messages.put("title", "Delete Book");     
        req.setAttribute("messages", messages);
        req.getRequestDispatcher("/DeleteBooks.jsp").forward(req, resp);
	}
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        String id = req.getParameter("id");
        if (id == null || id.trim().isEmpty()) {
            messages.put("title", "Invalid ID");
            messages.put("disableSubmit", "true");
        } else {
        	try {
        		Books book = new Books(id);
        		book = booksDao.delete(book);
        	if (book == null) {
	            messages.put("title", "Successfully deleted " + id);
	            messages.put("disableSubmit", "true");
	        } else {
	        	messages.put("title", "Failed to delete " + id);
	        	messages.put("disableSubmit", "false");
	        }
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
    }
    
    req.getRequestDispatcher("/DeleteBooks.jsp").forward(req, resp);
}
}
