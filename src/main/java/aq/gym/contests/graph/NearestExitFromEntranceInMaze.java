package aq.gym.contests.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

public class NearestExitFromEntranceInMaze {

//	https://leetcode.com/problems/nearest-exit-from-entrance-in-maze/
	public static void main(String[] args) {
//		char[][] maze = {{'+','+','.','+'},{'.','.','.','+'},{'+','+','+','.'}};
//		int[] entrance = {1,2};
//		char[][] maze = {{'+','+','+'},{'.','.','.'},{'+','+','+'}};
//		int[] entrance = {1,0};
//		char[][] maze = {{'.','+'}};
//		int[] entrance = {0,0};		
//		char[][] maze = {{'+','+','+','+','+'},{'+','.','.','.','+'},{'+','.','+','.','+'},{'+','.','.','.','+'},{'+','+','+','+','+'}};
//		int[] entrance = {1,1};
//		char[][] maze = {{'.','.','+'},{'.','.','.'},{'+','+','+'}};
//		int[] entrance = {0,0};
		char[][] maze = {	{'.','.','+','.'},
							{'.','+','.','.'},
							{'.','.','.','+'},
							{'.','.','+','.'}};
		int[] entrance = {2,2};
		System.out.println(new NearestExitFromEntranceInMaze().nearestExit(maze, entrance));
	}

    public int nearestExit(char[][] maze, int[] entrance) {
    	int stepsCount = 0;
    	boolean isExitAchieved = false;
    	Set<Node> visited = new HashSet<>();
    	Queue<Node> queue = new ArrayDeque<>();
    	Map<Node, List<Node>> graph = getGraph(maze);
    	Map<Node, Integer> countStepsOnBfsLevelMap = new HashMap<>();
    	final Node start = new Node(entrance[0], entrance[1], '.');
    	queue.offer(start);
    	while(!queue.isEmpty()) {
    		Node node = queue.poll();
    		if(isExitAchieved(node, start, maze)) {
    			stepsCount = countStepsOnBfsLevelMap.get(node);
    			isExitAchieved = true;
    			break;
    		} else {
				stepsCount = countStepsOnBfsLevelMap.getOrDefault(node, stepsCount);
    			visited.add(node);
    			List<Node> neighbours = graph.get(node);
    			for(Node neighbour : neighbours) {
    				if(!visited.contains(neighbour)) {    				
    					queue.offer(neighbour);
    					visited.add(neighbour);
    					countStepsOnBfsLevelMap.put(neighbour, stepsCount + 1);
    				}
    			}
    		}
    	}
    	return isExitAchieved ? stepsCount : -1;
    }
    
    private Map<Node, List<Node>> getGraph(char[][] maze) {
    	Map<Node, List<Node>> graph = new HashMap<>();
    	for(int i = 0; i < maze.length; i++) {
    		for(int j = 0; j < maze[i].length; j++) {
    			if(maze[i][j] == '.') {
    				putNodeIntoGraph(i, j, maze, graph);
    			}
    		}
    	}
    	return graph;
    }
    
    private void putNodeIntoGraph(int i, int j, char[][] maze, Map<Node, List<Node>> graph) {
    	Node node = new Node(i, j, maze[i][j]);
    	graph.put(node, new ArrayList<Node>());
    	int[] iDirs = {1,-1,0,0};
    	int[] jDirs = {0,0,1,-1};
    	for(int d = 0; d < 4; d++) {
    		int p = i + iDirs[d];
    		int q = j + jDirs[d];
    		if(p >= 0 && p < maze.length && q >= 0 && q < maze[i].length && maze[p][q] == '.') {
    			graph.get(node).add(new Node(p, q, maze[p][q]));
    		}
    	}
    }
    
    private boolean isExitAchieved(Node node, Node start, char[][] maze) {
    	return node != start && 
    			(node.i == 0 || node.i == maze.length - 1 || node.j == 0 || node.j == maze[0].length - 1) && 
    			maze[node.i][node.j] != '+';
    }
    
    private static final class Node {
    	
    	private final int i;
    	private final int j;
    	private final char value;
    	
		public Node(int i, int j, char value) {
			super();
			this.i = i;
			this.j = j;
			this.value = value;
		}
    	
    	@Override
    	public boolean equals(Object obj) {
    		if(obj == null || obj.getClass() != this.getClass()) return false;
    		if(obj == this) return true;
    		Node node = (Node) obj;
    		return node.i == this.i && node.j == this.j && node.value == this.value;
    	}
    	
    	@Override
    	public int hashCode() {
    		return Objects.hash(i, j, value);
    	}
    	
    	@Override
    	public String toString() {
    		return String.format("[%d, %d, %s]", i, j, value);
    	}
    }
}
