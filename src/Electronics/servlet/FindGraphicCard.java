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
@WebServlet("/findgraphiccard")
public class FindGraphicCard extends HttpServlet{
	protected GraphicCardDao graphiccardDao;
	
	@Override
	public void init() throws ServletException {
		graphiccardDao = GraphicCardDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/FindGraphicCard.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String size = req.getParameter("size");
        System.out.print(size);
        if (size == null || size.trim().isEmpty()) {
        	size = "";
        }
        String manufacturer = req.getParameter("manufacturer");
        //System.out.print(manufacturer);
        if (manufacturer == null || manufacturer.trim().isEmpty()) {
        	manufacturer = "";
        }
        String dDR_GDDR = req.getParameter("dDR_GDDR");
        //System.out.print(dDR_GDDR);
        if (dDR_GDDR == null || dDR_GDDR.trim().isEmpty()) {
        	dDR_GDDR = "";
        }
        String capacity = req.getParameter("capacity");
        //System.out.print(capacity);
        if (capacity == null || capacity.trim().isEmpty()) {
        	capacity = "";
        }
        /*String hard_disk_size = req.getParameter("hard_disk_size");
        if (hard_disk_size == null || hard_disk_size.trim().isEmpty()) {
        	hard_disk_size = "%%";
        	System.out.print(hard_disk_size);
        };*/
        List<GraphicCard> graphiccards = new ArrayList<GraphicCard>();
        try {
        	graphiccards = graphiccardDao.getGraphicCardByParameters(size,manufacturer,dDR_GDDR);
	        	messages.put("success", "Successfully Searched");
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        req.setAttribute("graphiccards", graphiccards);
        req.getRequestDispatcher("/FindGraphicCard.jsp").forward(req, resp);
}
}
