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

/**
 * Servlet implementation class Recommendation
 */
@WebServlet("/Recommendation")
public class Recommendation extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected RecommendationDao recommendationDao;
	@Override
	public void init() throws ServletException {
		recommendationDao =recommendationDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/Recommendation.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
		//System.out.print(1);
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String low = req.getParameter("lowLaptop");
        String high = req.getParameter("highLaptop");
        String lowr = "0";
    	String highr = "10000";
        if (!low.equals("") || !high.equals("")) {
        	lowr = "0";
        	highr = "10000";
        	if(!low.equals("")) {
        		lowr=low;
        	}
        	if(!high.equals("")) {
        		highr=high;
        	}
        	List<Laptop> laptops = new ArrayList<Laptop>();
            try {
        	    laptops = recommendationDao.getLaptopBudget(lowr,highr);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
            req.setAttribute("laptops", laptops);
        }
        low = req.getParameter("lowDesktop");
        high = req.getParameter("highDesktop");
        if (!low.equals("") || !high.equals("")) {
        	lowr = "0";
        	highr = "10000";
        	if(!low.equals("")) {
        		lowr=low;
        	}
        	if(!high.equals("")) {
        		highr=high;
        	}
        	List<Desktop> desktops = new ArrayList<Desktop>();
            try {
            	desktops = recommendationDao.getDesktopBudget(lowr,highr);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
            req.setAttribute("desktops", desktops);
        }
        low = req.getParameter("lowCPU");
        high = req.getParameter("highCPU");
        if (!low.equals("") || !high.equals("")) {
        	lowr = "0";
        	highr = "10000";
        	if(!low.equals("")) {
        		lowr=low;
        	}
        	if(!high.equals("")) {
        		highr=high;
        	}
        	List<CPUProcessor> cpus = new ArrayList<CPUProcessor>();
            try {
        	    cpus = recommendationDao.getCPUProcessorByBudget(lowr,highr);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
            req.setAttribute("cpuprocessors", cpus);
        }
        low = req.getParameter("lowMemory");
        high = req.getParameter("highMemory");
        if (!low.equals("") || !high.equals("")) {
        	lowr = "0";
        	highr = "10000";
        	if(!low.equals("")) {
        		lowr=low;
        	}
        	if(!high.equals("")) {
        		highr=high;
        	}
        	List<RamMemory> memorys = new ArrayList<RamMemory>();
            try {
            	memorys = recommendationDao.getRamMemoryByBudget(lowr,highr);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
            req.setAttribute("rammemorys", memorys);
        }
        low = req.getParameter("lowGPU");
        high = req.getParameter("highGPU");
        if (!low.equals("") || !high.equals("")) {
        	lowr = "0";
        	highr = "10000";
        	if(!low.equals("")) {
        		lowr=low;
        	}
        	if(!high.equals("")) {
        		highr=high;
        	}
        	List<GraphicCard> gpus = new ArrayList<GraphicCard>();
            try {
            	gpus= recommendationDao.getGraphicCardByBudget(lowr,highr);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
            req.setAttribute("graphiccards", gpus);
        }
        low = req.getParameter("lowHardDisk");
        high = req.getParameter("highHardDisk");
        if (!low.equals("") || !high.equals("")) {
        	lowr = "0";
        	highr = "10000";
        	if(!low.equals("")) {
        		lowr=low;
        	}
        	if(!high.equals("")) {
        		highr=high;
        	}
        	List<HardDisk> harddisks = new ArrayList<HardDisk>();
            try {
            	harddisks = recommendationDao.getHardDiskByBudget(lowr,highr);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
            req.setAttribute("harddisks", harddisks);
        }
        req.getRequestDispatcher("/Recommendation.jsp").forward(req, resp);
}
}
