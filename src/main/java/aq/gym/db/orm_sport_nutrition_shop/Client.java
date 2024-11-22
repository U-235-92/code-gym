package aq.gym.db.orm_sport_nutrition_shop;

import java.util.ArrayList;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString
public class Client extends Entity {

	@Getter
	private String name;
	private List<Order> orders;

	public Client(String name) {
		super();
		this.name = name;
		orders = new ArrayList<Order>();
	}
	
	public Client(int id, String name) {
		this(name);
		setId(id);
	}

	public List<Order> getOrders() {
		return List.copyOf(orders);
	}

	public void addOrders(List<Order> orders) {
		this.orders.addAll(orders);
	}
	
	public void addOrder(Order order) {
		orders.add(order);
	}
	
	
}
