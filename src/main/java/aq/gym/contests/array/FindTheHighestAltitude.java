package aq.gym.contests.array;

public class FindTheHighestAltitude {

//	https://leetcode.com/problems/find-the-highest-altitude/
	public static void main(String[] args) {
		int[] gain = {-5,1,5,0,-7};
		System.out.println(new FindTheHighestAltitude().largestAltitude(gain));
	}

    public int largestAltitude(int[] gain) {
    	int highestAltitude = 0, currentAltitude = 0;
        for(int i = 0; i < gain.length; i++) {
        	int delta = gain[i];
        	currentAltitude += delta;
        	if(currentAltitude > highestAltitude) {
        		highestAltitude = currentAltitude;
        	}
        }
        return highestAltitude;
    }
}
