package aq.gym.contests.stack;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.PriorityQueue;

public class NonOverlappingIntervals {

//	https://leetcode.com/problems/non-overlapping-intervals
	public static void main(String[] args) {
//		int[][] intervals = {{1,2},{2,3},{3,4},{1,3}}; // 1
//		int[][] intervals = {{1,2},{1,2},{1,2}}; // 2
//		int[][] intervals = {{1,2},{2,3}}; // 0
//		int[][] intervals = {{2,4},{1,3},{3,4}}; // 1
//		int[][] intervals = {{2,4},{1,5},{8,8}}; // 1
//		int[][] intervals = {{-4,2},{-2,0},{0,2}}; // 1
		
//		int[][] intervals = {{1,3},{3,4},{2,4}}; // 1
//		int[][] intervals = {{1,3},{3,5},{2,4}}; // 1
//		int[][] intervals = {{1,3},{3,5},{2,4},{4,6}}; // 2
//		int[][] intervals = {{1,3},{3,4},{4,5}}; // 0
//		int[][] intervals = {{1,9},{2,3},{3,4},{4,5}}; // 1
//		int[][] intervals = {{1,9},{1,3},{3,5},{5,6},{2,4}}; // 2
//		int[][] intervals = {{1,3},{1,3},{1,3}}; // 2
//		int[][] intervals = {{1,5},{2,8},{8,9}}; // 1
//		int[][] intervals = {{1,3},{1,3},{1,3},{1,9}}; // 3
		int[][] intervals = {{1,3}}; // 3
		System.out.println(new NonOverlappingIntervals().eraseOverlapIntervals(intervals));
	}

    public int eraseOverlapIntervals(int[][] intervals) {
    	if(intervals.length == 1) {
    		return 0;
    	}
    	int[] erasedIntervalsCount = {0};
    	Comparator<int[]> comparator = (interval1, interval2) -> {
    		if(interval1[0] == interval2[0]) {
    			return Integer.compare(interval1[1], interval2[1]);
    		} else {
    			return Integer.compare(interval1[0], interval2[0]);
    		}
    	};
        Deque<int[]> intervalStack = new ArrayDeque<>();
        PriorityQueue<int[]> intervalPQ = new PriorityQueue<>(comparator);
        for(int i = 0; i < intervals.length; i++) {
        	intervalPQ.offer(intervals[i]);
        }
        while(!intervalPQ.isEmpty()) {
        	if(intervalStack.isEmpty()) {
        		int[] interval1 = intervalPQ.poll();
        		int[] interval2 = intervalPQ.poll();
        		eraseOverlapedInterval(interval1, interval2, erasedIntervalsCount, intervalStack, intervalPQ);
        	} else {
        		int[] interval1 = intervalStack.peek();
        		int[] interval2 = intervalPQ.poll();
        		eraseOverlapedInterval(interval1, interval2, erasedIntervalsCount, intervalStack, intervalPQ);
        	}
        }
        return erasedIntervalsCount[0];
    }
    
    private void eraseOverlapedInterval(int[] interval1, int[] interval2, int[] erasedIntervalsCount, 
    		Deque<int[]> intervalStack, PriorityQueue<int[]> intervalPQ) {
		if(interval1[0] == interval2[0] && interval1[1] <= interval2[1]) {
			if(intervalStack.isEmpty()) {				
				intervalStack.push(interval1);
			}
			incrementErasedIntervalCount(erasedIntervalsCount);
		} else if(interval1[0] == interval2[0] && interval1[1] > interval2[1]) {
			intervalStack.push(interval2);
			incrementErasedIntervalCount(erasedIntervalsCount);
		} else if(interval1[0] != interval2[0] && interval1[1] <= interval2[0]) {
			if(intervalStack.isEmpty()) {				
				intervalStack.push(interval1);
			}
			intervalStack.push(interval2);
		} else if(interval1[0] != interval2[0] && interval1[1] <= interval2[1]) {
			if(intervalStack.isEmpty()) {				
				intervalStack.push(interval1);
			}
			incrementErasedIntervalCount(erasedIntervalsCount);
		} else if(interval1[0] != interval2[0] && interval1[1] > interval2[0]) {
			intervalStack.push(interval2);
			incrementErasedIntervalCount(erasedIntervalsCount);
		}
    }
    
    private void incrementErasedIntervalCount(int[] erasedIntervalsCount) {
    	erasedIntervalsCount[0]++;
    }
}
