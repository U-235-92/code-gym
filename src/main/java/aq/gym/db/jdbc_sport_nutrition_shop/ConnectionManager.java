package aq.gym.db.jdbc_sport_nutrition_shop;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConnectionManager {

	private static Properties properties = new Properties();
	
	static {		
		loadProperties();
	}
	
	private static void loadProperties() {
		try {
			final String path = "src/main/java/aq/gym/db/jdbc_sport_nutrition_shop/db.properties";
			properties.load(new FileReader(path));
			String driver = properties.getProperty("db.driver");
			loadDriver(driver);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	private static void loadDriver(String driver) {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		String user = properties.getProperty("db.user");
		String password = properties.getProperty("db.password");
		String dbUrl = properties.getProperty("db.url");
		return DriverManager.getConnection(dbUrl, user, password);
	}
}
