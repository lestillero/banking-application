package util;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class JDBCConnection { // GOOD TO GO
	
	private static Connection conn = null;
	
	public static Connection getConnection() {
		
		try {
			
			// if connection does not exist, create one and return it
			if (conn == null) {
				
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				Properties props = new Properties();
				FileInputStream input = new FileInputStream("src/main/resources/connection.properties");
				props.load(input);
				
				String url = props.getProperty("url");
				String username = props.getProperty("username");
				String password = props.getProperty("password");
				
				conn = DriverManager.getConnection(url, username, password);
				return conn;
				
			} else {
				
				// return connection that already exists
				return conn;
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		
		System.out.println(getConnection());
	}

}