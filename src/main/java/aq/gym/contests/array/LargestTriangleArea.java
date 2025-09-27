package aq.gym.contests.array;

public class LargestTriangleArea {

	public static void main(String[] args) {
//		int[][] points = { {0,0}, {0,1}, {1,0}, {0,2}, {2,0} };
		int[][] points = {{-35,19},{40,19},{27,-20},{35,-3},{44,20},{22,-21},{35,33},{-19,42},{11,47},{11,37}};
		System.out.println(new LargestTriangleArea().largestTriangleArea(points));
	}

    public double largestTriangleArea(int[][] points) {
        return bruteForceApproach(points);
    }
    
    private double bruteForceApproach(int[][] points) {
    	double result = 0.0;
    	for(int i = 0; i < points.length; i++) {
    		for(int j = i + 1; j < points.length; j++) {
    			for(int k = j + 1; k < points.length; k++) {
    				double area = area(points[i], points[j], points[k]);
    				result = Math.max(area, result);
    			}
    		}
    	}
    	return result;
    }
    
    public double area(int[] P, int[] Q, int[] R) {
        return 0.5 * Math.abs(P[0]*Q[1] + Q[0]*R[1] + R[0]*P[1]
                             -P[1]*Q[0] - Q[1]*R[0] - R[1]*P[0]);
    }
}
