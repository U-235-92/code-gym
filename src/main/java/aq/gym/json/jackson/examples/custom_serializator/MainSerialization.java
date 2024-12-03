package aq.gym.json.jackson.examples.custom_serializator;

import java.time.LocalDate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class MainSerialization {

	public static void main(String[] args) throws JsonProcessingException {
		Party newYearsParty = new Party("New Year's Eve", LocalDate.of(1970, 1, 1));
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		String jsonResult = mapper.writeValueAsString(newYearsParty);
		System.out.println(jsonResult);
		newYearsParty = mapper.readValue(jsonResult, Party.class);
		System.out.println(newYearsParty);
	}

}
