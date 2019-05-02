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

import Electronics.dal.*;
import Electronics.model.*;
@WebServlet("/findharddisk")
public class FindHardDisk extends HttpServlet{
	protected HardDiskDao harddiskDao;
	
	@Override
	public void init() throws ServletException {
		harddiskDao = HardDiskDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/FindHardDisk.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
       
        String manufacturer = req.getParameter("manufacturer");
        if (manufacturer == null || manufacturer.trim().isEmpty()) {
        	manufacturer = "";
        }
        String capacity = req.getParameter("capacity");
        if (capacity == null || capacity.trim().isEmpty()) {
        	capacity = "";
        }
        /*String hard_disk_size = req.getParameter("hard_disk_size");
        if (hard_disk_size == null || hard_disk_size.trim().isEmpty()) {
        	hard_disk_size = "%%";
        	System.out.print(hard_disk_size);
        };*/
        List<HardDisk> harddisks = new ArrayList<HardDisk>();
        try {
        	harddisks = harddiskDao.getHardDiskByParameters(manufacturer,capacity);
	        	messages.put("success", "Successfully Searched");
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        req.setAttribute("harddisks", harddisks);
        req.getRequestDispatcher("/FindHardDisk.jsp").forward(req, resp);
}
}
