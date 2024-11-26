package aq.gym.test.db.orm_sport_nutrition_shop;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import aq.gym.db.orm_sport_nutrition_shop.Item;
import aq.gym.db.orm_sport_nutrition_shop.ItemDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

class TestItemDAO {

	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("aq.db.orm_sport_nutrition_shop");
	private static EntityManager entityManager = entityManagerFactory.createEntityManager();
	private static ItemDAO itemDAO = new ItemDAO(entityManager);
	
	@BeforeAll
	public static void fill() {
		Item dymatize = new Item("Dymatize Elite 100% Whey Protein 907");
		Item optimum = new Item("Optimum Nutrition 100% Whey Gold Standard 909");
		Item san = new Item("SAN 100% Pure Titanium Whey 909");
		Item scitec = new Item("Scitec Nutrition 100% Whey Protein Proffessional 920");
		itemDAO.createItems(List.of(dymatize, optimum, san, scitec));
	}
	
	@Test
	@Disabled
	public void shouldCreateItems() {
		Item dymatize = new Item("Dymatize Elite 100% Whey Protein 907");
		Item optimum = new Item("Optimum Nutrition 100% Whey Gold Standard 909");
		Item san = new Item("SAN 100% Pure Titanium Whey 909");
		Item scitec = new Item("Scitec Nutrition 100% Whey Protein Proffessional 920");
		itemDAO.createItems(List.of(dymatize, optimum, san, scitec));
		List<Item> items = itemDAO.readItems();
		assertNotNull(items);
	}
	
	@Test
	@Disabled
	public void shouldAddItem() {
		String ultimateName = "Ultimate Nutrition ProStar Whey Protein 907";
		Item ultimate = new Item(ultimateName);
		itemDAO.createItem(ultimate);
		ultimate = itemDAO.readItem(ultimateName);
		assertEquals(ultimateName, ultimate.getName());
	}
	
	@Test
	@Disabled
	public void shoultUpdateItemName() {
		int id = 1;
		String updateName = "Dymatize Elite 100% Whey Protein 454";
		itemDAO.updateItemName(1, updateName);
		Item updated = itemDAO.readItem(id);
		assertEquals(updated.getName(), updateName);
	}
	
	@Test
	@Disabled
	public void shouldDeleteItem() {
		int id = 1;
		itemDAO.deleteItem(id);
		Item deleted = itemDAO.readItem(id);
		assertNull(deleted);
	}
	
	@Test
	@Disabled
	public void testRollback() {
		EntityTransaction entityTransaction =  entityManager.getTransaction();
		entityTransaction.begin();
		String ultimateName = "Ultimate Nutrition ProStar Whey Protein 907";
		Item ultimate = new Item(ultimateName);
		entityManager.persist(ultimate);
		entityTransaction.rollback();
		Item copy = itemDAO.readItem(ultimateName);
		assertNotNull(copy);
	}
}
