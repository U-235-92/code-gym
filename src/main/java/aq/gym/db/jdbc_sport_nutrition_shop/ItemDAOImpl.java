package aq.gym.db.jdbc_sport_nutrition_shop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ItemDAOImpl implements ItemDAO {

	private static final String SQL_SELECT_ALL_ITEMS = "SELECT * FROM items";
	private static final String SQL_SELECT_ITEM_BY_ID = "SELECT * FROM items WHERE id = ?";
	private static final String SQL_SELECT_ITEM_BY_NAME = "SELECT * FROM items WHERE name = ?";
	private static final String SQL_DELETE_ITEM_BY_ID = "DELETE FROM items WHERE id = ?";
	private static final String SQL_CREATE_ITEM = "INSERT INTO items(name, amount) VALUES (?, ?)";
	private static final String SQL_UPDATE_ITEM_BY_ID = "UPDATE items SET name = ?, amount = ? WHERE id = ?";
	private static final String SQL_UPDATE_ITEM_BY_NAME = "UPDATE items SET amount = ? WHERE name = ?"; //names are unique
	
	private Connection connection;
	private Transaction transaction;
	
	public ItemDAOImpl(Connection connection, Transaction transaction) {
		this.connection = connection;
		this.transaction = transaction;
	}

	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	@Override
	public List<Item> readAllItems() {
		List<Item> items = new ArrayList<Item>();
		try {
			transaction.begin();
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_ITEMS);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				int amount = resultSet.getInt("amount");
				String name = resultSet.getString("name");
				Item item = new Item(id, name, amount);
				items.add(item);
			}
			transaction.commit();
		} catch (SQLException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			transaction.end();
		}
		return items;
	}

	@Override
	public Optional<Item> readItemById(int itemID) {
		Item item = null;
		try {
			transaction.begin();
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ITEM_BY_ID);
			preparedStatement.setInt(1, itemID);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				int id = resultSet.getInt("id");
				int amount = resultSet.getInt("amount");
				String name = resultSet.getString("name");
				item = new Item(id, name, amount);
			}
			transaction.commit();
		} catch (SQLException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			transaction.end();
		}
		return Optional.ofNullable(item);
	}

	@Override
	public Optional<Item> readItemByName(String itemName) {
		Item item = null;
		try {
			transaction.begin();
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ITEM_BY_NAME);
			preparedStatement.setString(1, itemName);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				int id = resultSet.getInt("id");
				int amount = resultSet.getInt("amount");
				item = new Item(id, itemName, amount);
			}
			transaction.commit();
		} catch (SQLException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			transaction.end();
		}
		return Optional.ofNullable(item);
	}
	
	@Override
	public int createItem(Item item) {
		int rowsInserted = 0;
		if(item == null) {
			throw new NullPointerException();
		} else {			
			try {
				if(!isItemExist(item)) {						
					transaction.begin();
					PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_ITEM);
					preparedStatement.setString(1, item.getName());
					preparedStatement.setInt(2, item.getAmount());
					rowsInserted = preparedStatement.executeUpdate();
					transaction.commit();
				}
			} catch (SQLException e) {
				transaction.rollback();
				e.printStackTrace();
			} finally {
				transaction.end();
			}
		}
		return rowsInserted;
	}

	@Override
	public int createItems(List<Item> items) {
		int rowsInserted = 0;
		if(items == null) {
			throw new NullPointerException();
		} else {			
			try {
				transaction.begin();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_ITEM);
				for(Item item : items) {
					if(!isItemExist(item)) {					
						preparedStatement.setString(1, item.getName());
						preparedStatement.setInt(2, item.getAmount());
						preparedStatement.addBatch();
					}
				}
				int[] arrInsertedRows = preparedStatement.executeBatch();
				rowsInserted = Arrays.stream(arrInsertedRows).reduce(Integer::sum).getAsInt();
				transaction.commit();
			} catch (SQLException e) {
				transaction.rollback();
				e.printStackTrace();
			} finally {
				transaction.end();
			}
		}
		return rowsInserted;
	}

	@Override
	public boolean deleteItemById(int itemID) {
		boolean isItemDeleted = false;
		try {
			transaction.begin();
			if(isItemExistById(itemID)) {
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_ITEM_BY_ID);
				preparedStatement.setInt(1, itemID);
				preparedStatement.executeUpdate();
				isItemDeleted = true;
			}
			transaction.commit();
		} catch (SQLException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			transaction.end();
		}
		return isItemDeleted;
	}

	@Override
	public boolean updateItemById(int itemID, Item item) {
		boolean isItemUpdated = false;
		if(item == null) {
			throw new NullPointerException();
		} else {			
			try {
				transaction.begin();
				if(isItemExistById(itemID)) {
					PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_ITEM_BY_ID);
					preparedStatement.setString(1, item.getName());
					preparedStatement.setInt(2, item.getAmount());
					preparedStatement.executeUpdate();
					isItemUpdated = true;
				}
				transaction.commit();
			} catch (SQLException e) {
				transaction.rollback();
				e.printStackTrace();
			} finally {
				transaction.end();
			}
		}
		return isItemUpdated;
	}

	@Override
	public boolean updateItemByName(String itemName, Item item) {
		boolean isItemUpdated = false;
		if(item == null) {
			throw new NullPointerException();
		} else {			
			try {
				transaction.begin();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_ITEM_BY_NAME);
				preparedStatement.setInt(1, item.getAmount());
				preparedStatement.setString(2, item.getName());
				preparedStatement.executeUpdate();
				isItemUpdated = true;
				transaction.commit();
			} catch (SQLException e) {
				transaction.rollback();
				e.printStackTrace();
			} finally {
				transaction.end();
			}
		}
		return isItemUpdated;
	}
	
	private boolean isItemExist(Item item) throws SQLException {
		boolean isItemExist = false;
		PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ITEM_BY_NAME);
		preparedStatement.setString(1, item.getName());
		ResultSet resultSet = preparedStatement.executeQuery();
		isItemExist = resultSet.next() == true ? true : false;
		return isItemExist;
	}

	private boolean isItemExistById(int itemID) throws SQLException {
		boolean isItemExist = false;
		Optional<Item> selected = readItemById(itemID);
		isItemExist = (selected.isEmpty() == true) ? false : true;
		return isItemExist;
	}
}
