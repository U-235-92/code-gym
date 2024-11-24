package aq.gym.db.orm_sport_nutrition_shop;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@ToString
@Entity
@Table(name = "items")
public class Item extends aq.gym.db.orm_sport_nutrition_shop.Entity {

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
