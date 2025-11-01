package aq.gym.contests.bit_operation;

public class ReverseBits {

//	https://leetcode.com/problems/reverse-bits/description/
	public static void main(String[] args) {
//		int n = 43261596;
//		int n = 2147483644;
		int n = Integer.MAX_VALUE - 1;
		System.out.println(n);
		System.out.println(new ReverseBits().reverseBits(n));
	}

    public int reverseBits(int n) {
    	char[] nBitsArray = Integer.toBinaryString(n).toCharArray();
    	char[] rBitsArray = reverse(nBitsArray, 0, nBitsArray.length - 1);
    	int result = getReversBitsNumber(rBitsArray);
    	return result;
    }
    
    private char[] reverse(char[] bitArray, int left, int right) {
    	if(left >= right) {
    		char[] revArray = new char[32];
    		System.arraycopy(bitArray, 0, revArray, 0, bitArray.length);
    		if(bitArray.length < revArray.length) {
    			for(int i = bitArray.length; i < revArray.length; i++) {
    				revArray[i] = '0';
    			}
    		}
    		return revArray;
    	}
    	char tmp = bitArray[left];
    	bitArray[left] = bitArray[right];
    	bitArray[right] = tmp;
    	return reverse(bitArray, left + 1, right - 1);
    }
    
    private int getReversBitsNumber(char[] rBitsArray) {
    	int number = 0, digit = rBitsArray.length - 1;
    	for(char ch : rBitsArray) {
    		byte bit = Byte.valueOf(ch + "");
    		number += (int) Math.pow(2, digit) * bit;
    		digit--;
    	}
    	return number;
    }
}
