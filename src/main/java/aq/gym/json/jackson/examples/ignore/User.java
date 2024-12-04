package aq.gym.json.jackson.examples.ignore;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
public class User {

	@JsonProperty(value = "person_age")
	private int age;
	@JsonProperty(value = "person_login")
	private String login;
	@JsonIgnore
	private String password;
}
