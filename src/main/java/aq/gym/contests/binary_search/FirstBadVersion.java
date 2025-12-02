package aq.gym.contests.binary_search;

public class FirstBadVersion {
	
	private int badVersion;
	
	public FirstBadVersion(int version) {
		this.badVersion = version;
	}
	
    public int firstBadVersion(int n) {
    	int left = 1, right = n, badVersion = 0;
    	while(left <= right) {
    		int version = left + (right - left) / 2;
    		if(isBadVersion(version)) {
    			badVersion = version;
    			right = version - 1;
    		} else {
    			left = version + 1;
    		}
    	}
    	return badVersion;
    }
    
    private boolean isBadVersion(int version) {
    	if(version < this.badVersion) {
    		return false;
    	}
    	return true;
    }
    
//  https://leetcode.com/problems/first-bad-version/description/
	public static void main(String[] args) {
		int badVersion = 1702766719, n = 2126753390;
		System.out.println(new FirstBadVersion(badVersion).firstBadVersion(n));
	}
}
