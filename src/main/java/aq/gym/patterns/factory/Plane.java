package aq.gym.patterns.factory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public abstract class Plane {

	private Model model;
	private String manufacturer;
	private int passangers;
}
