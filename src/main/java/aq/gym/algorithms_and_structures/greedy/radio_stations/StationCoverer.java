package aq.gym.algorithms_and_structures.greedy.radio_stations;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StationCoverer {

	private Set<State> statesNeeded;
	private Set<Station> stations;
	
	public Set<Station> cover() {
		Set<Station> bestStations = new HashSet<>();
		while(statesNeeded.size() > 0) {
			Station bestStation = null;
			Set<State> currentMaxNumberCoveredStationsPerStation = new HashSet<State>();
			for(Station currentStation : stations) {
				Set<State> currentStationCover = currentStation.getCoveredStates();
				currentStationCover.retainAll(statesNeeded);
				if(currentStationCover.size() > currentMaxNumberCoveredStationsPerStation.size()) {
					currentMaxNumberCoveredStationsPerStation = currentStationCover;
					bestStation = currentStation;
				}
			}
			bestStations.add(bestStation);
			statesNeeded.removeAll(bestStation.getCoveredStates());
		}
		return bestStations;
	}
}
