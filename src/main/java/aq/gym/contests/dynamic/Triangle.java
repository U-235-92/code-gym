package aq.gym.contests.dynamic;

import java.util.ArrayList;
import java.util.List;

public class Triangle {

//	https://leetcode.com/problems/triangle/description
	public static void main(String[] args) {
		List<List<Integer>> triangle = new ArrayList<>();
		triangle.add(List.of(2));
		triangle.add(List.of(3, 4));
		triangle.add(List.of(6, 5, 7));
		triangle.add(List.of(4, 1, 8, 3));
//		triangle.add(List.of(2));
//		triangle.add(List.of(3, 3));
//		triangle.add(List.of(8, 6, 5));
//		triangle.add(List.of(4, 7, 9, 8));
		System.out.println(new Triangle().minimumTotal(triangle));
	}
	
    public int minimumTotal(List<List<Integer>> triangle) {
    	if(triangle.size() == 1) {
    		return triangle.get(0).get(0);
    	} else {    		
    		return evaluatePath(triangle, new ArrayList<List<Integer>>(), 0);
    	}
    }
    
    private int evaluatePath(List<List<Integer>> triangle, List<List<Integer>> sums, int depth) {
    	if(depth == triangle.size() - 1) {
    		return sums.get(sums.size() - 1).stream().mapToInt(Integer::intValue).min().getAsInt();
    	}
    	if(sums.size() == 0) {
    		sums.add(triangle.get(depth));
    	}
    	List<Integer> currLineSums = sums.get(depth);
    	List<Integer> nextLineSums = new ArrayList<>();
    	List<Integer> triangleNextLine = triangle.get(depth + 1);
    	for(int i = 0; i < triangle.get(depth).size(); i++) {
    		int left = triangleNextLine.get(i);
    		int right = triangleNextLine.get(i + 1);
    		int base = currLineSums.get(i);
    		if(nextLineSums.size() == 0) {    			
    			nextLineSums.add(left + base); 
    			nextLineSums.add(right + base);
    		} else {
    			nextLineSums.set(i, Math.min((left + base), nextLineSums.get(i))); 
    			nextLineSums.add(right + base);
    		}
    	}
    	sums.add(nextLineSums);
    	return evaluatePath(triangle, sums, depth + 1);
    }
}
