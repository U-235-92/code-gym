package aq.gym.contests.t_bank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ContractDelivery {
	
//case 1
//5 5
//1 4 9 16 25
//2	
//case 2
//6 4
//1 2 3 6 8 25
//5
//case 3
//5 4
//1 8 25 26 31
//4
	
//	https://edu.tbank.ru/selection/76378fbd-1998-48fa-944e-eb736d321f11/practice/244/task/3
	public static void main(String[] args) {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			int[] data = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::valueOf).toArray();
			int emplCount = data[0], time = data[1];
			int[] stages = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::valueOf).toArray();
			int stage = Integer.valueOf(br.readLine()) - 1;
			int result = getMinStepsCount(emplCount, time, stages, stage);
			System.out.println(result);
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}

	private static int getMinStepsCount(int emplCount, int time, int[] stages, int stage) {
		if(isEmployeeOnFirstFloor(stage) || isEmployeeOnLastFloor(stage, stages)) {
			return stages[stages.length - 1] - stages[0];
		} else if(isAbleToDeliveryContractsSequentlyFromDown(stage, time, stages) || isAbleToDeliveryContractsSequentlyFromUp(stage, time, stages)) {
			return stages[stages.length - 1] - stages[0];
		} else {
			int fromEmployeeToTopAndThenDown = (stages[stages.length - 1] - stages[0]) + stages[stages.length - 1] - stages[stage];
			int fromEmployeeToDownAndThenTop = (stages[stages.length - 1] - stages[0]) + stages[stage] - stages[0];
			return Math.min(fromEmployeeToTopAndThenDown, fromEmployeeToDownAndThenTop);
		}
	}
	
	private static boolean isEmployeeOnFirstFloor(int stage) {
		return stage == 0;
	}
	
	private static boolean isEmployeeOnLastFloor(int stage, int[] stages) {
		return stage == stages.length - 1;
	}
	
	private static boolean isAbleToDeliveryContractsSequentlyFromDown(int stage, int time, int[] stages) {
		if(stages[stage] - stages[0] <= time) {
			return true;
		}
		return false;
	}
	
	private static boolean isAbleToDeliveryContractsSequentlyFromUp(int stage, int time, int[] stages) {
		if(stages[stages.length - 1] - stages[stage] <= time) {
			return true;
		}
		return false;
	}
}
