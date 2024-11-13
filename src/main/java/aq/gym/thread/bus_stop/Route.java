package aq.gym.thread.bus_stop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Route {

	private List<BusStop> busStops = new ArrayList<BusStop>();
	
	public Route(BusStop...busStops) {
		this.busStops.addAll(Arrays.asList(busStops));
	}
	
	public List<BusStop> getBusStops() {
		return List.copyOf(busStops);
	}
}
