package aq.gym.json.jackson.examples.inheritance;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Zoo {

	@JsonProperty("animals")
	private List<Animal> animals = new ArrayList<Animal>();
	
	public void addAnimals(List<Animal> animals) {
		this.animals.addAll(animals);
	}
}
