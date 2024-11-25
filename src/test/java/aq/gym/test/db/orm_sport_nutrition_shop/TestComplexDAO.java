package aq.gym.test.db.orm_sport_nutrition_shop;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import aq.gym.db.orm_sport_nutrition_shop.Client;
import aq.gym.db.orm_sport_nutrition_shop.ClientDAO;
import aq.gym.db.orm_sport_nutrition_shop.Item;
import aq.gym.db.orm_sport_nutrition_shop.ItemDAO;
import aq.gym.db.orm_sport_nutrition_shop.Order;
import aq.gym.db.orm_sport_nutrition_shop.OrderDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

class TestComplexDAO {

	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("aq.db.orm_sport_nutrition_shop");
	private static EntityManager entityManager = entityManagerFactory.createEntityManager();
	private static ClientDAO clientDAO = new ClientDAO(entityManager);
	private static OrderDAO orderDAO = new OrderDAO(entityManager);
	private static ItemDAO itemDAO = new ItemDAO(entityManager);

	@BeforeAll
	@org.junit.jupiter.api.Order(1)
	public static void addItems() {
		Item dymatize = new Item("Dymatize Elite 100% Whey Protein 907");
		Item optimum = new Item("Optimum Nutrition 100% Whey Gold Standard 909");
		Item san = new Item("SAN 100% Pure Titanium Whey 909");
		Item scitec = new Item("Scitec Nutrition 100% Whey Protein Proffessional 920");
		itemDAO.createItems(List.of(dymatize, optimum, san, scitec));
	}
	
	@BeforeAll
	@org.junit.jupiter.api.Order(2)
	public static void addClients() {
		clientDAO.createClients(List.of(new Client("Alice"), new Client("Bob"), new Client("Sarah")));
	}

	@Test
	@Disabled
	public void shouldCreateOrderOfClient() {
		int bobID = 2;
		Client bob = clientDAO.readClient(bobID);
		Item dymatize = itemDAO.readItem(1);
		Item optimum = itemDAO.readItem(2);
		Order bobOrder = new Order(LocalDateTime.now());
		bobOrder.setComment("Bob's order");
		bobOrder.addItems(List.of(dymatize, optimum));
		bob.addOrder(bobOrder);
		assertNotNull(orderDAO.readOrders());
	}
	
	@Test
	public void shouldUpdateOrderOfClient() {
		createOrder();
		int bobID = 2;
		Client bob = clientDAO.readClient(bobID);
		Order bobOrder = bob.getOrders().get(0);
		String updateComment = "Bla-bla-bla";
		bobOrder.setComment(updateComment);
		assertEquals(orderDAO.readOrder(1).getComment(), updateComment);
	}
	
	private void createOrder() {
		int bobID = 2;
		Client bob = clientDAO.readClient(bobID);
		Item dymatize = itemDAO.readItem(1);
		Item optimum = itemDAO.readItem(2);
		Order bobOrder = new Order(LocalDateTime.now());
		bobOrder.setComment("Bob's order");
		bobOrder.addItems(List.of(dymatize, optimum));
		bob.addOrder(bobOrder);
	}
 	
	@AfterAll
	public static void closeEntityManager() {
		entityManager.close();
	}
}
