import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class SQLConnection {

	private static final String MYSQL = "jdbc:mysql://";

	private static final String ORACLE = "jdbc:oracle:thin:@";

	private static final String SQLSERVER = "jdbc:microsoft:sqlserver://";

	public SQLConnection() {
	}

	/**
	 * 
	 * @param DBType
	 *            数据库类型
	 * @param url
	 *            数据库IP
	 * @param port
	 *            数据库端口
	 * @param user
	 *            数据库用户名
	 * @param password
	 *            数据库密码
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection(String DBType, String url, int port, String user, String password)
			throws SQLException {
		if ("mysql".equalsIgnoreCase(DBType))
			return getMySqlConn(url, port, user, password);
		if ("oracle".equalsIgnoreCase(DBType))
			return getOracleConn(url, port, user, password);
		if ("sqlserver".equals(DBType)) {
			return getSqlServerConn(url, port, user, password);
		}
		return null; 
	}

	public static void closeConn(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private static Connection getMySqlConn(String url, int port, String user, String password) {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");// 加载驱动
			conn = (Connection) DriverManager.getConnection(MYSQL + url + ":" + port, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	private static Connection getOracleConn(String url, int port, String user, String password) {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");// 加载驱动
			conn = (Connection) DriverManager.getConnection(ORACLE + url + ":" + port, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return conn;
	}

	private static Connection getSqlServerConn(String url, int port, String user, String password) {
		Connection conn = null;
		try {
			Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");// 加载驱动
			conn = (Connection) DriverManager.getConnection(SQLSERVER + url + ":" + port, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return conn;
	}
}
