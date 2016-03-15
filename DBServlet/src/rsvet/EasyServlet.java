package rsvet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Through this Servlet we are getting info from Database.
 * Start Servlet page - http://server:port/DBServlet/show
 * 
 */

public class EasyServlet extends HttpServlet {
	
	public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {	
		String tname = request.getParameter("tname"); //tname - name of choosed table
		if (tname == null) { //if param is null open index.jsp where choose table name
			try {
				ArrayList<String> tableNames = EasyConnect.getTableNames(); //getting collection with Database tables names
				getServletContext().setAttribute("tableNames", tableNames);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			RequestDispatcher rDispatch = getServletContext().getRequestDispatcher("/index.jsp");
			rDispatch.forward(request, response);
		}
		else{
			try {
				List<Map<String, Object>> rows = EasyConnect.getTableInfo(tname); //getting collection from choosed table 'select * from tname'
				
				getServletContext().setAttribute("rows", rows);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			RequestDispatcher rDispatch = getServletContext().getRequestDispatcher("/response.jsp");
			rDispatch.forward(request, response);
		}
	}
}