package aq.gym.db.concurrency;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

public class DirtyRead {

	private static final String SQL_INSERT_PERSON = "INSERT INTO persons (name, age) VALUES (?, ?)";
	private static final String SQL_UPDATE_PERSON_AGE = "UPDATE persons SET age = ? WHERE id = ?";
	private static final String SQL_SELECT_PERSONS = "SELECT * FROM persons";
	private static final int TRANSACTION_ISOLATION_LEVEL = Connection.TRANSACTION_READ_UNCOMMITTED; //replace on READ_COMMITTED to avoid dirty read
	
	public static void main(String[] args) {
		DirtyRead dirtyReadInstant = new DirtyRead();
		Person john = new Person(30, "John");
		int generatedPersonID = dirtyReadInstant.putPersonOnDatabase(john);
		dirtyReadInstant.dirtyReadDemo(generatedPersonID, 25);
	}
	
	private int putPersonOnDatabase(Person person) {
		int generatedPersonID = 0;
		try(Connection connection = ConnectionManager.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_PERSON, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, person.getName());
			preparedStatement.setInt(2, person.getAge());
			preparedStatement.executeUpdate();
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			if(resultSet.next()) {
				generatedPersonID = resultSet.getInt(1);
				System.out.println(person + " with id = " + generatedPersonID + " was added on database!");
				TimeUnit.SECONDS.sleep(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return generatedPersonID;
	}
	
	private void dirtyReadDemo(int personID, int age) {
		bobUpdatePerson(personID, age);
		aliceDirtyReadPersons();
	}
	
	private void bobUpdatePerson(final int personID, int age) {
		Thread bobThread = new Thread(() -> {			
			try(Connection bobConnection = ConnectionManager.getConnection()) {
				bobConnection.setTransactionIsolation(TRANSACTION_ISOLATION_LEVEL);
				bobConnection.setAutoCommit(false);
				PreparedStatement preparedStatement = bobConnection.prepareStatement(SQL_UPDATE_PERSON_AGE);
				preparedStatement.setInt(1, age);
				preparedStatement.setInt(2, personID);
				TimeUnit.SECONDS.sleep(1);
				System.out.println("Bob's updation person's data...");
				preparedStatement.executeUpdate();
				TimeUnit.SECONDS.sleep(1);
				bobConnection.rollback();
				System.out.println("Bob canceled updating!");
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		bobThread.start();
	}
	
	private void aliceDirtyReadPersons() {
		Thread  aliceThread = new Thread(() -> {
			try(Connection aliceConnection = ConnectionManager.getConnection()) {
				aliceConnection.setTransactionIsolation(TRANSACTION_ISOLATION_LEVEL);
				aliceConnection.setAutoCommit(false);
				attemptDirtyRead(aliceConnection);
				TimeUnit.SECONDS.sleep(2);
				attemptDirtyRead(aliceConnection);
				TimeUnit.SECONDS.sleep(2);
				attemptDirtyRead(aliceConnection);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		aliceThread.start();
	}
	
	private void attemptDirtyRead(Connection connection) throws SQLException {
		System.out.println("Alice's reading persons...");
		Statement statement = connection.createStatement();
		ResultSet resultSet =  statement.executeQuery(SQL_SELECT_PERSONS);
		while(resultSet.next()) {
			int personID = resultSet.getInt("id");
			int age = resultSet.getInt("age");
			String name = resultSet.getString("name");
			Person person = new Person(personID, age, name);
			System.out.println(person);
		}
	}
}
