package aq.gym.db.concurrency;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

public class PhantomRead {

	private static final String SQL_INSERT_PERSON = "INSERT INTO persons (name, age) VALUES (?, ?)";
	private static final String SQL_SELECT_PERSONS = "SELECT * FROM persons";
	private static final int TRANSACTION_ISOLATION_LEVEL = Connection.TRANSACTION_REPEATABLE_READ; //replace on SERIALIZABLE to avoid non phantom read (MySql enough TRANSACTION_REPEATABLE_READ)
	
	public static void main(String[] args) {
		PhantomRead phantomReadInstant = new PhantomRead();
		int personID = phantomReadInstant.insertData();
		phantomReadInstant.phantomReadDemo(personID);
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
	
	private void phantomReadDemo(int personID) {
		readData();
		phantomInsertData(personID, 25);
	}
	
	private void readData() {
		Thread thread = new Thread(() -> {			
			try(Connection connection = ConnectionManager.getConnection()) {
				connection.setAutoCommit(false);
				connection.setTransactionIsolation(TRANSACTION_ISOLATION_LEVEL);
				Statement statement = connection.createStatement();
				phantomReadData(statement);
				TimeUnit.SECONDS.sleep(3);
				phantomReadData(statement);
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
	
	private void phantomReadData(Statement statement) throws SQLException {
		ResultSet resultSet = statement.executeQuery(SQL_SELECT_PERSONS);
		while(resultSet.next()) {
			int personID = resultSet.getInt("id");
			int age = resultSet.getInt("age");
			String name = resultSet.getString("name");
			Person person = new Person(personID, age, name);
			System.out.println(person);
		}
	}
	
	private void phantomInsertData(int personID, int age) {
		try(Connection connection = ConnectionManager.getConnection()) {
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(TRANSACTION_ISOLATION_LEVEL);
			TimeUnit.SECONDS.sleep(1);
			System.out.println("Inertion of a new person...");
			Person sarah = new Person(22, "Sarah");
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_PERSON);
			preparedStatement.setString(1, sarah.getName());
			preparedStatement.setInt(2, sarah.getAge());
			preparedStatement.executeUpdate();
			connection.commit();
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
