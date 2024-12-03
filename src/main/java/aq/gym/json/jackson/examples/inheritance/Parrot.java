package aq.gym.json.jackson.examples.inheritance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Parrot extends Animal {

	private String name;
	private String[] colors;
}
