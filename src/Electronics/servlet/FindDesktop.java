package Electronics.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Electronics.dal.DesktopDao;
import Electronics.model.Desktop;
@WebServlet("/finddesktop")
public class FindDesktop extends HttpServlet{
	protected DesktopDao desktopDao;
	
	@Override
	public void init() throws ServletException {
		desktopDao = desktopDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/FindDesktop.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String operating_system = req.getParameter("operating_system");
        if (operating_system == null || operating_system.trim().isEmpty()) {
        	operating_system = "";
        }
        String CPU_Model_family = req.getParameter("CPU_Model_family");
        if (CPU_Model_family == null || CPU_Model_family.trim().isEmpty()) {
        	CPU_Model_family = "";
        }
        String memory_size = req.getParameter("memory_size");
        if (memory_size == null || memory_size.trim().isEmpty()) {
        	memory_size = "";
        }
        String hard_disk_size = req.getParameter("hard_disk_size");
        if (hard_disk_size == null || hard_disk_size.trim().isEmpty()) {
        	hard_disk_size = "";
//        	System.out.print(hard_disk_size);
        };
        List<Desktop> desktops = new ArrayList<Desktop>();
        try {
        	    desktops = desktopDao.getDesktopByParameters(operating_system,CPU_Model_family,memory_size,hard_disk_size);
	        	messages.put("success", "Successfully Searched");
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        req.setAttribute("desktops", desktops);
        req.getRequestDispatcher("/FindDesktop.jsp").forward(req, resp);
}
}