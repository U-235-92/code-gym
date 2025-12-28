package aq.gym.contests.backtracking;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

public class SlidingPuzzle {

//	https://leetcode.com/problems/sliding-puzzle/
	public static void main(String[] args) {
//		int[][] board = {{1,2,3},{4,0,5}}; // 1
//		int[][] board = {{1,2,3},{5,4,0}}; // -1
		int[][] board = {{4,1,2},{5,0,3}}; // 5
		System.out.println(new SlidingPuzzle().slidingPuzzle(board));
//		Board b1 = new Board(board);
//		Board b2 = new Board(board);
//		System.out.println(b1 == b2);
//		System.out.println(b1.equals(b2));
//		System.out.println(b1.hashCode());
//		System.out.println(b2.hashCode());
	}
	
    public int slidingPuzzle(int[][] board) {
        Set<Board> visited = new HashSet<>();
        Queue<Board> bfs = new ArrayDeque<>();
        Map<Board, Integer> bfsBoardStatesDepthMap = new HashMap<>();
        Board start = new Board(board, 0);
        bfs.offer(start);
        bfsBoardStatesDepthMap.put(start, 0);
        return evaluateMoveCount(bfs, visited, bfsBoardStatesDepthMap);
    }
    
    private int evaluateMoveCount(Queue<Board> bfs, Set<Board> visited, Map<Board, Integer> bfsBoardStatesDepthMap) {
    	int moveCount = -1;
    	while(!bfs.isEmpty()) {
    		Board board = bfs.poll();
    		visited.add(board);
    		if(isFinishState(board)) {
    			moveCount = board.depth;
    			break;
    		}
    		List<Board> neighbours = getNeighbourBoardStates(board);
    		for(Board neighbour : neighbours) {
    			if(!visited.contains(neighbour)) {
    				bfs.offer(neighbour);
    				visited.add(neighbour);
    			}
    		}
    	}
    	return moveCount;
    }

    private boolean isFinishState(Board board) {
    	Board finish = new Board(new int[][]{{1,2,3},{4,5,0}}, 0);
    	return finish.equals(board);
    }
    
    private List<Board> getNeighbourBoardStates(Board board) {
    	List<Board> neighbours = new ArrayList<>();
    	int[] zeroPuzzleCoords = board.getZeroPuzzleCoordinates();
    	int iZero = zeroPuzzleCoords[0], jZero = zeroPuzzleCoords[1];
    	int h = board.board.length, w = board.board[0].length;
    	int[] iDirs = {0,0,1,-1}, jDirs = {1,-1,0,0};
    	for(int d = 0; d < 4; d++) {
    		int i = iDirs[d], j = jDirs[d];
    		if(iZero + i >= 0 && iZero + i < h && jZero + j >= 0 && jZero + j < w) {
    			int[][] copy = getBoardCopy(board.board);
    			swap(copy, i, j, iZero, jZero);
    			Board neighbour = new Board(copy, board.depth + 1);
    			neighbours.add(neighbour);
    		}
    	}
    	return neighbours;
    }
    
    private int[][] getBoardCopy(int[][] board) {
    	int[][] copy = new int[2][3];
		for(int c = 0; c < board.length; c++) {    				
			System.arraycopy(board[c], 0, copy[c], 0, board[c].length);
		}
		return copy;
    }
    
    private void swap(int[][] copy, int i, int j, int iZero, int jZero) {
    	int tmp = copy[iZero][jZero];
		copy[iZero][jZero] = copy[iZero + i][jZero + j];
		copy[iZero + i][jZero + j] = tmp;
    }
    
    private static class Board {
    	
    	private int[][] board;
    	private int depth;
    	
    	Board(int[][] board, int depth) {
    		this.board = board; 
    		this.depth = depth;
    	}
    	
    	int[] getZeroPuzzleCoordinates() {
    		int[] coords = new int[2];
    		for(int i = 0; i < board.length; i++) {
    			for(int j = 0; j < board[i].length; j++) {
    				if(board[i][j] == 0) {
    					coords[0] = i;
    					coords[1] = j;
    				}
    			}
    		}
    		return coords;
    	}
    	
    	@Override
    	public boolean equals(Object obj) {
    		if(obj == null || this.getClass() != obj.getClass()) return false;
    		if(this == obj) return true;
    		Board b = (Board) obj;
    		for(int i = 0; i < b.board.length; i++) {
    			for(int j = 0; j < b.board[i].length; j++) {
    				if(board[i][j] != b.board[i][j]) return false;
    			}
    		}
    		return true;
    	}
    	
    	@Override
    	public int hashCode() {
    		int hash = 0;
    		for(int[] line : board) {
    			hash += (Objects.hash(Arrays.hashCode(line)) * 32);
    		}
    		return hash;
    	}
    	
    	@Override
    	public String toString() {
    		StringBuilder sb = new StringBuilder();
    		sb.append(depth).append(", [");
    		for(int[] line : board) {
    			sb.append(Arrays.toString(line));
    		}
    		sb.append("]");
    		return sb.toString();
    	}
    }
}
