package aq.gym.contests.stack;

import java.util.ArrayDeque;
import java.util.Arrays;

public class AsteroidCollision {

//	https://leetcode.com/problems/asteroid-collision/description
	public static void main(String[] args) {
//		int[] asteroids = {5,10,-5};
//		int[] asteroids = {8,-8};
//		int[] asteroids = {10,2,-5};
//		int[] asteroids = {10,-5,6};
//		int[] asteroids = {-2,-1,1,2};
//		int[] asteroids = {-1,2,10,5};
//		int[] asteroids = {-2,1,1,2}; 
//		int[] asteroids = {1,-2,10,-5}; //+
//		int[] asteroids = {8,-5}; 
//		int[] asteroids = {-5,8}; 
//		int[] asteroids = {-2,-2,1,-2}; 
//		int[] asteroids = {-2,-2,5,-2}; 
//		int[] asteroids = {1,1,1,-2};
//		int[] asteroids = {-2,-2,1};
//		int[] asteroids = {-2,2,1,-2};
//		int[] asteroids = {2,-1,-2,-2}; //+
//		int[] asteroids = {-1,2,-1,2};
//		int[] asteroids = {7,-1,2,-3,-6,-6,-6,4,10,2};
		int[] asteroids = {10,-8,-9,1,8,8,-9,1,2,10}; //+
		System.out.println(Arrays.toString(new AsteroidCollision().asteroidCollision(asteroids)));
	}
	
    public int[] asteroidCollision(int[] asteroids) {
    	ArrayDeque<Integer> stack = new ArrayDeque<>();
    	for(int asteroid : asteroids) {   		
    		if(stack.isEmpty()) {
    			stack.push(asteroid);
    		} else {
    			int stacked = stack.peek();
    			if(isOppositeValuesAsteroids(stacked, asteroid)) {
    				if(isOncommingAsteroids(stacked, asteroid)) {    					
    					stack.pop();
    				} else {
    					stack.push(asteroid);
    				}
    			} else if(hasAsteroidsSameDirection(stacked, asteroid) || isNeverCollision(stacked, asteroid)) {
    				stack.push(asteroid);
    			} else if(isOncommingAsteroids(stacked, asteroid) && isIncommingMoreThanStacked(stacked, asteroid)) {
    				while(!stack.isEmpty() && isOncommingAsteroids(stacked, asteroid) && isIncommingMoreThanStacked(stacked, asteroid)) {
    					stack.pop();
    					if(!stack.isEmpty()) {   
    						stacked = stack.peek();
    					}
    				}
    				if(hasAsteroidsSameDirection(stacked, asteroid) || (isOncommingAsteroids(stacked, asteroid) && isIncommingMoreThanStacked(stacked, asteroid))) {    					
    					stack.push(asteroid);
    				} else if(stacked == -asteroid) {
    					stack.pop();
    					if(!stack.isEmpty()) {   
    						stacked = stack.peek();
    					}
    				}
    			}
    		}
    	}
    	int[] result = new int[stack.size()];
    	int idx = result.length - 1;
    	while(!stack.isEmpty()) {
    		result[idx--] = stack.pop();
    	}
    	return result;
    }
    
    private boolean isOppositeValuesAsteroids(int stacked, int incomming) {
    	return stacked == -incomming;
    }
    
    private boolean isOncommingAsteroids(int stacked, int incomming) {
    	return stacked > 0 && incomming < 0;
    }
    
    private boolean hasAsteroidsSameDirection(int stacked, int incomming) {
    	return (stacked < 0 && incomming < 0) || (stacked > 0 && incomming > 0);
    }
    
    private boolean isNeverCollision(int stacked, int incomming) {
    	return stacked < 0 && incomming > 0;
    }
    
    private boolean isIncommingMoreThanStacked(int stacked, int incomming) {
    	return Math.abs(stacked) < Math.abs(incomming);
    }
}
