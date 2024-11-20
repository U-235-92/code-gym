package aq.gym.db.jdbc_sport_nutrition_shop;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConnectionManager {

	private static Properties properties = new Properties();
	private static HikariDataSource hikariDataSource;
	
	static {		
		loadProperties();
		loadDriver();
		hikariDataSource = loadConnectionPool();
	}
	
	private static void loadProperties() {
		try {
			final String path = "src/main/java/aq/gym/db/jdbc_sport_nutrition_shop/db.properties";
			properties.load(new FileReader(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	private static void loadDriver() {
		try {
			String driver = properties.getProperty("db.driver");
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static HikariDataSource loadConnectionPool() {
		HikariConfig poolConfiguration = new HikariConfig();
		poolConfiguration.setJdbcUrl(properties.getProperty("db.url"));
		poolConfiguration.setUsername(properties.getProperty("db.user"));
		poolConfiguration.setPassword(properties.getProperty("db.password"));
		poolConfiguration.setConnectionTimeout(60 * 1000);
		poolConfiguration.setMaximumPoolSize(50);
		HikariDataSource hikariDataSource = new HikariDataSource(poolConfiguration);
		return hikariDataSource;
	}
	
	public static Connection getPooledConnection() throws SQLException {
		Connection connection = hikariDataSource.getConnection();
		connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
		return connection;
	}
	
	public static Connection getSimpleConnection() throws SQLException {
		String user = properties.getProperty("db.user");
		String password = properties.getProperty("db.password");
		String dbUrl = properties.getProperty("db.url");
		Connection connection = DriverManager.getConnection(dbUrl, user, password);
		connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
		return connection;
	}
}
