package aq.gym.contests.recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class SpaceGarbageCollector {
	
//	https://coderun.yandex.ru/problem/space-scavenger/description
	public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Map<Integer, Long>> movementNumberCache = new HashMap<>();
        Map<String, String> directionRuleMap = getDirectionRuleMap(reader);
        String[] movementDetails = getMovementDetails(reader);
		long movementNumber = getMovementNumber(directionRuleMap, movementNumberCache, getDirection(movementDetails), getDistance(movementDetails));
        System.out.println(movementNumber);
        reader.close();
    }

	private static Map<String, String> getDirectionRuleMap(BufferedReader reader) throws IOException {
		 Map<String, String> directionRuleMap = new HashMap<>();
	        for(int i = 0; i < 6; i++) {
	        	String line = reader.readLine();
	        	switch (i) {
	        	case 0:
	        		directionRuleMap.put("N", (line.isEmpty()) ? null : line);
	        		break;
	        	case 1:
	        		directionRuleMap.put("S", (line.isEmpty()) ? null : line);
	        		break;
	        	case 2:
	        		directionRuleMap.put("W", (line.isEmpty()) ? null : line);
	        		break;
	        	case 3:
	        		directionRuleMap.put("E", (line.isEmpty()) ? null : line);
	        		break;
	        	case 4:
	        		directionRuleMap.put("U", (line.isEmpty()) ? null : line);
	        		break;
	        	case 5:
	        		directionRuleMap.put("D", (line.isEmpty()) ? null : line);
	        		break;
	        	}
	        }
	        return directionRuleMap;
	}
	
	private static String[] getMovementDetails(BufferedReader reader) throws IOException {
		return reader.readLine().split("\\s");
	}
	
	private static String getDirection(String[] movementDetails) {
		return movementDetails[0];
	}
	
	private static int getDistance(String[] movementDetails) {
		return Integer.valueOf(movementDetails[1]);
	}
	
	@SuppressWarnings("unused")
	private static long getMovementNumber(Map<String, String> directionRuleMap, Map<String, Map<Integer, Long>> movementNumberCache, String direction, int distance) {
		long movementNumber = 0L;
		if(movementNumberCache.get(direction) != null && movementNumberCache.get(direction).get(distance) != null) {
			return movementNumberCache.get(direction).get(distance);
		} 
		if(distance == 1) {
			return 1L; 
		}
		movementNumber = 1L;
		String nextDirectionRuleLine = directionRuleMap.get(direction);
		if(nextDirectionRuleLine != null && !nextDirectionRuleLine.isEmpty()) {
			String[] nextDirections = nextDirectionRuleLine.split("");
			for(String nextDirection : nextDirections) {
				movementNumber += getMovementNumber(directionRuleMap, movementNumberCache, nextDirection, distance - 1);
			}
		} 
		movementNumberCache.computeIfAbsent(direction, k -> new HashMap<>()).put(distance, movementNumber);
		return movementNumber;
	}
}
