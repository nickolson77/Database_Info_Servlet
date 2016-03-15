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

public class EasyServlet extends HttpServlet {
	
	public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {	
		String tname = request.getParameter("tname"); //tname - содержит имя выбранной таблицы
		if (tname == null) { //если параметр пуст, то открываем index.jsp, где выбираем таблицу
			try {
				ArrayList<String> tableNames = EasyConnect.getTableNames(); //получаем коллекцию с именами таблиц
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
				List<Map<String, Object>> rows = EasyConnect.getTableInfo(tname); //получаем коллекцию с данными из запроса 'select * from tname'
				
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