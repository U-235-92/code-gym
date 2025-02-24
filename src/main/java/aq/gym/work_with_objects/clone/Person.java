package aq.gym.work_with_objects.clone;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person implements Cloneable {

	private int id;
	private String name;
	private Address address;
	
	@Override
	protected Person clone() throws CloneNotSupportedException {
		Person clone = (Person) super.clone();
		clone.address = address.clone();
		return clone;
	}
}
