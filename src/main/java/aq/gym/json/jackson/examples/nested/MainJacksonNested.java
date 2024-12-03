package aq.gym.json.jackson.examples.nested;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MainJacksonNested {

	public static void main(String[] args) {
		File source = new File("src/main/java/aq/gym/json/jackson/examples/nested/taxi.json");
		ObjectMapper mapper = new ObjectMapper();		
		printTaxiUsingAnnotationsOfClass(mapper, source);
		printTaxiUsingTreeViewOfJson(mapper, source);
	}

	private static void printTaxiUsingAnnotationsOfClass(ObjectMapper mapper, File source) {
		mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES,	false);
		try {
			Taxi taxi_1 = mapper.readerFor(Taxi.class).readValue(source);
			System.out.println(taxi_1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void printTaxiUsingTreeViewOfJson(ObjectMapper mapper, File source) {
		try {
			Taxi taxi_2 = new Taxi();
			JsonNode taxiTree = mapper.readTree(source);
			String number = taxiTree.get("number").asText();
			String ownerName = taxiTree.get("owner").get("name").asText();
			String driverName = taxiTree.get("driver").get("name").asText();
			taxi_2.setNumber(number);
			taxi_2.setDriverName(driverName);
			taxi_2.setOwnerName(ownerName);
			System.out.println(taxi_2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
