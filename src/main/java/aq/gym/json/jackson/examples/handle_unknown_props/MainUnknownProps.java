package aq.gym.json.jackson.examples.handle_unknown_props;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MainUnknownProps {

	public static void main(String[] args) throws StreamReadException, DatabindException, IOException {
		File source = new File("src/main/java/aq/gym/json/jackson/examples/handle_unknown_props/lamborghini.json");
		ObjectMapper mapper = new ObjectMapper();
//		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES); //if Car hasn't @JsonAnySetter annotation
		Car lamborghini = mapper.readValue(source, Car.class);
		System.out.println(lamborghini);
	}

}
