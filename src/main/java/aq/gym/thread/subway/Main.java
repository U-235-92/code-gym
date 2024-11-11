package aq.gym.thread.subway;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		Train orangeLine = new Train("Orange line");
		Train blueLightning = new Train("Blue lightning");
		Train easternExpress = new Train("Eastern express");
		List<Train> trains = new ArrayList<Train>();
		trains.add(easternExpress); trains.add(blueLightning); trains.add(orangeLine);
		Route left = new Route("Left");
		Route right = new Route("Right");
		Subway subway = new Subway("North hill subway", List.of(left, right));
		for(Train train : trains) {
			new Thread(() -> subway.use(train)).start();
		}
	}
}
