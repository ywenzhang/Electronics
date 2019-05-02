package Electronics.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.print(1);
		Map<String, String> messages = new HashMap<String, String>();
		request.setAttribute("messages", messages);
		HttpSession session = request.getSession();
		session.removeAttribute("username");
		session.setAttribute("Logstate","Log In");
		session.setAttribute("Logstatehref","/Electronics/Login");
		request.getRequestDispatcher("/home").forward(request, response);
	}
	@Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.print(1);
		Map<String, String> messages = new HashMap<String, String>();
		request.setAttribute("messages", messages);
		HttpSession session = request.getSession();
		session.removeAttribute("username");
		session.setAttribute("Logstate","Log In");
		session.setAttribute("Logstatehref","/Electronics/Login");
		request.getRequestDispatcher("/home").forward(request, response);
    }

}