package aq.gym.json.jackson.examples.nested;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Taxi {

	 private String number;
	 private String driverName;
	 private String ownerName;
	 
	 @JsonProperty(value = "driver")
	 private void unpackNestedDriver(Map<String, Object> values) {
		 driverName = (String) values.get("name");
	 }
	 
	 @JsonProperty(value = "owner")
	 private void unpackNestedOwner(Map<String, Object> values) {
		 ownerName = (String) values.get("name");
	 }
}
