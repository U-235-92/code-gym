package aq.gym.json.jackson.examples.inheritance;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MainJacksonInheritance {

	public static void main(String[] args) throws JsonProcessingException {
		Cat mew = new Cat();
		mew.setAge(5);
		mew.setColor("White");
		mew.setName("Mew");
		mew.setWeight(5);
		Parrot koko = new Parrot();
		koko.setAge(2);
		koko.setColors(new String[]{"Red", "Blue", "Yellow", "Green"});
		koko.setName("Koko");
		koko.setWeight(1);
		Shark bob = new Shark();
		bob.setAge(10);
		bob.setSharkType(SharkType.GREAT_WHITE_SHARK);
		bob.setWeight(90);
		List<Animal> animals = List.of(mew, koko, bob);
		Zoo zoo = new Zoo();
		zoo.addAnimals(animals);
		ObjectMapper mapper = new ObjectMapper();
		String jsonAnimalsString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(zoo);
		System.out.println(jsonAnimalsString);
	}

}
