package aq.gym.contests.array;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

public class ContainerWithMostWater {

//	https://leetcode.com/problems/container-with-most-water
	public static void main(String[] args) {
//		int[] height = {1,1};
//		int twoPointers = new ContainerWithMostWater().twoPointersWay(height);
//		int bruteForce = new ContainerWithMostWater().bruteForceWay(height);
//		System.out.println("TP: " + twoPointers + " BF: " + bruteForce);
		test();
	}
    
    private static void test() {
    	ContainerWithMostWater cwmw = new ContainerWithMostWater();
    	while(true) {
    		int[] height = Stream.generate(() -> new Random().nextInt(100)).limit(10_000).mapToInt(Integer::valueOf).toArray();
    		int twoPointers = cwmw.twoPointersWay(height);
    		int bruteForce = cwmw.bruteForceWay(height);
    		if(twoPointers != bruteForce) {
    			System.out.println("W/A");
    			System.out.println("TP: " + twoPointers + " BF: " + bruteForce);
    			System.out.println("Height: " + Arrays.toString(height));
    			break;
    		} else {
    			System.out.println("OK");
    		}
    	}
    }
    
    public int maxArea(int[] height) {
        return twoPointersWay(height);
    }
    
    private int twoPointersWay(int[] height) {
    	int i = 0, j = height.length - 1, maxArea = 0;
        while(i < j) {
        	int left = height[i];
        	int right = height[j];
        	int minCurrHeight = Math.min(left, right);
        	int currWidth = j - i;
        	int currArea = minCurrHeight * currWidth;
        	maxArea = Math.max(maxArea, currArea);
        	if(left < right) {
        		i++;
        	} else {
        		j--;
        	}
        }
        return maxArea;
    }
    
    private int bruteForceWay(int[] height) {
    	int maxArea = 0;
    	for(int i = 0; i < height.length; i++) {
    		for(int j = i + 1; j < height.length; j++) {
    			int width = j - i;
    			int minCurrHeight = Math.min(height[i], height[j]);
    			int currArea = width * minCurrHeight;
    			maxArea = Math.max(maxArea, currArea);
    		}
    	}
    	return maxArea;
    }
}
