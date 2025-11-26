package aq.gym.contests.queue;

import java.util.ArrayDeque;
import java.util.Deque;

public class Dota2Senate {

//	https://leetcode.com/problems/dota2-senate/
	public static void main(String[] args) {
		String senate = "DDRRRD";
		System.out.println(new Dota2Senate().predictPartyVictory(senate));
	}

    public String predictPartyVictory(String senate) {
        Deque<Integer> radiants = new ArrayDeque<>();
        Deque<Integer> dires = new ArrayDeque<>();
        int nextSenatorQueueIdx = senate.length();
        for(int i = 0; i < senate.length(); i++) {
        	char senator = senate.charAt(i);
        	switch(senator) {
        	case 'R':
        		radiants.offer(i);
        		break;
        	case 'D':
        		dires.offer(i);
        		break;
        	}
        }
        if(radiants.isEmpty()) {
        	return "Dire";
        } else if(dires.isEmpty()) {
        	return "Radiant";
        } else {        	
        	predictPartyVictory(dires, radiants, nextSenatorQueueIdx);
        	return radiants.isEmpty() ? "Dire" : "Radiant";
        }
    }
    
    private void predictPartyVictory(Deque<Integer> dires, Deque<Integer> radiants, int nextSenatorQueueIdx) {
    	while(!dires.isEmpty() && !radiants.isEmpty()) {
    		int direSenatorIdx = dires.peek();
    		int radiantSenatorIdx = radiants.peek();
    		if(direSenatorIdx < radiantSenatorIdx) {
    			radiants.poll();
    			dires.poll();
    			dires.addLast(nextSenatorQueueIdx);
    		} else {
    			dires.poll();
    			radiants.poll();
    			radiants.addLast(nextSenatorQueueIdx);
    		}
    		nextSenatorQueueIdx++;
    	}
    }
}
