package aq.gym.contests.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;

public class RelativeRanks {

//	https://leetcode.com/problems/relative-ranks/
	public static void main(String[] args) {
		int[] score = {10,3,8,9,4};
		System.out.println(Arrays.toString(new RelativeRanks().findRelativeRanks(score)));
	}

    public String[] findRelativeRanks(int[] score) {
        PriorityQueue<Athlete> athletes = new PriorityQueue<>(Comparator.reverseOrder());
        for(int i = 0; i < score.length; i++) {
        	Athlete athlete = new Athlete(i + 1, score[i]);
        	athletes.offer(athlete);
        }
        return getRanks(athletes);
    }
    
    private String[] getRanks(PriorityQueue<Athlete> athletes) {
    	int place = 1;
    	String[] ranks = new String[athletes.size()];
        while(!athletes.isEmpty()) {
        	Athlete athlete = athletes.poll();
        	switch(place++) {
        	case 1:
        		ranks[athlete.place - 1] = "Gold Medal";
        		break;
        	case 2:
        		ranks[athlete.place - 1] = "Silver Medal";
        		break;
        	case 3:
        		ranks[athlete.place - 1] = "Bronze Medal";
        		break;
        	default:
        		ranks[athlete.place - 1] = (place - 1) + "";
        		break;
        	}
        }
        return ranks;
    }
    
    private static class Athlete implements Comparable<Athlete> {
    	
    	private int place;
    	private int score;
    	
    	Athlete(int place, int score) {
			this.place = place;
			this.score = score;
		}
    	
    	@Override
    	public boolean equals(Object obj) {
    		if(obj == null || obj.getClass() != this.getClass()) return false;
    		if(obj == this) return true;
    		Athlete a = (Athlete) obj;
    		return a.score == this.score && a.place == this.place;
    	}
    	
    	@Override
    	public int hashCode() {
    		return Objects.hash(place, score);
    	}
    	
    	@Override
    	public String toString() {
    		return String.format("[ %d, %d ]", score, place);
    	}
    	
		@Override
		public int compareTo(Athlete athlete) {
			return Integer.compare(this.score, athlete.score);
		}
    }
}
