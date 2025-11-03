package aq.gym.contests.array;

public class MinimumTimeToMakeRopeColorful {

//	https://leetcode.com/problems/minimum-time-to-make-rope-colorful/
	public static void main(String[] args) {
//		String colors = "abaac";
//		int[] neededTime = {1,2,3,4,5}; 
//		String colors = "abc";
//		int[] neededTime = {1,2,3};
//		String colors = "aabaa";
//		int[] neededTime = {1,2,3,4,1};
//		String colors = "aaabbbcccddd";
//		int[] neededTime = {1,10000,5000,2,9999,3,8888,4,7777,5,6666,6};
//		String colors = "abcabc";
//		int[] neededTime = {1,2,3,4,5,6};
//		String colors = "merrychristmashohohooooh";
//		int[] neededTime = {167,59,671,318,342,332,950,609,657,650,419,334,214,96,793,176,476,847,141,404,320,760,243,929};
		String colors = "ccc";
		int[] neededTime = {3,2,5};
		System.out.println(new MinimumTimeToMakeRopeColorful().minCost(colors, neededTime));
	}
	
    public int minCost(String colors, int[] neededTime) {
        return getMinCost(colors, neededTime);
    }
    
    private int getMinCost(String colorsString, int[] neededTime) {
    	int totalMinCostTime = 0;
    	String[] colors = colorsString.split("");
    	for(int i = 0; i < colors.length - 1; i++) {
    		String currBallon = colors[i], nextBallon = colors[i + 1];
    		int currNeedTime = neededTime[i], nextNeedTime = neededTime[i + 1];
    		if(currBallon.equals(nextBallon)) {    			
    			totalMinCostTime += Math.min(currNeedTime, nextNeedTime);
    			neededTime[i + 1] = Math.max(currNeedTime, nextNeedTime);
    		}
    	}
    	return totalMinCostTime;
    }
}
