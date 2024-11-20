package aq.gym.db.concurrency;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class Person {

	private int id;
	private int age;
	@NonNull
	private String name;
	
	public Person(int id, int age, String name) {
		this(age, name);
		this.id = id;
	}
	
	public Person(int age, String name) {
		this.age = age;
		this.name = name;
	}
}
