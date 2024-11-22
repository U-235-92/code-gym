package aq.gym.db.orm_sport_nutrition_shop;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString
public class Order extends Entity {

	@Getter
	@Setter
	private LocalDateTime date;
	@Getter
	@Setter
	private String comment;
	private List<Item> items;

	public Order(int id, LocalDateTime date, String comment) {
		this(date);
		setId(id);
		this.comment = comment;
	}
	
	public Order(LocalDateTime orderDate) {
		this.date = orderDate;
		items = new ArrayList<Item>();
	}
	
	public List<Item> getItems() {
		return items;
	}
	
	public void addItems(List<Item> items) {
		this.items.addAll(items);
	}
	
	public void addItem(Item item) {
		items.add(item);
	}
}
