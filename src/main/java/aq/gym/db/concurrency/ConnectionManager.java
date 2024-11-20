package aq.gym.db.concurrency;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {

	private static Properties dbConnectionProperties = new Properties();
	
	static {
		loadProperties();
		loadDriver();
	}
	
	private static void loadProperties() {
		try {
			final String path = "src/main/java/aq/gym/db/concurrency/db_connection_properties.properties";
			dbConnectionProperties.load(new FileReader(path ));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void loadDriver() {
		try {
			String driver = dbConnectionProperties.getProperty("db.driver");
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException {
		String user = dbConnectionProperties.getProperty("db.user");
		String password = dbConnectionProperties.getProperty("db.password");
		String dbUrl = dbConnectionProperties.getProperty("db.url");
		Connection connection = DriverManager.getConnection(dbUrl, user, password);
		return connection;
	}
}
