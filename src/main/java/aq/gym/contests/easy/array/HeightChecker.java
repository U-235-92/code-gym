package aq.gym.contests.easy.array;

import java.util.Arrays;

public class HeightChecker {

	public static void main(String[] args) {
		int[] arr = {1,1,4,2,1,3};
		int indexesNumber = new HeightChecker().heightChecker(arr);
		System.out.println(indexesNumber);
	}

	public int heightChecker(int[] heights) {
		int indexesNumber = 0;
        int[] sorted = Arrays.copyOf(heights, heights.length);
        Arrays.sort(sorted);
        for(int i = 0; i < sorted.length; i++) {
        	if(sorted[i] != heights[i])
        		indexesNumber++;
        }
        return indexesNumber;
    }
}
