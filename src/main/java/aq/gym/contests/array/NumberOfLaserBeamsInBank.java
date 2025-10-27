package aq.gym.contests.array;

import java.util.Arrays;

public class NumberOfLaserBeamsInBank {

//	https://leetcode.com/problems/number-of-laser-beams-in-a-bank/
	public static void main(String[] args) {
		String[] bank = {"011001","000000","010100","001000"};
		System.out.println(new NumberOfLaserBeamsInBank().numberOfBeams(bank));
	}

    public int numberOfBeams(String[] bank) {
    	int numOfBeans = 0;
        for(int i = 0; i < bank.length; i++) {
        	int currLineDeviceNumber = getCurentLineDeviceNumber(bank[i]);
        	if(hasDevices(currLineDeviceNumber)) {
        		for(int j = i + 1; j < bank.length; j++) {
        			int nextLineDeviceNumber = getCurentLineDeviceNumber(bank[j]);
        			if(hasDevices(nextLineDeviceNumber)) {
        				numOfBeans += currLineDeviceNumber * nextLineDeviceNumber;
        				i = j - 1;
        				break;
        			}
        		}
        	}
        }
        return numOfBeans;
    }
    
    private int getCurentLineDeviceNumber(String line) {
    	int num = Arrays.stream(line.split("")).mapToInt(Integer::valueOf).sum();
    	return num;
    }
    
    private boolean hasDevices(int number) {
    	return number > 0;
    }
}
