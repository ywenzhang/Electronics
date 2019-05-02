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
import Electronics.model.RamMemory;
@WebServlet("/findrammemory")
public class FindRamMemory extends HttpServlet{
	protected RamMemoryDao rammemoryDao;
	
	@Override
	public void init() throws ServletException {
		rammemoryDao = RamMemoryDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/FindRamMemory.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
       
        String memory_Size = req.getParameter("memory_Size");
        if (memory_Size == null || memory_Size.trim().isEmpty()) {
        	memory_Size = "";
        }
        String DDR = req.getParameter("DDR");
        if (DDR == null || DDR.trim().isEmpty()) {
        	DDR = "";
        }
        /*String hard_disk_size = req.getParameter("hard_disk_size");
        if (hard_disk_size == null || hard_disk_size.trim().isEmpty()) {
        	hard_disk_size = "%%";
        	System.out.print(hard_disk_size);
        };*/
        List<RamMemory> rammemorys = new ArrayList<RamMemory>();
        try {
        	rammemorys = rammemoryDao.getRamMemoryByParameters(memory_Size,DDR);
	        	messages.put("success", "Successfully Searched");
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        req.setAttribute("rammemorys", rammemorys);
        req.getRequestDispatcher("/FindRamMemory.jsp").forward(req, resp);
}
}
