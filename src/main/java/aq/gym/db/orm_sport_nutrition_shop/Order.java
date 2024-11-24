package aq.gym.db.orm_sport_nutrition_shop;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
@Entity
public class Order extends aq.gym.db.orm_sport_nutrition_shop.Entity {

	@Getter @Setter
	private LocalDateTime date;
	@Getter @Setter
	private String comment;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
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
