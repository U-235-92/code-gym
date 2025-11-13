package aq.gym.contests.string;

public class MaximumNumberOfOperationsToMoveOnesToTheEnd {

//	https://leetcode.com/problems/maximum-number-of-operations-to-move-ones-to-the-end/
	public static void main(String[] args) {
//		String s = "100";
//		String s = "010010";
//		String s = "1001101";
		String s = "0011010101100";
		System.out.println(new MaximumNumberOfOperationsToMoveOnesToTheEnd().maxOperations(s));
	}

	public int maxOperations(String s) {
        int operationsCount = 0, onesCount = 0;
        char[] chrs = s.toCharArray();
        for(int i = 0; i < chrs.length; i++) {
        	if(chrs[i] == '1') {
        		onesCount++;
        	} else {
        		if(i > 0 && chrs[i - 1] == '1') {
        			operationsCount += onesCount;
        		}
        	}
        }
        return operationsCount;
    }
}
