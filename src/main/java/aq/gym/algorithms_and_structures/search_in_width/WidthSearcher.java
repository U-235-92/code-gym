package aq.gym.algorithms_and_structures.search_in_width;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

public class WidthSearcher {

	public User searchPilotUser(User user) {
		List<User> visitedUsers = new ArrayList<User>();
		Queue<User> searchQueue = new LinkedList<User>();
		searchQueue.addAll(user.getFriends());
		while(searchQueue.size() > 0) {
			User guessUser = searchQueue.poll();
			if(guessUser != null) {				
				if(!visitedUsers.contains(guessUser)) {				
					if(guessUser.isPilot()) 
						return guessUser;
					searchQueue.addAll(guessUser.getFriends());
					visitedUsers.add(guessUser); 
				}
			}
		}
		throw new NoSuchElementException("There isn't any pilot!");
	}
}
