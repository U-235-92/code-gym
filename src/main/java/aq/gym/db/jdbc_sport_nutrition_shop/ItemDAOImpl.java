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
public class ItemDAOImpl implements ItemDAO {

	private static final String SQL_SELECT_ALL_ITEMS = "SELECT * FROM items";
	private static final String SQL_SELECT_ITEM_BY_ID = "SELECT * FROM items WHERE id = ?";
	private static final String SQL_SELECT_ITEM_BY_NAME = "SELECT * FROM items WHERE name = ?";
	private static final String SQL_DELETE_ITEM_BY_ID = "DELETE FROM items WHERE id = ?";
	private static final String SQL_CREATE_ITEM = "INSERT INTO items(name, amount) VALUES (?, ?)";
	private static final String SQL_UPDATE_ITEM_BY_ID = "UPDATE items SET name = ?, amount = ? WHERE id = ?";
	private static final String SQL_UPDATE_ITEM_BY_NAME = "UPDATE items SET amount = ? WHERE name = ?"; //names are unique

	@Override
	public List<Item> readItems() {
		List<Item> items = new ArrayList<Item>();
		try (Connection connection = ConnectionManager.getConnection()) {
			readItems(connection, items);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return items;
	}

	private void readItems(Connection connection, List<Item> items) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_ITEMS);
		ResultSet resultSet = preparedStatement.executeQuery();
		while(resultSet.next()) {
			int id = resultSet.getInt("id");
			int amount = resultSet.getInt("amount");
			String name = resultSet.getString("name");
			Item item = new Item(id, name, amount);
			items.add(item);
		}
	}
	
	@Override
	public Optional<Item> readItemById(int itemID) {
		Item item = null;
		try (Connection connection = ConnectionManager.getConnection()) {
			item = readItemById(connection, itemID);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return Optional.ofNullable(item);
	}

	@Override
	public Optional<Item> readItemByName(String itemName) {
		Item item = null;
		try (Connection connection = ConnectionManager.getConnection()) {
			item = readItemByName(connection, itemName);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return Optional.ofNullable(item);
	}
	
	private Item readItemByName(Connection connection, String itemName) throws SQLException {
		Item item = null;
		PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ITEM_BY_NAME);
		preparedStatement.setString(1, itemName);
		ResultSet resultSet = preparedStatement.executeQuery();
		if(resultSet.next()) {
			int id = resultSet.getInt("id");
			int amount = resultSet.getInt("amount");
			item = new Item(id, itemName, amount);
		}
		return item;
	}
	
	@Override
	public boolean createItem(Item item) {
		boolean isItemCreate = false;
		try (Connection connection = ConnectionManager.getConnection()) {
			if(!isItemExist(connection, item)) {						
				createItem(connection, item);
				isItemCreate = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return isItemCreate;
	}

	private void createItem(Connection connection, Item item) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_ITEM);
		preparedStatement.setString(1, item.getName());
		preparedStatement.setInt(2, item.getAmount());
		preparedStatement.executeUpdate();
	}
	
	@Override
	public int createItems(List<Item> items) {
		int rowsInserted = 0;
		try (Connection connection = ConnectionManager.getConnection()) {
			rowsInserted = createItems(connection, items);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return rowsInserted;
	}
	
	private int createItems(Connection connection, List<Item> items) throws SQLException {
		int rowsInserted = 0;
		connection.setAutoCommit(false);
		PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_ITEM);
		for(Item item : items) {
			if(!isItemExist(connection, item)) {					
				preparedStatement.setString(1, item.getName());
				preparedStatement.setInt(2, item.getAmount());
				preparedStatement.addBatch();
			}
		}
		int[] arrInsertedRows = preparedStatement.executeBatch();
		connection.commit();
		connection.setAutoCommit(true);
		rowsInserted = (arrInsertedRows.length > 0) ? Arrays.stream(arrInsertedRows).reduce(Integer::sum).getAsInt() : 0;
		return rowsInserted;
	}

	@Override
	public boolean deleteItemById(int itemID) {
		boolean isItemDeleted = false;
		try (Connection connection = ConnectionManager.getConnection()) {
			if(isItemExistById(connection, itemID)) {
				deleteItemById(connection, itemID);
				isItemDeleted = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return isItemDeleted;
	}
	
	private void deleteItemById(Connection connection, int itemID) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_ITEM_BY_ID);
		preparedStatement.setInt(1, itemID);
		preparedStatement.executeUpdate();
	}

	@Override
	public boolean updateItemById(int itemID, Item item) {
		boolean isItemUpdated = false;
		try (Connection connection = ConnectionManager.getConnection()) {
			if(isItemExistById(connection, itemID)) {
				updateItemById(connection, itemID, item);
				isItemUpdated = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return isItemUpdated;
	}

	private void updateItemById(Connection connection, int itemID, Item item) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_ITEM_BY_ID);
		preparedStatement.setString(1, item.getName());
		preparedStatement.setInt(2, item.getAmount());
		preparedStatement.executeUpdate();
	}
	
	@Override
	public boolean updateItemByName(String itemName, Item item) {
		boolean isItemUpdated = false;
		try (Connection connection = ConnectionManager.getConnection()) {
			updateItemByName(connection, itemName, item);
			isItemUpdated = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return isItemUpdated;
	}
	
	private void updateItemByName(Connection connection, String itemName, Item item) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_ITEM_BY_NAME);
		preparedStatement.setInt(1, item.getAmount());
		preparedStatement.setString(2, item.getName());
		preparedStatement.executeUpdate();
	}
	
	private boolean isItemExist(Connection connection, Item item) throws SQLException {
		boolean isItemExist = false;
		PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ITEM_BY_NAME);
		preparedStatement.setString(1, item.getName());
		ResultSet resultSet = preparedStatement.executeQuery();
		isItemExist = resultSet.next() == true ? true : false;
		return isItemExist;
	}

	private boolean isItemExistById(Connection connection, int itemID) throws SQLException {
		boolean isItemExist = false;
		Item readItem = readItemById(connection, itemID);
		isItemExist = (readItem == null) ? false : true;
		return isItemExist;
	}
	
	private Item readItemById(Connection connection, int itemID) throws SQLException {
		Item item = null;
		PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ITEM_BY_ID);
		preparedStatement.setInt(1, itemID);
		ResultSet resultSet = preparedStatement.executeQuery();
		if(resultSet.next()) {
			int id = resultSet.getInt("id");
			int amount = resultSet.getInt("amount");
			String name = resultSet.getString("name");
			item = new Item(id, name, amount);
		}
		return item;
	}
}
