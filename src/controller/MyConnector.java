package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MyConnector {
	private  String url;
	private  Properties prop = new Properties();
	private  Connection conn;
	{
		url = "jdbc:derby:db_derby";
prop.setProperty("user", "driver");
		prop.setProperty("password","12345");
		prop.setProperty("create", "true");
	}
	public  Connection getConnection(){
		try {
			conn = DriverManager.getConnection(url, prop);
		} catch (SQLException e) {
			System.err.println("Проблеми із підключенням до " + url);
			e.printStackTrace();
		}
		return conn;
	}

	public  void closeConnection() {
		try {
			if(conn != null)
				conn.close();
		} catch (SQLException e) {
			System.err.println("Проблеми із закриттям connection до " + url);
			e.printStackTrace();
		}
	}

	@Override
	protected void finalize() throws Throwable {
		if(conn != null)
			conn.close();
		super.finalize();
	}
}

