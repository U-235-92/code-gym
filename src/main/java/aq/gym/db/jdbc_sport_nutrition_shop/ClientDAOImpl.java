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

@NoArgsConstructor(access = AccessLevel.PACKAGE, staticName = "getInstance")
public class ClientDAOImpl implements ClientDAO {

	private static final String SQL_SELECT_CLIENTS = "SELECT * FROM clients";
	private static final String SQL_SELECT_CLIENT = "SELECT * FROM clients WHERE id = ?";
	private static final String SQL_DELETE_CLIENT = "DELETE FROM clients WHERE id = ?";
	private static final String SQL_UPDATE_CLIENT = "UPDATE clients SET clients.name = ? WHERE clients.id = ?";
	private static final String SQL_CREATE_CLIENT = "INSERT INTO clients(name) VALUES(?)";

	@Override
	public int createClients(List<Client> clients) {
		int rowsInserted = 0;
		try (Connection connection = ConnectionManager.getConnection()) {
			rowsInserted = createClients(clients, connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowsInserted;
	}
	
	private int createClients(List<Client> clients, Connection connection) throws SQLException {
		int rowsInserted = 0;
		connection.setAutoCommit(false);
		PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_CLIENT);
		for(Client client : clients) {
			preparedStatement.setString(1, client.getName());
			preparedStatement.addBatch();
		}
		int[] arrInsertedRows = preparedStatement.executeBatch();
		connection.commit();
		connection.setAutoCommit(true);
		rowsInserted = (arrInsertedRows.length > 0) ? Arrays.stream(arrInsertedRows).reduce(0, Integer::sum) : 0;
		return rowsInserted;
	}

	@Override
	public boolean createClient(Client client) {
		boolean isClientCreate = false;
		try (Connection connection = ConnectionManager.getConnection()) {
			createClient(connection, client);
			isClientCreate = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isClientCreate;
	}

	private void createClient(Connection connection, Client client) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_CLIENT);
		preparedStatement.setString(1, client.getName());
		preparedStatement.addBatch();;
		preparedStatement.executeUpdate();
	}
	
	@Override
	public List<Client> readClients(OrderDAO orderDAO) {
		List<Client> clients = new ArrayList<Client>();
		try (Connection connection = ConnectionManager.getConnection()) {
			readClients(connection, clients);
			setOrdersClients(orderDAO, clients);
		} catch(SQLException e) {
			e.printStackTrace();
		} 
		return clients;
	}

	private void readClients(Connection connection, List<Client> clients) throws SQLException {
		List<Client> readClientsDB = new ArrayList<Client>();
		PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_CLIENTS);
		ResultSet resultSet = preparedStatement.executeQuery();
		while(resultSet.next()) {
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			Client client = new Client(id, name);
			readClientsDB.add(client);
		}
		clients.addAll(readClientsDB);
	}
	
	private void setOrdersClients(OrderDAO orderDAO, List<Client> clients) throws SQLException {
		for(Client client : clients) {
			List<Order> orders = orderDAO.readOrdersOfClient(client.getId());
			client.addOrders(orders);
		}
	}

	@Override
	public Optional<Client> readClient(int clientID, OrderDAO orderDAO) {
		Client client = null;
		try (Connection connection = ConnectionManager.getConnection()) {
			if(isClientExist(connection, clientID)) {				
				client = readClient(connection, clientID);
				setOrdersClient(client, orderDAO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return Optional.ofNullable(client);
	}

	private Client readClient(Connection connection, int clientID) throws SQLException {
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

	private void setOrdersClient(Client client, OrderDAO orderDAO) throws SQLException {
		List<Order> orders = orderDAO.readOrdersOfClient(client.getId());
		client.addOrders(orders);
	}

	@Override
	public boolean updateClient(int clientID, Client updateClientData) {
		boolean isClientUpdate = false;
		try (Connection connection = ConnectionManager.getConnection()) {
			if(isClientExist(connection, clientID)) {				
				updateClient(connection, clientID, updateClientData);
				isClientUpdate = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return isClientUpdate;
	}
	
	private void updateClient(Connection connection, int clientID, Client updateClientData) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_CLIENT);
		preparedStatement.setString(1, updateClientData.getName());
		preparedStatement.setInt(2, clientID);
		preparedStatement.executeUpdate();
	}

	@Override
	public boolean deleteClient(int clientID, OrderDAO orderDAO) {
		boolean isClientDelete = false;
		try (Connection connection = ConnectionManager.getConnection()) {
			if(isClientExist(connection, clientID)) {				
				deleteClient(null, clientID, orderDAO);
				isClientDelete = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return isClientDelete;
	}
	
	private void deleteClient(Connection connection, int clientID, OrderDAO orderDAO) throws SQLException {
		orderDAO.deleteOrdersOfClient(clientID);
		PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_CLIENT);
		preparedStatement.setInt(1, clientID);
		preparedStatement.executeUpdate();
	}
	
	private boolean isClientExist(Connection connection, int clientID) throws SQLException {
		boolean isClientExist = false;
		PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_CLIENT);
		preparedStatement.setInt(1, clientID);
		ResultSet resultSet = preparedStatement.executeQuery();
		isClientExist = (resultSet.next() == true) ? true : false;
		return isClientExist;
	}
}
