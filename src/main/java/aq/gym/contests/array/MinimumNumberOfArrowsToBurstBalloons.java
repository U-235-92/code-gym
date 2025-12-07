package aq.gym.contests.array;

import java.util.Arrays;
import java.util.Comparator;

public class MinimumNumberOfArrowsToBurstBalloons {

//	https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/
	public static void main(String[] args) {
//		int[][] points = {{10,16},{2,8},{1,6},{7,12}}; // 2
//		int[][] points = {{1,2},{3,4},{5,6},{7,8}}; // 4
		int[][] points = {{1,2},{2,3},{3,4},{4,5}}; // 2
		System.out.println(new MinimumNumberOfArrowsToBurstBalloons().findMinArrowShots(points));
	}

    public int findMinArrowShots(int[][] points) {
    	if(points.length == 1) {
    		return 1;
    	}
        Comparator<int[]> comparator = (p1, p2) -> {
        	if(p1[0] == p2[0]) {
        		return Integer.compare(p1[1], p2[1]);
        	}
        	return Integer.compare(p1[0], p2[0]);
        };
		Arrays.sort(points, comparator.reversed());
		int[] highestBallon = points[0];
		int arrowShotsCount = 1;
		for(int i = 1; i < points.length; i++) {
			int[] currentBallon = points[i];
			if(currentBallon[0] < highestBallon[0] && currentBallon[1] < highestBallon[0]) {
				highestBallon = currentBallon;
				arrowShotsCount++;
			} 
		}
		return arrowShotsCount;
    }
}
