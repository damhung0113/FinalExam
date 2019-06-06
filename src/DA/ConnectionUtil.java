package DA;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	
	private static Connection con = null;
	
	public static Connection getConnectionUtil() throws ClassNotFoundException, SQLException {
		if(con == null) {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost/test";
			String user = "root";
			String password = "123456";
			con = DriverManager.getConnection(url, user, password);
		}
		return con;
	}
}