package aq.gym.contests.sliding_window;

public class AlternatingGroups_I {

//	https://leetcode.com/problems/alternating-groups-i/
	public static void main(String[] args) {
//		int[] colors = {1,0,0};
//		int[] colors = {1,0,1};
//		int[] colors = {0,1,0};
//		int[] colors = {0,0,0};
		int[] colors = {0,1,0,0,1};
		System.out.println(new AlternatingGroups_I().numberOfAlternatingGroups(colors));
	}

    public int numberOfAlternatingGroups(int[] colors) {
    	int groupsCount = 0;
    	int left = 0, middle = 0, right = 0;
    	final int n = colors.length;
    	for(int i = 0; i < colors.length; i++) {
    		left = colors[i];
			middle = colors[(i + 1) % n];
			right = colors[(i + 2) % n];
    		if(left == right && middle != left && middle != right) {
    			groupsCount++;
    		}
    	}
    	return groupsCount;
    }
}
