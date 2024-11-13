package aq.gym.thread.subway;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Train {

	private String name;
	
	public void goThroughSubway(Subway subway) {
		subway.use(this);
	}
}
