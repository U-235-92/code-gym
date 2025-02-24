package aq.gym.work_with_objects.clone;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address implements Cloneable {

	private String country;
	private String city;
	
	@Override
	protected Address clone() throws CloneNotSupportedException {
		return (Address) super.clone();
	}
}
