package aq.gym.contests.algorithms.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class GreedyCollectorSignature {

	public static void main(String[] args) {
//		List<String> intervals = new LinkedList<>(List.of("1 3", "2 5", "4 7", "3 6"));
//		collectSignatures(intervals);
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			List<String> intervals = new LinkedList<>();
			int n = Integer.valueOf(br.readLine());
			for(int i = 0; i < n; i++) {
				intervals.add(br.readLine());
			}
			collectSignatures(intervals);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	private static void collectSignatures(List<String> intervals) {
		Set<Integer> points = new HashSet<>();
		List<String> trash = new LinkedList<>();
		while(intervals.size() > 0) {
			String bestInterval = "";
			int bestRight = Integer.MAX_VALUE;
			for(String interval : intervals) {
				int currentRight = Integer.valueOf(interval.split("\\s")[1]);
				if(currentRight < bestRight) {
					bestRight = currentRight;
					bestInterval = interval;
				}
			}
			for(String interval : intervals) {
				int[] border = Arrays.stream(interval.split("\\s")).mapToInt(Integer::valueOf).toArray();
				int left = border[0];
				if(bestRight >= left) {
					points.add(bestRight);
					trash.add(interval);
				}
			}
			trash.add(bestInterval);
			intervals.removeAll(trash);
			trash.removeAll(trash);
		}
		StringBuilder pointsLine = new StringBuilder();
		for(int point : points) {
			pointsLine.append(point);
			pointsLine.append(" ");
		}
		System.out.println(points.size());
		System.out.println(pointsLine.toString().trim());
	}
}
