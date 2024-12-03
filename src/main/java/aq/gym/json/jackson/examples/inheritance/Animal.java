package aq.gym.json.jackson.examples.inheritance;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(use = Id.NAME, include = As.PROPERTY, property = "type")
@JsonSubTypes(value = {
		@JsonSubTypes.Type(value = Cat.class, name = "Cat"),
		@JsonSubTypes.Type(value = Parrot.class, name = "Parrot"),
		@JsonSubTypes.Type(value = Shark.class, name = "Shark")})
public abstract class Animal {

	private int age;
	private int weight;
}
