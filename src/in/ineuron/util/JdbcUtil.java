package in.ineuron.util;

import java.io.*;
import java.sql.*;
import java.util.*;

public class JdbcUtil {

	private JdbcUtil() {
	}
	
	static {
		// Step1: loading and register the Driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException, IOException {

		// Take the data from properties file
		FileInputStream fis = new FileInputStream("src\\in\\ineuron\\application\\Application.properties");
		Properties properties = new Properties();
		properties.load(fis);

		// Step2. Establish the Connection
		Connection connection = DriverManager.getConnection(properties.getProperty("url"),
				properties.getProperty("userId"), properties.getProperty("passWord"));
		return connection;
	}

	public static void cleanUp(Connection con, Statement statement, ResultSet resultSet) throws SQLException {
		// Step6. Close the resources
		if (con != null) {
			con.close();
		}
		if (statement != null) {
			statement.close();
		}
		if (resultSet != null) {
			resultSet.close();
		}
	}

}
