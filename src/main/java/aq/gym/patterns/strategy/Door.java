package aq.gym.patterns.strategy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Door {

	private OpenBehaviour openBehaviour;
	
	public void open() {
		openBehaviour.open();
	}
}
