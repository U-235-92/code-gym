package aq.gym.db.orphan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Customer {

	@Id @GeneratedValue
	private int id;
	private String name;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Thing> things = new ArrayList<>();
	
	protected void addThing(Thing thing) {
		things.add(thing);
	}
	
	protected void addThings(Thing... things) {
		this.things.addAll(Arrays.asList(things));
	}
	
	protected void deleteThing(Thing thing) {
		things.remove(thing);
	}
}
