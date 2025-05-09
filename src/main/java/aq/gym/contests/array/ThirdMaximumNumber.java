package aq.gym.contests.array;

import java.util.Arrays;
import java.util.TreeSet;
import java.util.stream.IntStream;

public class ThirdMaximumNumber {

	public static void main(String[] args) {
//		int[] arr = {2,2,3,5,10,0,1};
//		int[] arr = {2,2,3,1};
//		int[] arr = {5,10,0};
//		int[] arr = {1,1,2};
//		int[] arr = {1,2,-2147483648};
//		int[] arr = {3, 3, 5, 3};
//		int thirdMax = new ThirdMaximumNumber().thirdMaxByThreeIndexes(arr);
//		System.out.println(thirdMax);
		new ThirdMaximumNumber().test();
	}

	private void test() {
		while(true) {
			int[] nums = IntStream.generate(() -> (int)(Math.random() * 11)).limit(100).toArray();
			int tree = thirdMaxByTreeSet(nums);
			int idxs = thirdMaxByThreeIndexes(nums);
			if(tree == idxs) {
				System.out.println("OK");
			} else {
				System.out.println("W/A");
				System.out.println("idxs = " + idxs + "; tree = " + tree);
				System.out.println(Arrays.toString(nums));
				break;
			}
		}
	}
	
	public int thirdMaxByTreeSet(int[] nums) {
		if(nums.length <= 2) {
			long max = Long.MIN_VALUE;
			for(int num : nums) {
				if(num > max) {
					max = num;
				}
			}
			return (int) max;
		} else {
			TreeSet<Integer> treeSet = new TreeSet<Integer>();
			for(int num : nums) {
				if(treeSet.contains(num)) {
					continue;
				}
				if(treeSet.size() < 3) {
					treeSet.add(num);
				} else {
					if(treeSet.getFirst() < num) {
						treeSet.removeFirst();
						treeSet.add(num);
					}
				}
			}
			return (treeSet.size() >= 3) ? treeSet.getFirst() : treeSet.getLast();
		}
	}
	
	public int thirdMaxByThreeIndexes(int[] nums) {
		if(nums.length <= 2) {
			long max = Long.MIN_VALUE;
			for(int num : nums) {
				if(num > max) {
					max = num;
				}
			}
			return (int) max;
		} else {
			long firstMax = Long.MIN_VALUE, secondMax = Long.MIN_VALUE, thirdMax = Long.MIN_VALUE;
			for(int i = 0; i < nums.length; i++) {
				int current = nums[i];
				if(current == firstMax || current == secondMax || current == thirdMax) {
					continue;
				}
				if(current >= firstMax) {
					thirdMax = secondMax;
					secondMax = firstMax;
					firstMax = current;
				} else if(current >= secondMax) {
					thirdMax = secondMax;
					secondMax = current;
				} else if(current >= thirdMax) {
					thirdMax = current;
				}
			}
			if(thirdMax == Long.MIN_VALUE) {
				thirdMax = firstMax;
			}
			return (int) thirdMax;
		} 
    }
}
