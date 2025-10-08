package aq.gym.contests.array;

import java.util.Arrays;

public class CanPlaceFlowers {

//	https://leetcode.com/problems/can-place-flowers/description
	public static void main(String[] args) {
		int[] flowerbed = {1,1};
		int n = 0;
		System.out.println(Arrays.toString(flowerbed));
		System.out.println(new CanPlaceFlowers().canPlaceFlowers(flowerbed, n));
		System.out.println(Arrays.toString(flowerbed));
	}

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
    	if(flowerbed.length == 1 && flowerbed[0] == 0 && n == 0) return true;
    	if(flowerbed.length == 1 && flowerbed[0] == 0 && n == 1) return true;
    	if(flowerbed.length == 1 && flowerbed[0] == 1 && n == 0) return true;
    	if(flowerbed.length == 1 && flowerbed[0] == 1 && n == 1) return false;
        for(int i = 0; i < flowerbed.length; i++) {
        	if(flowerbed[i] == 1) {
        		continue;
        	}
        	if(n == 0) {
        		break;
        	}
        	if(i == 0) {
        		if(flowerbed[i] == 0 && flowerbed[i + 1] == 0) {
        			flowerbed[i] = 1;
        			n--;
        		}
        	} else if(i == flowerbed.length - 1) {
        		if(flowerbed[i] == 0 && flowerbed[i - 1] == 0) {
        			flowerbed[i] = 1;
        			n--;
        		}
        	} else {
        		if(flowerbed[i] == 0 && flowerbed[i - 1] == 0 && flowerbed[i + 1] == 0) {
        			flowerbed[i] = 1;
        			n--;
        		}
        	}
        }
		return n == 0;
    }
}
