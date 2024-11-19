package aq.gym.db.jdbc_sport_nutrition_shop;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString
public class Item extends Entity {

	@Getter @Setter
	private String name;
	
	public Item(String name) {
		super();
		this.name = name;
	}
	
	public Item(int id, String name) {
		super(id);
		this.name = name;
	}
}
