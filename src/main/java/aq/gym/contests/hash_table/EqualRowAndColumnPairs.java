package aq.gym.contests.hash_table;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class EqualRowAndColumnPairs {

//	https://leetcode.com/problems/equal-row-and-column-pairs/description
	public static void main(String[] args) {
//		int[][] grid = new int[][]{ {1,1,5,8}, {1,1,1,1}, {1,1,1,1}, {1,1,1,1} };
		int[][] grid = new int[][]{ {11,1}, {1,11} };
		System.out.println(new EqualRowAndColumnPairs().equalPairs(grid));
	}

    public int equalPairs(int[][] grid) {
        Map<String, Integer> rows = countRowsMap(grid);
        Map<String, Integer> columns = countColumnsMap(grid);
        return getPairsCount(rows, columns);
    }
    
	private Map<String, Integer> countRowsMap(int[][] grid) {
		Map<String, Integer> map = new HashMap<>();
    	for(int i = 0; i < grid.length; i++) {
    		String row = Arrays.stream(grid[i])
    				.mapToObj(String::valueOf)
    				.collect(Collectors.joining(","));
    		compute(map, row);
    	}
    	return map;
    }
    
	private Map<String, Integer> countColumnsMap(int[][] grid) {
		Map<String, Integer> map = new HashMap<>();
    	int j = 0;
    	StringBuilder column = new StringBuilder();
    	for(int i = 0; i < grid.length; i++) {
    		column.append(grid[i][j]);
    		if(i == grid.length - 1) {
    			compute(map, column.toString());
    			column = new StringBuilder();
    			j++; i = -1;
    		} else {
    			column.append(",");
    		}
    		if(j >= grid.length) {
    			break;
    		}
    	}
    	return map;
    }
	
	private void compute(Map<String, Integer> map, String key) {
		if(map.get(key) == null) map.put(key, 1);
		else map.put(key, map.get(key) + 1);
	}
	
	private int getPairsCount(Map<String, Integer> rows, Map<String, Integer> columns) {
		int count = 0;
		for(Map.Entry<String, Integer> eRow : rows.entrySet()) {
			String row = eRow.getKey();
			for(Map.Entry<String, Integer> eColumn : columns.entrySet()) {
				String column = eColumn.getKey();
				if(row.equals(column)) count += eRow.getValue() * eColumn.getValue(); 
			}
		}
		return count;
	}
}
