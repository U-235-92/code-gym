package aq.gym.db.jdbc_sport_nutrition_shop;

import java.sql.Connection;
import java.sql.SQLException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Transaction {

	private Connection connection;
	
	public void begin() {
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void end() {
		try {
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void commit() {
		try {
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void rollback() {
		try {
			connection.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
}
