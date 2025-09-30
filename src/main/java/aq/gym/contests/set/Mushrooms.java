package aq.gym.contests.set;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.TreeMap;

public class Mushrooms {

//	https://contest.yandex.ru/contest/80939/problems/
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int number = Integer.valueOf(reader.readLine());
		int[] mushrooms = Arrays.stream(reader.readLine().split("\s")).mapToInt(Integer::valueOf).toArray(); 
		TreeMap<Integer, Integer> wMushrooms = new TreeMap<>();
		TreeMap<Integer, Integer> mMushrooms = new TreeMap<>();
		for(int i = 1; i <= number; i++) {
			int mushroom = mushrooms[i - 1];
			if(i % 2 == 0) {
				mMushrooms.compute(mushroom, (k, v) -> {
					if(v == null)
						return 1;
					else
						return v + 1;
				});
			} else {
				wMushrooms.compute(mushroom, (k, v) -> {
					if(v == null)
						return 1;
					else
						return v + 1;
				});
			}
		}
		changeMushrooms(wMushrooms, mMushrooms);
		
	}
	
	private static void changeMushrooms(TreeMap<Integer, Integer> wMushrooms, TreeMap<Integer, Integer> mMushrooms) {
		int wMinMushroomKey = wMushrooms.firstKey(); 
		int wMinMushroomValue = wMushrooms.get(wMinMushroomKey);
		int mMaxMushroomKey = mMushrooms.lastKey(); 
		int mMaxMushroomValue = mMushrooms.get(mMaxMushroomKey);
		if(mMaxMushroomKey > wMinMushroomKey) {
			wMushrooms.compute(mMaxMushroomKey, (k, v) -> {
				if(v == null)
					return 1;
				else
					return v + 1;
					
			});
			if(wMinMushroomValue == 1) {
				wMushrooms.remove(wMinMushroomKey);
			} else {
				wMushrooms.put(wMinMushroomKey, wMinMushroomValue - 1);
			}
			mMushrooms.compute(wMinMushroomKey, (k, v) -> {
				if(v == null)
					return 1;
				else
					return v + 1;
					
			});
			if(mMaxMushroomValue == 1) {
				mMushrooms.remove(mMaxMushroomKey);
			} else {
				mMushrooms.put(mMaxMushroomKey, mMaxMushroomValue - 1);
			}
		}
		int wTotalWeight = wMushrooms.entrySet().stream().mapToInt(e -> Integer.valueOf(e.getKey() * e.getValue())).sum();
		int mTotalWeight = mMushrooms.entrySet().stream().mapToInt(e -> Integer.valueOf(e.getKey() * e.getValue())).sum();
		int wHappiness = wTotalWeight - mTotalWeight;
		System.out.println(wHappiness);
	}
}
