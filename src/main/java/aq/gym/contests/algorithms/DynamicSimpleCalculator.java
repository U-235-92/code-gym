package aq.gym.contests.algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DynamicSimpleCalculator {

	public static void main(String[] args) {
		app();
	}

	private static void app() {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			int n = Integer.valueOf(br.readLine());
			Map<Integer, Integer> calculationMap = calculateMinimalOperationCountMap(n);
			int minimalOperationCount = calculateMinimalOperationCount(n, calculationMap);
			List<Integer> calculationTmpValues = calculateTemporalCalculationValues(n, calculationMap);
			StringBuilder sb = new StringBuilder();
			System.out.println(minimalOperationCount);
			for(int i = calculationTmpValues.size() - 1; i >= 0; i--) {
				sb.append(calculationTmpValues.get(i));
				sb.append(" ");
			}
			System.out.println(sb.toString().trim());
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	private static Map<Integer, Integer> calculateMinimalOperationCountMap(int n) {
		Map<Integer, Integer> calculationMap = new LinkedHashMap<>();
		calculationMap.put(1, 0);
		for(int i = 2; i <= n; i++) {
			calculationMap.put(i, 1 + calculationMap.get(i - 1));
			if(i % 2 == 0) {
				int currentCalculationCount = calculationMap.get(i);
				int dynamicCalculationCount = calculationMap.get(i / 2) + 1;
				calculationMap.put(i, Integer.min(currentCalculationCount, dynamicCalculationCount));
			} 
			if(i % 3 == 0) {
				int currentCalculationCount = calculationMap.get(i);
				int dynamicCalculationCount = calculationMap.get(i / 3) + 1;
				calculationMap.put(i, Integer.min(currentCalculationCount, dynamicCalculationCount));
			} 
		}
		return calculationMap;
	}
	
	private static int calculateMinimalOperationCount(int n, Map<Integer, Integer> calculationCountMap) {
		return calculationCountMap.get(n);
	}
	
	private static List<Integer> calculateTemporalCalculationValues(int n, Map<Integer, Integer> calculationCountMap) {
		List<Integer> calculationTemporalValues = new ArrayList<Integer>();
		calculationTemporalValues.add(n);				
		while(n > 1) {
			if(calculationCountMap.get(n) == calculationCountMap.get(n - 1) + 1) {
				n = n - 1;
			} else if(n % 2 == 0 && (calculationCountMap.get(n / 2) + 1) == calculationCountMap.get(n)) {
				n = n / 2;
			} else if(n % 3 == 0 && (calculationCountMap.get(n / 3) + 1) == calculationCountMap.get(n)) {
				n = n / 3;
			} 
			calculationTemporalValues.add(n);				
		}
		return calculationTemporalValues;
	}
}
