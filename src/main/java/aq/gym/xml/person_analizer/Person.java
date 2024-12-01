package aq.gym.xml.person_analizer;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Person {

	private String name;
	private int age;
	private Country country;
}
