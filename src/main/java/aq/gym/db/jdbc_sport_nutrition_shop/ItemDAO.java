package aq.gym.db.jdbc_sport_nutrition_shop;

import java.util.List;
import java.util.Optional;

public interface ItemDAO extends AbstractDAO<Item> {

	public List<Item> readItems();
	public Optional<Item> readItemById(int itemID);
	public Optional<Item> readItemByName(String itemName);
	public boolean createItem(Item item);
	public int createItems(List<Item> items);
	public boolean deleteItemById(int itemID);
	public boolean updateItemById(int itemID, Item item);
	public boolean updateItemByName(String itemName, Item item);
}
