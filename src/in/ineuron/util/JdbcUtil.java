package in.ineuron.util;

import java.io.*;
import java.sql.*;
import java.util.*;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

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

		HikariConfig config = new HikariConfig("src\\in\\ineuron\\application\\Application.properties");
		HikariDataSource dataSource = new HikariDataSource(config);
		return dataSource.getConnection();
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
