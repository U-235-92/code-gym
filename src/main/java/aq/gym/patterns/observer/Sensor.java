package aq.gym.patterns.observer;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class Sensor implements Observer {

	private String name;
	
	@Override
	public void notify(String line) {
		System.out.println(name + "[" + line + "]");
	}
}
