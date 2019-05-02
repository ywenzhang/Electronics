package Electronics.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Electronics.dal.*;
import Electronics.model.*;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected UsersDao usersDao;
	@Override
	public void init() throws ServletException {
		usersDao = UsersDao.getInstance();
	}
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.print(1);
        Map<String, String> messages = new HashMap<String, String>();
		String uname = request.getParameter("uname");
		String pass = request.getParameter("pass");
		Users user = null;
		HttpSession session = request.getSession();
		request.setAttribute("messages",messages);
		try {
			user = usersDao.getUserByParameters(uname,pass);
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
			
		}
		if(user!=null) {
			session.setAttribute("username",uname);
			session.setAttribute("Logstate","Log Out");
			session.setAttribute("Logstatehref","/Electronics/Logout");
			request.getRequestDispatcher("/Blog.jsp").forward(request, response);
		}
		else {
			session.setAttribute("username",uname);
			session.setAttribute("Logstate","Log in");
			session.setAttribute("Logstatehref","/Electronics/Login");
			messages.put("error","Your username or password is wrong. Please try again.");
			request.getRequestDispatcher("/Login.jsp").forward(request, response);
		}
    }
	
	
	@Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//System.out.print(1);
        Map<String, String> messages = new HashMap<String, String>();
		String uname = request.getParameter("uname");
		String pass = request.getParameter("pass");
		Users user = null;
		HttpSession session = request.getSession();
		try {
			user = usersDao.getUserByParameters(uname,pass);
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
			
		}
		if(user!=null) {
			session.setAttribute("username",uname);
			session.setAttribute("Logstate","Log Out");
			session.setAttribute("Logstatehref","/Electronics/Logout");
			request.getRequestDispatcher("/Blog.jsp").forward(request, response);
		}
		else {
			session.setAttribute("username",uname);
			session.setAttribute("Logstate","Log in");
			session.setAttribute("Logstatehref","/Electronics/Login");
			messages.put("error","Your username or password is wrong. Please try again.");
			request.getRequestDispatcher("/Login.jsp").forward(request, response);
		}
    }

}
