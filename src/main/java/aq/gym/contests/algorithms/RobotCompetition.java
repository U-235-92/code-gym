package aq.gym.contests.algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RobotCompetition {

	public static void main(String[] args) {
		app();
	}

	private static void app() {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			int[] data = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::valueOf).toArray();
			int observableRobotNumber = data[1];
			List<Integer> robotSkillLevels = Stream
					.of(br.readLine().split("\\s"))
					.map(Integer::valueOf)
					.collect(Collectors.toCollection(ArrayList::new));
			int maximalWinCount = getMaximalWinCount(robotSkillLevels, observableRobotNumber);
			System.out.println(maximalWinCount);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private static int getMaximalWinCount(List<Integer> robotSkillLevels, int observableRobotNumber) {
		int maximalWinCount = 0;
		int minSkillLevel = robotSkillLevels.stream().mapToInt(Integer::valueOf).summaryStatistics().getMin();
		if(robotSkillLevels.size() == 1) {
			return maximalWinCount;
		} else if(observableRobotNumber == minSkillLevel) {
			return maximalWinCount;
		} else {
			final int skillLevelValueObservableRobot = robotSkillLevels.get(observableRobotNumber - 1);
			robotSkillLevels.sort(Comparator.naturalOrder());
			while(true) {
				List<Integer> bestRobotSkillLevels = new ArrayList<>();
				int idxObservableRobot = robotSkillLevels.indexOf(skillLevelValueObservableRobot);
				if(idxObservableRobot != 0) {					
					bestRobotSkillLevels.add(robotSkillLevels.remove(idxObservableRobot));
					robotSkillLevels.remove(idxObservableRobot - 1);
				}
				for(int i = robotSkillLevels.size() - 1; i >= 0; i -= 2) {
					int firstRobot = robotSkillLevels.remove(i);
					if(firstRobot == skillLevelValueObservableRobot) {
						return maximalWinCount;
					}
					if(i > 0) {						
						bestRobotSkillLevels.add(firstRobot);
						int secondRobot = robotSkillLevels.remove(i - 1);
						if(secondRobot == skillLevelValueObservableRobot) {
							return maximalWinCount;
						}
					} else {
						bestRobotSkillLevels.add(firstRobot);
					}
				}
				bestRobotSkillLevels.sort(Comparator.naturalOrder());
				robotSkillLevels = bestRobotSkillLevels;
				maximalWinCount++;
			}
		}
	}
}
