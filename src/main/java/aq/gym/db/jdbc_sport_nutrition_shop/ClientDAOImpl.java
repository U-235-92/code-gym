package aq.gym.db.jdbc_sport_nutrition_shop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ClientDAOImpl implements ClientDAO {

	private static final String SQL_SELECT_CLIENTS = "SELECT * FROM clients";
	private static final String SQL_SELECT_CLIENT = "SELECT * FROM clients WHERE id = ?";
	private static final String SQL_DELETE_CLIENT = "DELETE FROM clients WHERE id = ?";
	private static final String SQL_UPDATE_CLIENT = "UPDATE clients SET clients.name = ? WHERE clients.id = ?";
	private static final String SQL_CREATE_CLIENT = "INSERT INTO clients(name) VALUES(?)";

	private Connection connection;
	private Transaction transaction;

	public ClientDAOImpl(Connection connection, Transaction transaction) {
		this.connection = connection;
		this.transaction = transaction;
	}

//	public int addClients(List<Client> clients) {
//		int rowsInserted = 0;
//		try {
//			transaction.begin();
//			PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_CLIENT);
//			for(Client client : clients) {
//				preparedStatement.setString(1, client.getName());
//				preparedStatement.addBatch();
//			}
//			int[] inserted = preparedStatement.executeBatch();
//			rowsInserted = Arrays.stream(inserted).reduce(0, Integer::sum);
//			transaction.commit();
//		} catch (SQLException e) {
//			transaction.rollback();
//			e.printStackTrace();
//		} finally {
//			transaction.end();
//		}
//		return rowsInserted;
//	}
//
//	public List<Client> readAllClients(OrderDAOImpl orderDAO, ItemDAOImpl itemDao) {
//		List<Client> clients = readAllClients0();
//		for(Client client : clients) {			
//			List<Order> orders = orderDAO.readAllOrdersByClientID(client.getId());
//			client.addOrders(orders);
//			for(Order order : orders) {				
//				List<Item> items = itemDao.readItemsByOrderID(order.getId());
//				order.addItems(items);				
//			}
//		}
//		return clients;
//	}
//	
//	private List<Client> readAllClients0() {
//		List<Client> clients = new ArrayList<Client>();
//		try {
//			transaction.begin();
//			PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_CLIENTS);
//			ResultSet resultSet = preparedStatement.executeQuery();
//			while(resultSet.next()) {
//				int id = resultSet.getInt("id");
//				String name = resultSet.getString("name");
//				Client client = new Client(id, name);
//				clients.add(client);
//			}
//			transaction.commit();
//		} catch (SQLException e) {
//			transaction.rollback();
//			e.printStackTrace();
//		} finally {
//			transaction.end();
//		}
//		return clients;
//	}
//
//	@Override
//	public Optional<Client> readEntity(int id) {
//		Client client = null;
//		try {
//			transaction.begin();
//			PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_CLIENT_BY_ID);
//			preparedStatement.setInt(1, id);
//			ResultSet resultSet = preparedStatement.executeQuery();
//			if(resultSet.next()) {
//				String name = resultSet.getString("name");
//				client = new Client(id, name);
//			}
//			transaction.commit();
//		} catch (SQLException e) {
//			transaction.rollback();
//			e.printStackTrace();
//		} finally {
//			transaction.end();
//		}
//		return Optional.ofNullable(client);
//	}
//
//	@Override
//	public boolean deleteEntity(int id) {
//		boolean operationResult = false;
//		try {
//			transaction.begin();
//			PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_CLIENT_BY_ID);
//			preparedStatement.setInt(1, id);
//			preparedStatement.executeUpdate();
//			operationResult = true;
//			transaction.commit();
//		} catch (SQLException e) {
//			transaction.rollback();
//			e.printStackTrace();
//		} finally {
//			transaction.end();
//		}
//		return operationResult;
//	}
//
//	@Override
//	public void createClient(Client client, OrderDAO orderDAO) {
//		createClient0(client);
//		createClientOrdersIfOrdersExist(client, orderDAO);
//	}
//	
//	private void createClient0(Client client) {
//		try {
//			transaction.begin();
//			PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_CLIENT);
//			preparedStatement.setString(1, client.getName());
//			preparedStatement.executeUpdate();
//			transaction.commit();
//		} catch (SQLException e) {
//			transaction.rollback();
//			e.printStackTrace();
//		} finally {
//			transaction.end();
//		}
//	}
//	
//	private void createClientOrdersIfOrdersExist(Client client, OrderDAO orderDAO) {
//		if(client.getOrders().size() > 0) {
//			orderDAO.createOrderByClient(client);
//		}
//	}
//
//	@Override
//	public int updateEntity(int id, Client entity) {
//		int rowsUpdated = 0;
//		try {
//			transaction.begin();
//			PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_CLIENT);
//			preparedStatement.setString(1, entity.getName());
//			preparedStatement.setInt(2, id);
//			rowsUpdated = preparedStatement.executeUpdate();
//			transaction.commit();
//		} catch (SQLException e) {
//			transaction.rollback();
//			e.printStackTrace();
//		} finally {
//			transaction.end();
//		}
//		return rowsUpdated;
//	}

	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	@Override
	public int createClients(List<Client> clients) {
		int rowsInserted = 0;
		try {
			transaction.begin();
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_CLIENT);
			for(Client client : clients) {
				preparedStatement.setString(1, client.getName());
				preparedStatement.addBatch();
			}
			int[] inserted = preparedStatement.executeBatch();
			rowsInserted = Arrays.stream(inserted).reduce(0, Integer::sum);
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
	public int createClient(Client client) {
		int rowsInserted = 0;
		try {
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
