package aq.gym.json.jackson.examples.enum_handle;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MainEnum {

	public static void main(String[] args) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String enumJsonString = mapper.writeValueAsString(Distance.METER);
		System.out.println(enumJsonString);
		Distance distance = mapper.readValue(enumJsonString, Distance.class);
		System.out.println(distance);
	}

}
