package aq.gym.db.jdbc_sport_nutrition_shop;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OrderDAOImpl implements OrderDAO {

	private static final String SQL_SELECT_ORDERS_OF_CLIENT_BY_CLIENT_ID = "SELECT (orders.id, orders.date, orders.comment) FROM orders INNER JOIN clients ON clients.id = orders.client_id WHERE orders.client_id = ?";
	private static final String SQL_SELECT_ORDERS = "SELECT (id, date, comment) FROM orders";
	private static final String SQL_SELECT_ORDER_BY_ORDER_ID = "SELECT (date, comment) FROM orders WHERE id = ?";
	private static final String SQL_SELECT_ORDER_ITEMS_BY_ORDER_ID = "SELECT (items.id, items.name, items.amount) FROM items INNER JOIN orders_items ON items.id = orders_items.item_id WHERE orders_items.order_id = ?";
	private static final String SQL_DELETE_ORDER_FROM_ORDERS = "DELETE FROM orders WHERE id = ?";
	private static final String SQL_DELETE_ORDER_FROM_ORDERS_CLIENTS_BY_CLIENTID_AND_ORDERID = "DELETE FROM orders_clients WHERE order_id = ? AND client_id = ?";
	private static final String SQL_DELETE_ORDER_FROM_ORDERS_CLIENTS_BY_ORDERID = "DELETE FROM orders_clients WHERE order_id = ?";
	private static final String SQL_DELETE_ITEMS_FROM_ORDER_ITEMS = "DELETE FROM orders_items WHERE order_id = ?";
	private static final String SQL_CREATE_ORDER_IN_ORDERS = "INSERT INTO orders(date, comment) VALUES (?, ?)";
	private static final String SQL_CREATE_ORDER_ITEMS_IN_ORDERS_ITEMS = "INSERT INTO orders_items(order_id, item_id) VALUES (?, ?)";
	private static final String SQL_UPDATE_ORDER_IN_ORDERS = "UPDATE orders SET date = ?, comment = ? WHERE id = ?";
	private static final String SQL_UPDATE_ORDER_IN_ORDERS_ITEMS = "UPDATE orders_items SET item_id = ? WHERE order_id = ?";

	private Connection connection;
	private Transaction transaction;

	public OrderDAOImpl(Connection connection, Transaction transaction) {
		this.connection = connection;
		this.transaction = transaction;
	}

	@Override
	public List<Order> readOrders() {
		List<Order> orders = new ArrayList<Order>();
		try {
			transaction.begin();
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ORDERS);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				LocalDateTime date = LocalDateTime.from(resultSet.getDate("date").toInstant());
				String comment = resultSet.getString("comment");
				Order order = new Order(id, date, comment);
				List<Item> items = readOrderItems(order);
				order.addItems(items);
				orders.add(order);
			}
			transaction.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			transaction.end();
		}
		return orders;
	}

	@Override
	public List<Order> readOrdersOfClient(int clientID) {
		List<Order> orders = new ArrayList<Order>();
		try {
			transaction.begin();
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ORDERS_OF_CLIENT_BY_CLIENT_ID);
			preparedStatement.setInt(1, clientID);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("orders.id");
				LocalDateTime date = LocalDateTime.from(resultSet.getDate("orders.date").toInstant());
				String comment = resultSet.getString("orders.comment");
				Order order = new Order(id, date, comment);
				List<Item> items = readOrderItems(order);
				order.addItems(items);
				orders.add(order);
			}
			transaction.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			transaction.end();
		}
		return orders;
	}

	@Override
	public Optional<Order> readOrder(int orderID) {
		Order order = null;
		try {
			transaction.begin();
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ORDER_BY_ORDER_ID);
			preparedStatement.setInt(1, orderID);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				LocalDateTime date = LocalDateTime.from(resultSet.getDate("date").toInstant());
				String comment = resultSet.getString("comment");
				order = new Order(orderID, date, comment);
				List<Item> items = readOrderItems(order);
				order.addItems(items);
			}
			transaction.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			transaction.end();
		}
		return Optional.ofNullable(order);
	}

	private List<Item> readOrderItems(Order order) throws SQLException {
		List<Item> items = new ArrayList<Item>();
		if(order == null) {			
			throw new NullPointerException();
		} else {			
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ORDER_ITEMS_BY_ORDER_ID);
			preparedStatement.setInt(1, order.getId());
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("items.id");
				int amount = resultSet.getInt("items.amount");
				String name = resultSet.getString("items.name");
				Item item = new Item(id, name, amount);
				items.add(item);
			}
		}
		return items;
	}

	@Override
	public boolean deleteOrderOfClient(int clientID, int orderID) {
		boolean isOrderDeleted = false;
		try {
			transaction.begin();
			if(isOrderExist(orderID)) {				
				deleteItemsFromOrdersItems(orderID);
				deleteOrderFromOrdersClients(clientID, orderID);
				deleteOrderFromOrders(orderID);
				isOrderDeleted = true;
			}
			transaction.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			transaction.end();
		}
		return isOrderDeleted;
	}
	
	@Override
	public boolean deleteOrdersOfClient(int clientID) {
		boolean isOrderDeleted = false;
		try {
			transaction.begin();
			List<Order> orders = readOrdersOfClient(clientID);
			for(Order order : orders) {
				int orderID = order.getId();
				if (isOrderExist(orderID)) {
					deleteItemsFromOrdersItems(orderID);
					deleteOrderFromOrdersClients(clientID, orderID);
					deleteOrderFromOrders(orderID);
					isOrderDeleted = true;
				}
			}
			transaction.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			transaction.end();
		}
		return isOrderDeleted;
	}
	
	@Override
	public boolean deleteOrder(int orderID) {
		boolean isOrderDeleted = false;
		try {
			transaction.begin();
			if(isOrderExist(orderID)) {				
				deleteItemsFromOrdersItems(orderID);
				deleteOrderFromOrdersClients(orderID);
				deleteOrderFromOrders(orderID);
				isOrderDeleted = true;
			}
			transaction.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			transaction.end();
		}
		return isOrderDeleted;
	}
	
	private boolean isOrderExist(int orderID) throws SQLException {
		boolean isOrderExist = false;
		return isOrderExist;
	}
	
	private void deleteItemsFromOrdersItems(int orderID) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_ITEMS_FROM_ORDER_ITEMS);
		preparedStatement.setInt(1, orderID);
		preparedStatement.executeUpdate();
	}

	private void deleteOrderFromOrdersClients(int orderID) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_ORDER_FROM_ORDERS_CLIENTS_BY_ORDERID);
		preparedStatement.setInt(1, orderID);
		preparedStatement.executeUpdate();
	}
	
	private void deleteOrderFromOrdersClients(int clientID, int orderID) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_ORDER_FROM_ORDERS_CLIENTS_BY_CLIENTID_AND_ORDERID);
		preparedStatement.setInt(1, orderID);
		preparedStatement.setInt(2, clientID);
		preparedStatement.executeUpdate();
	}
	
	private void deleteOrderFromOrders(int orderID) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_ORDER_FROM_ORDERS);
		preparedStatement.setInt(1, orderID);
		preparedStatement.executeUpdate();
	}
	
	@Override
	public int[] updateOrder(int orderID, Order updateOrderData) {
		int[] arrUpdatedRows = new int[2];
		if(updateOrderData == null) {			
			throw new NullPointerException();
		} else {			
			try {			
				transaction.begin();
				if(isOrderExist(orderID)) {				
					arrUpdatedRows[0] = updateOrderInOrders(orderID, updateOrderData);
					arrUpdatedRows[1] = updateOrderInOrdersItems(orderID, updateOrderData);
				}
				transaction.commit();
			} catch (SQLException e) {
				e.printStackTrace();
				transaction.rollback();
			} finally {			
				transaction.end();
			}
		}
		return arrUpdatedRows;
	}
	
	private int updateOrderInOrders(int orderID, Order updateOrderData) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_ORDER_IN_ORDERS);
		preparedStatement.setDate(1, new Date(Instant.from(updateOrderData.getDate()).toEpochMilli()));
		preparedStatement.setString(2, updateOrderData.getComment());
		preparedStatement.setInt(3, orderID);
		return preparedStatement.executeUpdate();
	}

	private int updateOrderInOrdersItems(int orderID, Order updateOrderData) throws SQLException {
		int rowsUpdated = 0;
		if(updateOrderData.getItems().size() > 0) {			
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_ORDER_IN_ORDERS_ITEMS);
			for(Item item : updateOrderData.getItems()) {
				preparedStatement.setInt(1, item.getId());
				preparedStatement.setInt(2, orderID);
				preparedStatement.addBatch();
			}
			int[] arrUpdatedRows = preparedStatement.executeBatch();
			rowsUpdated = Arrays.stream(arrUpdatedRows).reduce(Integer::sum).getAsInt();
		}
		return rowsUpdated;
	}
	
	@Override
	public int createOrderOfClient(int clientID, Order order) {
		int rowsCreated = 0;
		if(order == null) {			
			throw new NullPointerException();
		} else {			
			try {			
				transaction.begin();
				createOrderInOrders(order);
				createOrderInOrdersItems(clientID, order);
				transaction.commit();
			} catch (SQLException e) {
				e.printStackTrace();
				transaction.rollback();
			} finally {			
				transaction.end();
			}
		}
		return rowsCreated;
	}
	
	private int createOrderInOrders(Order order) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_ORDER_IN_ORDERS);
		preparedStatement.setDate(1, new Date(Instant.from(order.getDate()).toEpochMilli()));
		preparedStatement.setString(2, order.getComment());
		return preparedStatement.executeUpdate();
	}

	private int createOrderInOrdersItems(int clientID, Order order) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_ORDER_ITEMS_IN_ORDERS_ITEMS);
		for(Item item : order.getItems()) {
			preparedStatement.setInt(1, order.getId());
			preparedStatement.setInt(2, item.getId());
			preparedStatement.addBatch();
		}
		int[] arrCreatedRows = preparedStatement.executeBatch();
		int createdRows = Arrays.stream(arrCreatedRows).reduce(Integer::sum).getAsInt();
		return createdRows;
	}

	@Override
	public void setConnection(Connection connection) {
		if(connection == null)
			throw new NullPointerException();
		else
			this.connection = connection;
	}

	@Override
	public void setTransaction(Transaction transaction) {
		if(transaction == null)
			throw new NullPointerException();
		else
			this.transaction = transaction;
	}
}
