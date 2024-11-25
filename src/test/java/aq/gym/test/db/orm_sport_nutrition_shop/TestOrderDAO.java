package aq.gym.test.db.orm_sport_nutrition_shop;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import aq.gym.db.orm_sport_nutrition_shop.Order;
import aq.gym.db.orm_sport_nutrition_shop.OrderDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

class TestOrderDAO {

	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("aq.db.orm_sport_nutrition_shop");
	private static EntityManager entityManager = entityManagerFactory.createEntityManager();
	private static OrderDAO orderDAO = new OrderDAO(entityManager);
	
	@Test
	@Disabled
	public void shouldDeleteOrder() {
		
	}
	
	@Test
	@Disabled
	public void shouldCreateOrder() {
		Order order = new Order(LocalDateTime.now());
		orderDAO.createOrder(order);
		order = orderDAO.readOrder(1);
		assertNotNull(order);
	}
	
	@Test
	@Disabled
	public void shouldUpdateOrder() {
		Order order = new Order(LocalDateTime.now());
		order.setComment("Hello!");
		orderDAO.createOrder(order);
		order = orderDAO.readOrder(1);
		String updateComment = "Bye!";
		orderDAO.updateOrderComment(1, updateComment);
		order = orderDAO.readOrder(1);
		assertEquals("Bye!", order.getComment());
	}
	
	@Test
	@Disabled
	public void shouldReadOrder() {
		Order order = new Order(LocalDateTime.now());
		orderDAO.createOrder(order);
		order = orderDAO.readOrder(1);
		assertEquals(1, order.getId());
	}

	@Test
	public void shouldReadOrders() {
		Order first = new Order(LocalDateTime.now().minusDays(1));
		Order second = new Order(LocalDateTime.now());
		orderDAO.createOrders(List.of(first, second));
		List<Order> orders = orderDAO.readOrders();
		assertNotNull(orders);
	}
}
