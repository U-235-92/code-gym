package aq.gym.db.jdbc_sport_nutrition_shop;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
public class Item extends Entity {

	@Getter @Setter
	private String name;
	@Getter @Setter
	private int amount;
	
	public Item(String name, int amount) {
		super();
		this.name = name;
		this.amount = amount;
	}
	
	public Item(int id, String name, int amount) {
		super(id);
		this.name = name;
		this.amount = amount;
	}
}
