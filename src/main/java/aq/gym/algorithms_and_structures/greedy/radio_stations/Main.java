package aq.gym.algorithms_and_structures.greedy.radio_stations;

import java.util.HashSet;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		Set<State> states = new HashSet<>();
		states.add(State.of("mt"));
		states.add(State.of("wa"));
		states.add(State.of("or"));
		states.add(State.of("id"));
		states.add(State.of("nv"));
		states.add(State.of("ut"));
		states.add(State.of("ca"));
		states.add(State.of("az"));
		
		Set<State> koneStates = new HashSet<>();
		koneStates.add(State.of("id"));
		koneStates.add(State.of("nv"));
		koneStates.add(State.of("ut"));
		Station kone = Station.of("kone", koneStates);
		
		Set<State> ktwoStates = new HashSet<>();
		ktwoStates.add(State.of("wa"));
		ktwoStates.add(State.of("mt"));
		ktwoStates.add(State.of("id"));
		Station ktwo = Station.of("ktwo", ktwoStates);
		
		Set<State> kthreeStates = new HashSet<>();
		kthreeStates.add(State.of("or"));
		kthreeStates.add(State.of("nv"));
		kthreeStates.add(State.of("ca"));
		Station kthree = Station.of("kthree", kthreeStates);
		
		Set<State> kfourStates = new HashSet<>();
		kfourStates.add(State.of("ut"));
		kfourStates.add(State.of("nv"));
		Station kfour = Station.of("kfour", kfourStates);
		
		Set<State> kfiveStates = new HashSet<>();
		kfiveStates.add(State.of("ca"));
		kfiveStates.add(State.of("az"));
		Station kfive = Station.of("kfive", kfiveStates);
		
		Set<Station> stations = new HashSet<>(Set.of(kone, ktwo, kthree, kfour, kfive));
		
		StationCoverer stationCoverer = new StationCoverer(states, stations);
		Set<Station> bestStations = stationCoverer.cover();
		System.out.println("The best stetions are:");
		bestStations.forEach(station -> System.out.println("- " + station.getName()));
	}
}
