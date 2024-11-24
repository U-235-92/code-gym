package aq.gym.db.orm_sport_nutrition_shop;

import java.util.ArrayList;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
@Entity
@Table(name = "clients")
public class Client extends aq.gym.db.orm_sport_nutrition_shop.Entity {

	@Getter
	private String name;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "client_fk")
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
