package aq.gym.contests.string;

import java.util.Arrays;

public class StringCompression {

//	https://leetcode.com/problems/string-compression
	public static void main(String[] args) {
//		char[] chars = {'a','a','b','b','c','c','c'};
//		char[] chars = {'a','a','b'};
//		char[] chars = {'a','b','b'};
//		char[] chars = {'a','b','b','b','b','b','b','b','b','b','b','b','b'};
//		char[] chars = {'a','b','b','b','b','b','b','b','b','b','b','b','b','c'};
//		char[] chars = {'a'};
//		char[] chars = {'a','b'};
//		char[] chars = {'d','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','a','c'};
//		char[] chars = {'a','a','a','a','b','a','b','a','a','a'};
//		char[] chars = {'a','a','a','b','b','a','a'};
//		char[] chars = {'x','a','a','a','b','b','a','a','b','b','y'};
		char[] chars = {'l','e','e','t','c','o','d','e'};
		System.out.println(Arrays.toString(chars));
		System.out.println(new StringCompression().compress(chars));
		System.out.println(Arrays.toString(chars));
	}

    public int compress(char[] chars) {
        int j = -1, length = 0, count = 1;
        char curr = '\u0000', next = '\u0000';
        for(int i = 0; i < chars.length - 1; i++) {
        	curr = chars[i];
        	next = chars[i + 1];
        	if(curr == next) {
        		if(count == 1 && j == -1) {
        			j++;
        		}
        		count++;
        	} else {
        		if(count != 1 && count < 10) {
        			chars[j] = curr;
        			j++;
        			chars[j] = Character.forDigit(count, 10);
        			j++; count = 1;
        		} else if(count != 1 && count >= 10) {
        			chars[j] = curr;
        			j++;
        			char[] nums = (count + "").toCharArray();
        			for(char num : nums) {
        				chars[j] = num;
        				j++;
        			}
        			count = 1;
        		} else if(count == 1) {
        			if(j == -1) {
        				j++;
        			}
        			chars[j] = curr;
        			j++;
        		}
        	}
        }
        if(count != 1 && count < 10) {
        	chars[j] = next;
        	j++;
        	chars[j] = Character.forDigit(count, 10);
        	j++;
        } else if(count != 1 && count >= 10) {
        	chars[j] = next;
        	j++;
			char[] nums = (count + "").toCharArray();
			for(char num : nums) {
				chars[j] = num;
				j++;
			}
		} else if(count == 1) {
			if(j == -1) {
				j++;
			}
			chars[j] = (next == '\u0000') ? chars[j] : next;
			j++;
		}
        length = j;
        return length;
    }
}
