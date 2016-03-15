package rsvet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EasyConnect {
	
	// статические параметры подключения к БД
	static String url = "jdbc:oracle:thin:@ebsproj8.russvet.ru:1561:WMS12";
	static String username = "apps";
	static String password = "apps";
	
	static Connection conn = null;
	static Statement st = null;

	//метод соединяется с БД и возвращает Connection 
	public static Connection getConnectionDB() throws SQLException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is JDBC Driver?");
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	//метод возвращает коллекцию с именами таблиц 
	public static ArrayList<String> getTableNames() throws SQLException {

		ArrayList<String> tableNamesArray = new ArrayList<String>();
		Connection conn = getConnectionDB();
		String query = "select table_name from all_tables where table_name like '%FND_USER%'";
		st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		ResultSetMetaData metadata = rs.getMetaData();
		int numberOfColumns = metadata.getColumnCount();
		while (rs.next()) {
			int i = 1;
			while (i <= numberOfColumns) {
				tableNamesArray.add(rs.getString(i++));
			}
		}
		conn.close();
		return tableNamesArray;
	}

	//метод возвращает коллекцию с данными из выбранной таблицы 
	public static List<Map<String, Object>> getTableInfo(String tname) throws SQLException {

		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		Connection conn = getConnectionDB();
		String query = "SELECT * FROM " + tname;
		st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		ResultSetMetaData metaData = rs.getMetaData();
		int columnCount = metaData.getColumnCount();

		while (rs.next()) {
			Map<String, Object> columns = new LinkedHashMap<String, Object>();

			for (int i = 1; i <= columnCount; i++) {
				columns.put(metaData.getColumnLabel(i), rs.getObject(i));
			}

			rows.add(columns);
		}
		return rows;
	}
}
