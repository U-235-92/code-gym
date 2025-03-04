package aq.gym.contests.easy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RocketTaxiStatistics {
	
	public static void main(String[] args) {
		String result = getStatistics();
		System.out.println(result);
	}
	
	private static String getStatistics() {
		int n = 0;
		String[] stats = null;
		try(BufferedReader br = new BufferedReader(new FileReader(new File("src/main/java/aq/gym/contests/easy/rocket_taxi_stat_input.txt")))) {
			n = Integer.valueOf(br.readLine());
			String line = null;
			int i = 0;
			stats = new String[n];
			while((line = br.readLine()) != null) {
				stats[i++] = line;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		StringBuilder result = new StringBuilder();
		Map<Integer, List<Log>> logStat = Arrays.stream(stats)
			.map(line -> {
				String[] parts = line.toString().split(" ");
				Log log = new Log();
				log.day = Integer.valueOf(parts[0]);
				log.hour = Integer.valueOf(parts[1]);
				log.minute = Integer.valueOf(parts[2]);
				log.id = Integer.valueOf(parts[3]);
				log.status = parts[4];
				return log;
			})
			.collect(Collectors.groupingBy(Log::getId));
		List<Integer> keys = new ArrayList<Integer>(logStat.keySet());
		keys.sort(Comparator.comparing(Integer::intValue));
		int total = 0;
		for(Integer key : keys) {
			total = 0;
			List<Log> statisticsForKey = logStat.get(key);
			statisticsForKey.sort(Comparator.comparing(Log::getDay)
					.thenComparing(Comparator.comparing(Log::getHour))
					.thenComparing(Comparator.comparing(Log::getMinute))
					.thenComparing(Comparator.comparing(Log::getId)));
			int tmp = 0;
			for(int i = 0; i < statisticsForKey.size() - 1; i++) {
				int currentDay = statisticsForKey.get(i).getDay();
				int currentHour = statisticsForKey.get(i).getHour();
				int currentMinute = statisticsForKey.get(i).getMinute();
				String currentStatus = statisticsForKey.get(i).getStatus();
				LocalDateTime current = LocalDateTime.now().withDayOfYear(currentDay).withHour(currentHour).withMinute(currentMinute);
				int nextDay = statisticsForKey.get(i + 1).getDay();
				int nextHour = statisticsForKey.get(i + 1).getHour();
				int nextMinute = statisticsForKey.get(i + 1).getMinute();
				LocalDateTime next = LocalDateTime.now().withDayOfYear(nextDay).withHour(nextHour).withMinute(nextMinute);
				if(currentStatus.equals("S") || currentStatus.equals("C")) {
					tmp = 0;
				} else {
					int dHour = 0;
					if(nextHour - currentHour >= 0) {
						dHour = next.minusHours(currentHour).getHour();
					} else {
						dHour = (int) Duration.between(current, next).toHours();
					}
					int dMinutes = next.minusMinutes(currentMinute).getMinute();
					tmp = dMinutes + (dHour * 60);
					total += tmp;
					tmp = 0;
				}
			}
			result.append(total);
			result.append(" ");
		}
		return result.toString().trim();
	}
	
	private static class Log {
		
		private int day;
		private int hour;
		private int minute;
		private int id;
		private String status;
		
		public int getDay() {
			return day;
		}

		public int getHour() {
			return hour;
		}

		public int getMinute() {
			return minute;
		}

		public int getId() {
			return id;
		}

		public String getStatus() {
			return status;
		}

		@Override
		public String toString() {
			return "Log [day=" + day + ", hour=" + hour + ", minute=" + minute + ", id=" + id + ", status=" + status
					+ "]";
		}
	}
}
