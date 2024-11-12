package aq.gym.algorithms_and_structures.search_in_width;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		User a = new User("A", false);
		User b = new User("B", false);
		User c = new User("C", false);
		User d = new User("D", false);
		User e = new User("E", false);
		User f = new User("F", false);
		User g = new User("G", false);
		User h = new User("H", false);
		User i = new User("I", false);
		User j = new User("J", true);
		User k = new User("K", false);
		a.addFriends(List.of(b, c, d));
		b.addFriends(List.of(e, f));
		c.addFriends(List.of(g));
		d.addFriends(List.of(h, i));
		e.addFriends(List.of(j, k));
		WidthSearcher widthSearcher = new WidthSearcher();
		User guessPilot = widthSearcher.searchPilotUser(a);
		System.out.println("Pilot: " + guessPilot.getName());
	}

}
