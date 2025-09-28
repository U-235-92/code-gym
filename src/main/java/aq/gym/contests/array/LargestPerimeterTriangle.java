package aq.gym.contests.array;

import java.util.Arrays;

public class LargestPerimeterTriangle {

//	https://leetcode.com/problems/largest-perimeter-triangle/description
	public static void main(String[] args) {
		int[] nums = {2,1,2};
		System.out.println(new LargestPerimeterTriangle().largestPerimeter(nums));
	}

    public int largestPerimeter(int[] A) {
        Arrays.sort(A);
        for (int i = A.length - 3; i >= 0; --i)
            if (A[i] + A[i+1] > A[i+2])
                return A[i] + A[i+1] + A[i+2];
        return 0;
    }
}
