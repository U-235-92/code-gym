package aq.gym.json.jackson.examples.ignore;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class MainIgnore {

	public static void main(String[] args) throws JsonProcessingException  {
		User alice = new User(22, "Alice", "123");
		User bob = new User(30, "Bob", "456");
		ObjectMapper mapper = new ObjectMapper();
		List<User> usersToWrite = List.of(alice, bob);
		mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		String jsonPersonsString = mapper.writeValueAsString(usersToWrite);
		System.out.println(jsonPersonsString);
		List<User> usersToRead = mapper.readValue(jsonPersonsString, new TypeReference<List<User>>() {});
		usersToRead.forEach(System.out::println);
	}
}
