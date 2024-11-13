package aq.gym.thread.port;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Ship {

	private String name;

	@Override
	public String toString() {
		return "Ship [name=" + name + "]";
	}

}
