package aq.gym.contests.graph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class EvaluateDivision {

//	https://leetcode.com/problems/evaluate-division/
	public static void main(String[] args) {
//		Case #1
//		List<List<String>> equations = List.of(List.of("a","b"), List.of("b","c"));
//		double[] values = {2.0,3.0};
//		List<List<String>> queries = List.of(List.of("c","a"), List.of("a","c"), List.of("b","a"), List.of("a","e"), List.of("a","a"), List.of("x","x"));
//		Case #2
		List<List<String>> equations = List.of(List.of("a","b"), List.of("b","c"), List.of("bc","cd"));
		double[] values = {1.5,2.5,5.0};
		List<List<String>> queries = List.of(List.of("a","c"), List.of("c","b"), List.of("bc","cd"), List.of("cd","bc"));
//		Case #3
//		List<List<String>> equations = List.of(List.of("a","b"), List.of("c","b"), List.of("d","b"), List.of("w","x"), List.of("y","x"), List.of("z","x"), List.of("w","d"));
//		double[] values = {2.0,3.0,4.0,5.0,6.0,7.0,8.0};
//		List<List<String>> queries = List.of(List.of("a","c"), List.of("b","c"), List.of("a","e"), List.of("a","a"), List.of("x","x"), List.of("a","z"));
		System.out.println(Arrays.toString(new EvaluateDivision().calcEquation(equations, values, queries)));
	}

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> graph = initializeGraph(equations, values);
        double[] answers = calcEquation(graph, queries);
        return answers;
    }
    
	@SuppressWarnings("unused")
	private Map<String, Map<String, Double>> initializeGraph(List<List<String>> equations, double[] values) {
    	Map<String, Map<String, Double>> graph = new HashMap<>();
    	for(int i = 0; i < equations.size(); i++) {
    		List<String> equation = equations.get(i);
    		String divisible = equation.get(0);
    		String divisor = equation.get(1);
    		double value = values[i];
            graph.computeIfAbsent(divisible, k -> new HashMap<>()).put(divisible, 1.0);
            graph.computeIfAbsent(divisible, k -> new HashMap<>()).put(divisor, value);
            graph.computeIfAbsent(divisor, k -> new HashMap<>()).put(divisor, 1.0);
            graph.computeIfAbsent(divisor, k -> new HashMap<>()).put(divisible, 1.0 / value);
    	}
    	return graph;
    }
    
    private double[] calcEquation(Map<String, Map<String, Double>> graph, List<List<String>> queries) {
    	double[] answers = new double[queries.size()];
    	String divisor = null, divisible = null;
    	for(int i = 0; i < queries.size(); i++) {
    		List<String> query = queries.get(i);
    		divisible = query.get(0);
    		divisor = query.get(1);
    		if(!graph.containsKey(divisible) || !graph.containsKey(divisor)) {
    			answers[i] = -1;
    		} else if(divisible.equals(divisor)) {
    			answers[i] = 1;
    		} else {
    			boolean isFound = false;
    			Set<String> visited = new HashSet<>();
				Queue<Map.Entry<String, Double>> queue = new ArrayDeque<>();
				queue.offer(Map.entry(divisible, 1.0));
				while(!queue.isEmpty()) {
					Map.Entry<String, Double> entry = queue.poll();
	                String current = entry.getKey();
	                double value = entry.getValue();
					visited.add(entry.getKey());
	                if(current.equals(divisor)) {
	                    answers[i] = value;
	                    isFound = true;
	                    break;
	                }
					for(Map.Entry<String, Double> neighbour : graph.get(entry.getKey()).entrySet()) {
						if(!visited.contains(neighbour.getKey())) {
							visited.add(neighbour.getKey());
							queue.offer(Map.entry(neighbour.getKey(), value * neighbour.getValue()));
						}
					}
				}
				if(!isFound) {
	                answers[i] = -1.0;
	            }
    		}
    	}
    	return answers;
    }
}
