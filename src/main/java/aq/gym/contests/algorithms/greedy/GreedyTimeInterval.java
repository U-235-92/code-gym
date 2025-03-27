package aq.gym.contests.algorithms.greedy;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

public class GreedyTimeInterval {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = Integer.valueOf(scanner.nextLine());
		Set<String> intervals = new ConcurrentSkipListSet<String>();
		for(int i = 0; i < n; i++) {
			String interval = scanner.nextLine();
			intervals.add(interval);
		}
		scanner.close();
		int maxNoCrossIntervalCount = 0;
		while(intervals.size() > 0) {
			int minRight = Integer.MAX_VALUE;
			String best = null;
//			Define best interval
			for(String interval : intervals) {
				int[] nums = Arrays.stream(interval.split("\\s")).mapToInt(Integer::valueOf).toArray();
				int currentRight = nums[1];
				if(currentRight < minRight) {
					minRight = currentRight;
					best = interval;
				}
			}
//			Remove all the intervals which cross best interval
			int bestRight = Arrays.stream(best.split("\\s")).mapToInt(Integer::valueOf).toArray()[1];
			for(String interval : intervals) {
				if(interval != best) {
					int currentLeft = Arrays.stream(interval.split("\\s")).mapToInt(Integer::valueOf).toArray()[0];
					if(currentLeft <= bestRight) {
						intervals.remove(interval);
					}
				}
			}
			intervals.remove(best);
			maxNoCrossIntervalCount++;
		}
		System.out.println(maxNoCrossIntervalCount);
	}
}
