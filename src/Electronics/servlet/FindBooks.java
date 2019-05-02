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


@WebServlet("/findbooks")
public class FindBooks extends HttpServlet {
	
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
        req.setAttribute("messages", messages);

        List<Books> matchbooks = new ArrayList<Books>();

        String author = req.getParameter("author");
        if (author == null || author.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {
        	try {
        		matchbooks = booksDao.getBooksByAuthor(author);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + author);
        	messages.put("previousAuthor", author);
        }
        req.setAttribute("matchbooks", matchbooks);
        req.getRequestDispatcher("/FindBooks.jsp").forward(req, resp);
	}
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Books> matchbooks = new ArrayList<Books>();
        String author = req.getParameter("author");
        if (author == null || author.trim().isEmpty()) {
            messages.put("success", "Please enter a valid author name.");
        } else {
        	try {
        		matchbooks = booksDao.getBooksByAuthor(author);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for Author " + author);
        }
        req.setAttribute("matchbooks", matchbooks);
        
        req.getRequestDispatcher("/FindBooks.jsp").forward(req, resp);
    }
}
