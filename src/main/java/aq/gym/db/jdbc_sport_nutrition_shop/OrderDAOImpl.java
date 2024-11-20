package aq.gym.db.jdbc_sport_nutrition_shop;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PACKAGE, staticName = "getInstance")
public class OrderDAOImpl implements OrderDAO {

	private static final String SQL_SELECT_ORDERS_OF_CLIENT_BY_CLIENT_ID = "SELECT orders.id, orders.date, orders.comment FROM orders INNER JOIN orders_clients ON orders_clients.order_id = orders.id WHERE orders_clients.client_id = ?";
	private static final String SQL_SELECT_ORDERS = "SELECT id, date, comment FROM orders";
	private static final String SQL_SELECT_ORDER_BY_ORDER_ID = "SELECT date, comment FROM orders WHERE id = ?";
	private static final String SQL_SELECT_ORDER_ITEMS_BY_ORDER_ID = "SELECT items.id, items.name FROM items INNER JOIN orders_items ON items.id = orders_items.item_id WHERE orders_items.order_id = ?";
	private static final String SQL_DELETE_ORDER_FROM_ORDERS = "DELETE FROM orders WHERE id = ?";
	private static final String SQL_DELETE_ORDER_FROM_ORDERS_CLIENTS_BY_CLIENTID_AND_ORDERID = "DELETE FROM orders_clients WHERE order_id = ? AND client_id = ?";
	private static final String SQL_DELETE_ORDER_FROM_ORDERS_CLIENTS_BY_ORDERID = "DELETE FROM orders_clients WHERE order_id = ?";
	private static final String SQL_DELETE_ITEMS_FROM_ORDER_ITEMS = "DELETE FROM orders_items WHERE order_id = ?";
	private static final String SQL_CREATE_ORDER_IN_ORDERS = "INSERT INTO orders(date, comment) VALUES (?, ?)";
	private static final String SQL_CREATE_ORDER_ITEMS_IN_ORDERS_ITEMS = "INSERT INTO orders_items(order_id, item_id) VALUES (?, ?)";
	private static final String SQL_CREATE_ORDER_ITEMS_IN_ORDERS_CLIENTS = "INSERT INTO orders_clients(order_id, client_id) VALUES (?, ?)";
	private static final String SQL_UPDATE_ORDER_IN_ORDERS = "UPDATE orders SET date = ?, comment = ? WHERE id = ?";
	private static final String SQL_UPDATE_ORDER_IN_ORDERS_ITEMS = "UPDATE orders_items SET item_id = ? WHERE order_id = ?";

	@Override
	public List<Order> readOrders() {
		List<Order> orders = new ArrayList<Order>();
		try (Connection connection = ConnectionManager.getPooledConnection()) {
			readOrders(connection, orders);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return orders;
	}

	private void readOrders(Connection connection, List<Order> orders) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ORDERS);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			Instant dateInstant = Instant.ofEpochMilli(resultSet.getDate("date").getTime());
			LocalDateTime orderDate = LocalDateTime.ofInstant(dateInstant, ZoneId.systemDefault());
			String comment = resultSet.getString("comment");
			Order order = new Order(id, orderDate, comment);
			List<Item> items = readOrderItems(connection, order);
			order.addItems(items);
			orders.add(order);
		}
	}
	
	@Override
	public List<Order> readOrdersOfClient(int clientID) {
		List<Order> orders = new ArrayList<Order>();
		try (Connection connection = ConnectionManager.getPooledConnection()) {
			readOrdersOfClient(connection, clientID, orders);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return orders;
	}
	
	private void readOrdersOfClient(Connection connection, int clientID, List<Order> orders) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ORDERS_OF_CLIENT_BY_CLIENT_ID);
		preparedStatement.setInt(1, clientID);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			int id = resultSet.getInt("orders.id");
			Instant dateInstant = Instant.ofEpochMilli(resultSet.getDate("orders.date").getTime());
			LocalDateTime orderDate = LocalDateTime.ofInstant(dateInstant, ZoneId.systemDefault());
			String comment = resultSet.getString("orders.comment");
			Order order = new Order(id, orderDate, comment);
			List<Item> items = readOrderItems(connection, order);
			order.addItems(items);
			orders.add(order);
		}
	}

	@Override
	public Optional<Order> readOrder(int orderID) {
		Order order = null;
		try (Connection connection = ConnectionManager.getPooledConnection()) {
			order = readOrder(connection, orderID);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return Optional.ofNullable(order);
	}
	
	private Order readOrder(Connection connection, int orderID) throws SQLException {
		Order order = null;
		PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ORDER_BY_ORDER_ID);
		preparedStatement.setInt(1, orderID);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			Instant dateInstant = Instant.ofEpochMilli(resultSet.getDate("date").getTime());
			LocalDateTime orderDate = LocalDateTime.ofInstant(dateInstant, ZoneId.systemDefault());
			String comment = resultSet.getString("comment");
			order = new Order(orderID, orderDate, comment);
			List<Item> items = readOrderItems(connection, order);
			order.addItems(items);
		}
		return order;
	}

	private List<Item> readOrderItems(Connection connection, Order order) throws SQLException {
		List<Item> items = new ArrayList<Item>();
		PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ORDER_ITEMS_BY_ORDER_ID);
		preparedStatement.setInt(1, order.getId());
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			int id = resultSet.getInt("items.id");
			String name = resultSet.getString("items.name");
			Item item = new Item(id, name);
			items.add(item);
		}
		return items;
	}

	@Override
	public boolean deleteOrderOfClient(int clientID, int orderID) {
		boolean isOrderDeleted = false;
		try (Connection connection = ConnectionManager.getPooledConnection()) {
			if(isOrderExist(connection, orderID)) {				
				deleteItemsFromOrdersItems(connection, orderID);
				deleteOrderFromOrdersClients(connection, clientID, orderID);
				deleteOrderFromOrders(connection, orderID);
				isOrderDeleted = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return isOrderDeleted;
	}
	
	@Override
	public boolean deleteOrdersOfClient(int clientID) {
		boolean isOrderDeleted = false;
		try (Connection connection = ConnectionManager.getPooledConnection()) {
			List<Order> orders = readOrdersOfClient(clientID);
			for(Order order : orders) {
				int orderID = order.getId();
				if (isOrderExist(connection, orderID)) {
					deleteItemsFromOrdersItems(connection, orderID);
					deleteOrderFromOrdersClients(connection, clientID, orderID);
					deleteOrderFromOrders(connection, orderID);
					isOrderDeleted = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isOrderDeleted;
	}
	
	@Override
	public boolean deleteOrder(int orderID) {
		boolean isOrderDeleted = false;
		try (Connection connection = ConnectionManager.getPooledConnection()) {
			if(isOrderExist(connection, orderID)) {				
				deleteItemsFromOrdersItems(connection, orderID);
				deleteOrderFromOrdersClients(connection, orderID);
				deleteOrderFromOrders(connection, orderID);
				isOrderDeleted = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return isOrderDeleted;
	}
	
	private boolean isOrderExist(Connection connection, int orderID) throws SQLException {
		boolean isOrderExist = false;
		PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ORDER_BY_ORDER_ID);
		preparedStatement.setInt(1, orderID);
		ResultSet resultSet = preparedStatement.executeQuery();
		isOrderExist = (resultSet.next()) ? true : false;
		return isOrderExist;
	}
	
	private void deleteItemsFromOrdersItems(Connection connection, int orderID) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_ITEMS_FROM_ORDER_ITEMS);
		preparedStatement.setInt(1, orderID);
		preparedStatement.executeUpdate();
	}

	private void deleteOrderFromOrdersClients(Connection connection, int orderID) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_ORDER_FROM_ORDERS_CLIENTS_BY_ORDERID);
		preparedStatement.setInt(1, orderID);
		preparedStatement.executeUpdate();
	}
	
	private void deleteOrderFromOrdersClients(Connection connection, int clientID, int orderID) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_ORDER_FROM_ORDERS_CLIENTS_BY_CLIENTID_AND_ORDERID);
		preparedStatement.setInt(1, orderID);
		preparedStatement.setInt(2, clientID);
		preparedStatement.executeUpdate();
	}
	
	private void deleteOrderFromOrders(Connection connection, int orderID) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_ORDER_FROM_ORDERS);
		preparedStatement.setInt(1, orderID);
		preparedStatement.executeUpdate();
	}
	
	@Override
	public boolean updateOrder(int orderID, Order updateOrderData) {
		boolean isOrderUpdate = false;
		try (Connection connection = ConnectionManager.getPooledConnection()) {
			if(isOrderExist(connection, orderID)) {				
				updateOrderInOrders(connection, orderID, updateOrderData);
				updateOrderInOrdersItems(connection, orderID, updateOrderData);
				isOrderUpdate = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return isOrderUpdate;
	}
	
	private int updateOrderInOrders(Connection connection, int orderID, Order updateOrderData) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_ORDER_IN_ORDERS);
		Date orderDate = new Date(updateOrderData.getDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
		preparedStatement.setDate(1, orderDate);
		preparedStatement.setString(2, updateOrderData.getComment());
		preparedStatement.setInt(3, orderID);
		return preparedStatement.executeUpdate();
	}

	private int updateOrderInOrdersItems(Connection connection, int orderID, Order updateOrderData) throws SQLException {
		int rowsUpdated = 0;
		if(updateOrderData.getItems().size() > 0) {			
			connection.setAutoCommit(false);
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_ORDER_IN_ORDERS_ITEMS);
			for(Item item : updateOrderData.getItems()) {
				preparedStatement.setInt(1, item.getId());
				preparedStatement.setInt(2, orderID);
				preparedStatement.addBatch();
			}
			int[] arrUpdatedRows = preparedStatement.executeBatch();
			connection.commit();
			connection.setAutoCommit(true);
			rowsUpdated = (arrUpdatedRows.length > 0) ? Arrays.stream(arrUpdatedRows).reduce(Integer::sum).getAsInt() : 0;
		}
		return rowsUpdated;
	}
	
	@Override
	public boolean createOrderOfClient(int clientID, Order order) {
		boolean isOrderCreated = false;
		try (Connection connection = ConnectionManager.getPooledConnection()) {
			int generatedOrderID = createOrderInOrders(connection, order);
			createOrderInOrdersClients(connection, clientID, generatedOrderID);
			createOrderInOrdersItems(connection, clientID, generatedOrderID, order);
			isOrderCreated = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return isOrderCreated;
	}
	
	private int createOrderInOrders(Connection connection, Order order) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_ORDER_IN_ORDERS, Statement.RETURN_GENERATED_KEYS);
		Date orderDate = new Date(order.getDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
		preparedStatement.setDate(1, orderDate);
		preparedStatement.setString(2, order.getComment());
		preparedStatement.executeUpdate();
		ResultSet resultSet = preparedStatement.getGeneratedKeys();
		int key = 0;
		if(resultSet.next()) 
			key = resultSet.getInt(1);
		return key;
	}

	private void createOrderInOrdersClients(Connection connection, int clientID, int orderID) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_ORDER_ITEMS_IN_ORDERS_CLIENTS);
		preparedStatement.setInt(1, orderID);
		preparedStatement.setInt(2, clientID);
		preparedStatement.executeUpdate();
	}
	
	private int createOrderInOrdersItems(Connection connection, int clientID, int orderID, Order orderData) throws SQLException {
		connection.setAutoCommit(false);
		PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_ORDER_ITEMS_IN_ORDERS_ITEMS);
		for(Item item : orderData.getItems()) {
			preparedStatement.setInt(1, orderID);
			preparedStatement.setInt(2, item.getId());
			preparedStatement.addBatch();
		}
		int[] arrCreatedRows = preparedStatement.executeBatch();
		connection.commit();
		connection.setAutoCommit(true);
		int createdRows = (arrCreatedRows.length > 0) ? Arrays.stream(arrCreatedRows).reduce(Integer::sum).getAsInt() : 0;
		return createdRows;
	}
}
