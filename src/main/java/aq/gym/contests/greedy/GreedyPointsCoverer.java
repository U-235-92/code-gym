package aq.gym.contests.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class GreedyPointsCoverer {

	public static void main(String[] args) {
		fastWay();
	}
	
	private static void fastWay() {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			long[] data = Arrays.stream(br.readLine().split("\\s")).mapToLong(Long::valueOf).toArray();
			@SuppressWarnings("unused")
			long n = data[0];
			long l = data[1];
			Set<Long> buffer = Stream
					.of(br.readLine().split("\\s"))
					.map(Long::valueOf)
					.collect(Collectors.toCollection(TreeSet::new));
			List<Long> points = new ArrayList<>(buffer);
			LongSummaryStatistics statistics = points.stream().mapToLong(Long::valueOf).summaryStatistics();
			long min = statistics.getMin();
			long max = statistics.getMax();
			long lineCount = 1;
			if(Math.abs(max) + Math.abs(min) <= l) {
				System.out.println(lineCount);
				return;
			} else if(min == max) {
				System.out.println(lineCount);
				return;
			} else {				
				long border = points.get(0) + l;
				for(int i = 1; i < points.size(); i++) {
					long currentPoint = points.get(i);
					if(currentPoint > border) {
						lineCount++;
						border = currentPoint + l;
					}
				}
				System.out.println(lineCount);
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	private static void badMemoryLimitWay() {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			int[] data = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::valueOf).toArray();
			int n = data[0];
			int l = data[1];
			Set<Long> origPoints = Stream
					.of(br.readLine().split("\\s"))
					.map(Long::valueOf)
					.collect(Collectors.toCollection(HashSet::new));
			LongSummaryStatistics pointsStatistics = origPoints
					.stream()
					.mapToLong(Long::valueOf)
					.summaryStatistics();
			long minPoint = pointsStatistics.getMin();
			long maxPoint = pointsStatistics.getMax();
			long limit = (maxPoint - minPoint) + 1;
			List<Long> allPoints = Stream
					.iterate(minPoint, point -> point + 1L)
					.limit(limit)
					.collect(Collectors.toCollection(ArrayList::new));
			long lineCount = 0;
			for(int i = 0; i < allPoints.size();) {
				if(origPoints.contains(allPoints.get(i))) {				
					lineCount++;
					i += (l + 1);
				} else {
					i++;
				}
			}
			System.out.println(lineCount);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	private static void badTimeLimitWay() {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			long[] data = Arrays.stream(br.readLine().split("\\s")).mapToLong(Long::valueOf).toArray();
			long n = data[0];
			long l = data[1];
			Set<Long> buffer = Stream
					.of(br.readLine().split("\\s"))
					.map(Long::valueOf)
					.collect(Collectors.toCollection(TreeSet::new));
			List<Long> points = new ArrayList<>(buffer);
			long lineCount = 0;
			while(points.size() > 0) {
				long start = points.get(0);
				long limit = l + 1;
				List<Long> trash = LongStream
						.iterate(start, p -> p + 1L)
						.limit(limit)
						.boxed()
						.collect(Collectors.toCollection(ArrayList::new));
				points.removeAll(trash);
				lineCount++;
			}
			System.out.println(lineCount);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
