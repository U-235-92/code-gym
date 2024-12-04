package aq.gym.json.jackson.examples.handle_unknown_props;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {

	private String mark;
	private String model;
	private int power;
	private Map<String, Object> unknownFields = new HashMap<String, Object>();
	
	@JsonAnySetter  
    public void allSetter(String fieldName, String fieldValue) {  
		unknownFields.put(fieldName, fieldValue);  
    }  
}
