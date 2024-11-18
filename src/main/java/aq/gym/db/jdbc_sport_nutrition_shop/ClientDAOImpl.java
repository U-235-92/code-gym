package aq.gym.db.jdbc_sport_nutrition_shop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE, staticName = "getInstance")
public class ClientDAOImpl implements ClientDAO {

	private static final String SQL_SELECT_CLIENTS = "SELECT * FROM clients";
	private static final String SQL_SELECT_CLIENT = "SELECT * FROM clients WHERE id = ?";
	private static final String SQL_DELETE_CLIENT = "DELETE FROM clients WHERE id = ?";
	private static final String SQL_UPDATE_CLIENT = "UPDATE clients SET clients.name = ? WHERE clients.id = ?";
	private static final String SQL_CREATE_CLIENT = "INSERT INTO clients(name) VALUES(?)";

	@Override
	public int createClients(List<Client> clients) {
		int rowsInserted = 0;
		Transaction transaction = null;
		try (Connection connection = ConnectionManager.getConnection()) {
			transaction = new Transaction(connection);
			transaction.begin();
			rowsInserted = createClients(clients, connection);
			transaction.commit();
		} catch (SQLException e) {
			if(transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			if(transaction != null)
				transaction.end();
		}
		return rowsInserted;
	}
	
	private int createClients(List<Client> clients, Connection connection) throws SQLException {
		int rowsInserted = 0;
		PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_CLIENT);
		for(Client client : clients) {
			preparedStatement.setString(1, client.getName());
			preparedStatement.addBatch();
		}
		int[] inserted = preparedStatement.executeBatch();
		rowsInserted = Arrays.stream(inserted).reduce(0, Integer::sum);
		return rowsInserted;
	}

	@Override
	public int createClient(Client client) {
		int rowsInserted = 0;
		try (Connection connection = ConnectionManager.getConnection()) {
			Transaction transaction = new Transaction(connection);
			transaction.begin();
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_CLIENT);
			preparedStatement.setString(1, client.getName());
			preparedStatement.addBatch();;
			rowsInserted = preparedStatement.executeUpdate();
			transaction.commit();
		} catch (SQLException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			transaction.end();
		}
		return rowsInserted;
	}

	@Override
	public List<Client> readClients(OrderDAO orderDAO) {
		List<Client> clients = new ArrayList<Client>();
		try {
			transaction.begin();
			clients.addAll(readClients());
			readOrdersOfClients(clients, orderDAO);
			transaction.commit();
		} catch(SQLException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			transaction.end();
		}
		return clients;
	}

	private List<Client> readClients() throws SQLException {
		List<Client> clients = new ArrayList<Client>();
		try {
			transaction.begin();
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_CLIENTS);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				Client client = new Client(id, name);
				clients.add(client);
			}
			transaction.commit();
		} catch (SQLException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			transaction.end();
		}
		return clients;
	}
	
	private void readOrdersOfClients(List<Client> clients, OrderDAO orderDAO) throws SQLException {
		for(Client client : clients) {
			List<Order> orders = orderDAO.readOrdersOfClient(client.getId());
			client.addOrders(orders);
		}
	}

	@Override
	public Optional<Client> readClient(int clientID, OrderDAO orderDAO) {
		Client client = null;
		try {
			transaction.begin();
			if(isExistClient(clientID)) {				
				client = readClient(clientID);
				readClientOrders(client, orderDAO);
				transaction.commit();
			}
		} catch (SQLException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			transaction.end();
		}
		return Optional.ofNullable(client);
	}

	private Client readClient(int clientID) throws SQLException {
		Client client = null;
		PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_CLIENT);
		preparedStatement.setInt(1, clientID);
		ResultSet resultSet = preparedStatement.executeQuery();
		if(resultSet.next()) {
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			client = new Client(id, name);
		}
		return client;
	}

	private void readClientOrders(Client client, OrderDAO orderDAO) throws SQLException {
		List<Order> orders = orderDAO.readOrdersOfClient(client.getId());
		client.addOrders(orders);
	}

	@Override
	public boolean updateClient(int clientID, Client updateClientData) {
		boolean isClientUpdate = false;
		try {
			transaction.begin();
			if(isExistClient(clientID)) {				
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_CLIENT);
				preparedStatement.setString(1, updateClientData.getName());
				preparedStatement.setInt(2, clientID);
				preparedStatement.executeUpdate();
				isClientUpdate = true;
				transaction.commit();
			}
		} catch (SQLException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			transaction.end();
		}
		return isClientUpdate;
	}

	@Override
	public boolean deleteClient(int clientID, OrderDAO orderDAO) {
		boolean isClientDelete = false;
		try {
			transaction.begin();
			if(isExistClient(clientID)) {				
				orderDAO.deleteOrdersOfClient(clientID);
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_CLIENT);
				preparedStatement.setInt(1, clientID);
				preparedStatement.executeUpdate();
				isClientDelete = true;
				transaction.commit();
			}
		} catch (SQLException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			transaction.end();
		}
		return isClientDelete;
	}
	
	private boolean isExistClient(int clientID) throws SQLException {
		boolean isClientExist = false;
		PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_CLIENT);
		preparedStatement.setInt(1, clientID);
		ResultSet resultSet = preparedStatement.executeQuery();
		isClientExist = (resultSet.next() == true) ? true : false;
		return isClientExist;
	}
}
