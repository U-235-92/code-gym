package aq.gym.contests.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class KeysAndRooms {

	public static void main(String[] args) {
		List<List<Integer>> rooms = new ArrayList<>();
//		Test 1
//		rooms.add(List.of(1,3));
//		rooms.add(List.of(3,0,1));
//		rooms.add(List.of(2));
//		rooms.add(List.of(0));
//		Test 2
		rooms.add(List.of(1));
		rooms.add(List.of(2));
		rooms.add(List.of(3));
		rooms.add(List.of(0));
		System.out.println(new KeysAndRooms().canVisitAllRooms(rooms));
	}

	public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Deque<Integer> noVisitedRooms = new ArrayDeque<>();
        Set<Integer> visitedRooms = new HashSet<>();
        int visitCounter = 0;
        for(int r = 0; r < rooms.size(); r++) {
        	if(!visitedRooms.contains(r)) {        		
        		noVisitedRooms.offer(r);
        		while(!noVisitedRooms.isEmpty()) {
        			int room = noVisitedRooms.poll();
        			if(room < rooms.size()) {        				
        				if(!visitedRooms.contains(room)) {
        					noVisitedRooms.addAll(rooms.get(room));
        					visitedRooms.add(room);
        				}
        			}
        		}
        		visitCounter++;
        	}
        }
        return visitCounter == 1;
    }
}
