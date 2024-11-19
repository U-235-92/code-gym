package aq.gym.db.jdbc_sport_nutrition_shop;

import java.util.List;
import java.util.Optional;

public interface OrderDAO extends AbstractDAO<Order> {

	public List<Order> readOrders();
	public List<Order> readOrdersOfClient(int clientID);
	public Optional<Order> readOrder(int orderID);
	public boolean deleteOrder(int orderID);
	public boolean deleteOrderOfClient(int clientID, int orderID);
	public boolean deleteOrdersOfClient(int clientID);
	public boolean updateOrder(int orderID, Order updateOrderData);
	public boolean createOrderOfClient(int clientID, Order order);
}
