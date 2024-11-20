package aq.gym.db.concurrency;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

public class RepeatableRead {

	private static final String SQL_INSERT_PERSON = "INSERT INTO persons (name, age) VALUES (?, ?)";
	private static final String SQL_SELECT_PERSONS = "SELECT * FROM persons";
	private static final String SQL_UPDATE_PERSON_AGE = "UPDATE persons SET age = ? WHERE id = ?";
	private static final int TRANSACTION_ISOLATION_LEVEL = Connection.TRANSACTION_READ_COMMITTED; //replace on REPEATABLE_READ to avoid non repeatable read 
	
	public static void main(String[] args) {
		RepeatableRead repeatableReadInstant = new RepeatableRead();
		int personID = repeatableReadInstant.insertData();
		repeatableReadInstant.repeatableReadDemo(personID);
	}
	
	private int insertData() {
		int personID = 0;
		try(Connection connection = ConnectionManager.getConnection()) {
			Person john = new Person(30, "John");
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_PERSON, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, john.getName());
			preparedStatement.setInt(2, john.getAge());
			preparedStatement.executeUpdate();
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			if(resultSet.next()) {
				personID = resultSet.getInt(1);
			}
			TimeUnit.SECONDS.sleep(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return personID;
	}
	
	private void repeatableReadDemo(int personID) {
		readData();
		updateData(personID, 25);
	}
	
	private void readData() {
		Thread thread = new Thread(() -> {			
			try(Connection connection = ConnectionManager.getConnection()) {
				connection.setTransactionIsolation(TRANSACTION_ISOLATION_LEVEL);
				connection.setAutoCommit(false);
				repeatableReadData(connection);
				TimeUnit.SECONDS.sleep(3);
				repeatableReadData(connection);
				connection.commit();
				connection.setAutoCommit(true);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});
		thread.start();
	}
	
	private void repeatableReadData(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(SQL_SELECT_PERSONS);
		while(resultSet.next()) {
			int personID = resultSet.getInt("id");
			int age = resultSet.getInt("age");
			String name = resultSet.getString("name");
			Person person = new Person(personID, age, name);
			System.out.println(person);
		}
	}
	
	private void updateData(int personID, int age) {
		try(Connection connection = ConnectionManager.getConnection()) {
			TimeUnit.SECONDS.sleep(1);
			System.out.println("Updation of person's data...");
			connection.setTransactionIsolation(TRANSACTION_ISOLATION_LEVEL);
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_PERSON_AGE);
			preparedStatement.setInt(1, age);
			preparedStatement.setInt(2, personID);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
