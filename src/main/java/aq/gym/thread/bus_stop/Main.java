package aq.gym.thread.bus_stop;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

	public static void main(String[] args) {
		List<Bus> buses = new ArrayList<Bus>(List.of(
				new Bus(10, "A"),
				new Bus(10, "B"), 
				new Bus(20, "C"),
				new Bus(30, "D"),
				new Bus(30, "E"),
				new Bus(30, "F")
		));
		BusStop busStop1 = new BusStop("Stop_1", 2);
		BusStop busStop2 = new BusStop("Stop_2", 2);
		Route route = new Route(busStop1, busStop2);
		ExecutorService executor = Executors.newFixedThreadPool(buses.size());
		buses.forEach(bus -> executor.execute(() -> route.getBusStops().forEach(busStop -> bus.tryStop(busStop))));
		executor.shutdown();
	}

}
