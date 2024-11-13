package aq.gym.algorithms_and_structures.search_in_width;

import java.util.ArrayList;
import java.util.List;

public class User {

	private List<User> friends = new ArrayList<User>();
	private boolean isPilot = false;
	private String name;

	public User(String name, boolean isPilot) {
		this.name = name;
		this.isPilot = isPilot;
	}

	public void addFriends(List<User> friends) {
		this.friends.addAll(friends);
	}
	
	public List<User> getFriends() {
		return List.copyOf(friends);
	}

	public boolean isPilot() {
		return isPilot;
	}

	public String getName() {
		return name;
	}
}
