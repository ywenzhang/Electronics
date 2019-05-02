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
@WebServlet("/findcpuprocessor")
public class FindCPUProcessor extends HttpServlet{
	protected CPUProcessorDao cpuprocessorDao;
	
	@Override
	public void init() throws ServletException {
		cpuprocessorDao = CPUProcessorDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/FindCPUProcessor.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String series = req.getParameter("series");
        if (series == null || series.trim().isEmpty()) {
        	series = "";
        }
        String cache = req.getParameter("cache");
        if (cache == null || cache.trim().isEmpty()) {
        	cache = "";
        }
        String cacheSize = req.getParameter("cacheSize");
        if (cacheSize == null || cacheSize.trim().isEmpty()) {
        	cacheSize = "";
        }
        /*String hard_disk_size = req.getParameter("hard_disk_size");
        if (hard_disk_size == null || hard_disk_size.trim().isEmpty()) {
        	hard_disk_size = "%%";
        	System.out.print(hard_disk_size);
        };*/
        List<CPUProcessor> cpuprocessors = new ArrayList<CPUProcessor>();
        try {
        	cpuprocessors = cpuprocessorDao.getCPUProcessorByParameters(series,cache,cacheSize);
	        	messages.put("success", "Successfully Searched");
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        req.setAttribute("cpuprocessors", cpuprocessors);
        req.getRequestDispatcher("/FindCPUProcessor.jsp").forward(req, resp);
}
}
