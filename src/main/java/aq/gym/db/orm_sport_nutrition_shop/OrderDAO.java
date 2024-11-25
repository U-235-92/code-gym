package aq.gym.db.orm_sport_nutrition_shop;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderDAO {

	@NonNull
	private EntityManager entityManager;
	
	public void createOrder(Order order) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(order);
		transaction.commit();
	}
	
	public void createOrders(List<Order> orders) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		orders.forEach(entityManager::persist);
		transaction.commit();
	}
	
	@SuppressWarnings("unchecked")
	public List<Order> readOrders() {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Query query = entityManager.createQuery("SELECT o FROM Order o", Order.class);
		List<Order> orders = query.getResultList();
		transaction.commit();
		return orders;
	}
	
	public Order readOrder(int orderID) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Order order = entityManager.find(Order.class, orderID);
		transaction.commit();
		return order;
	}
	
	public void updateOrderComment(int orderID, String comment) {
		EntityTransaction transaction = entityManager.getTransaction();
		Order order = readOrder(orderID);
		transaction.begin();
		order.setComment(comment);
		transaction.commit();
	}
	
	public void updateOrderDate(int orderID, LocalDateTime date) {
		EntityTransaction transaction = entityManager.getTransaction();
		Order order = readOrder(orderID);
		transaction.begin();
		order.setDate(date);
		transaction.commit();
	}
	
	public void addOrderItem(int orderID, Item item) {
		EntityTransaction transaction = entityManager.getTransaction();
		Order order = readOrder(orderID);
		transaction.begin();
		order.addItem(item);
		transaction.commit();
	}
	
	public void addOrderItems(int orderID, List<Item> items) {
		EntityTransaction transaction = entityManager.getTransaction();
		Order order = readOrder(orderID);
		transaction.begin();
		order.addItems(items);
		transaction.commit();
	}
	
	public void deleteOrderItem(int orderID, int itemID) {
		EntityTransaction transaction = entityManager.getTransaction();
		Order order = readOrder(orderID);
		transaction.begin();
		order.getItems().removeIf(item -> item.getId() == itemID);
		transaction.commit();
	}
	
	public void deleteOrder(int orderID) {
		EntityTransaction transaction = entityManager.getTransaction();
		Order delete = readOrder(orderID);
		transaction.begin();
		entityManager.remove(delete);
		transaction.commit();
	}
}
