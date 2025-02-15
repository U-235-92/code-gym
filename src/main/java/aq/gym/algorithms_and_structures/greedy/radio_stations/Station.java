package aq.gym.algorithms_and_structures.greedy.radio_stations;

import java.util.Set;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PROTECTED, staticName = "of")
public class Station {

	private String name;
	private Set<State> coveredStates;
}
