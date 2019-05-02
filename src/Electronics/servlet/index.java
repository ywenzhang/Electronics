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
import javax.servlet.http.HttpSession;

import Electronics.dal.DesktopDao;
import Electronics.dal.RecommendationDao;
import Electronics.model.CPUProcessor;
import Electronics.model.Desktop;
import Electronics.model.GraphicCard;
import Electronics.model.HardDisk;
import Electronics.model.Laptop;
import Electronics.model.RamMemory;

/**
 * Servlet implementation class index
 */
@WebServlet("/home")
public class index extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected RecommendationDao recommendationDao;
	public void init() throws ServletException {
		recommendationDao = RecommendationDao.getInstance();
	}  
    /**
     * @see HttpServlet#HttpServlet()
     */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req,resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		if(session.getAttribute("username")==null) {
			session.setAttribute("Logstate","Log in");
			session.setAttribute("Logstatehref","/Electronics/Login");
		}
        Map<String, String> messages = new HashMap<String, String>();
        try {
        	List<Desktop> desktops = new ArrayList<Desktop>();
    	    desktops = recommendationDao.getDesktopTop(3);
    	    req.setAttribute("desktops", desktops);
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        try {
        	List<Laptop> laptops = new ArrayList<Laptop>();
        	laptops = recommendationDao.getLaptopTop(3);
    	    req.setAttribute("laptops", laptops);
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        try {
        	List<CPUProcessor> cpus = new ArrayList<CPUProcessor>();
        	cpus = recommendationDao.getCPUProcessorTop(3);
    	    req.setAttribute("cpus", cpus);
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        try {
        	List<RamMemory> memorys = new ArrayList<RamMemory>();
        	memorys = recommendationDao.getRamMemoryTop(3);
    	    req.setAttribute("memorys", memorys);
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        try {
        	List<HardDisk> harddisks = new ArrayList<HardDisk>();
        	harddisks = recommendationDao.getHardDiskTop(3);
    	    req.setAttribute("harddisks", harddisks);
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        try {
        	List<GraphicCard> graphiccards = new ArrayList<GraphicCard>();
        	graphiccards = recommendationDao.getGraphicCardTop(3);
    	    req.setAttribute("graphiccards", graphiccards);
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        req.setAttribute("messages", messages);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}

}
