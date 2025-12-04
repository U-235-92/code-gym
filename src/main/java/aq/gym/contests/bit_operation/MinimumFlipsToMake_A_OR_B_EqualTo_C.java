package aq.gym.contests.bit_operation;

public class MinimumFlipsToMake_A_OR_B_EqualTo_C {

//	https://leetcode.com/problems/minimum-flips-to-make-a-or-b-equal-to-c/
	public static void main(String[] args) {
		int a = 2, b = 6, c = 5;
		System.out.println(new MinimumFlipsToMake_A_OR_B_EqualTo_C().minFlips(a, b, c));
	}

    public int minFlips(int a, int b, int c) {
    	int flipsNumber = 0;
        int[] aBinArr = toBinArray(a);
        int[] bBinArr = toBinArray(b);
        int[] cBinArr = toBinArray(c);
        for(int i = 31; i >= 0; i--) {
        	int aBit = aBinArr[i];
        	int bBit = bBinArr[i];
        	int cBit = cBinArr[i];
        	if(aBit == 1 && bBit == 1 && cBit == 0) {
        		flipsNumber += 2;
        	} else if(aBit == 1 && bBit == 0 && cBit == 0) {
        		flipsNumber++;
        	} else if(aBit == 0 && bBit == 1 && cBit == 0) {
        		flipsNumber++;
        	} else if(aBit == 0 && bBit == 0 && cBit == 1) {
        		flipsNumber++;
        	}
        }
        return flipsNumber;
    }
    
    private int[] toBinArray(int number) {
    	int[] arr = new int[32];
    	String numBinStr = Integer.toBinaryString(number);
    	for(int i = numBinStr.length() - 1, j = arr.length - 1; i >= 0; i--, j--) {
    		arr[j] = numBinStr.charAt(i) - 48;
    	}
    	return arr;
    }
}
