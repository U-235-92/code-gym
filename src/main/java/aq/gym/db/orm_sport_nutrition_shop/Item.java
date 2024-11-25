package aq.gym.db.orm_sport_nutrition_shop;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
@Entity
@Table(name = "items")
@NamedQueries(value = {
		@NamedQuery(name = Item.DELETE_ITEM, query = "DELETE FROM Item i WHERE i.id = :id"),
		@NamedQuery(name = Item.READ_ITEMS, query = "SELECT i FROM Item i")
})
public class Item extends aq.gym.db.orm_sport_nutrition_shop.Entity {
	
	public static final String DELETE_ITEM = "delete_item";
	public static final String READ_ITEMS = "read_items";
	
	@Getter @Setter
	@Column(name = "name", unique = true)
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
