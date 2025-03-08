package aq.gym.contests.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SymmetrySequence {

//	2 3 6 3 0 3 8 8 3 8
//	0 8 5 8
//	1 7 4 1 
//	1 2 3 4 5 4 3 2 1
//	1 2 3 4 5
//	1 2 1 2 2
//	1
//	1 2 1 2 1 2 2 1
//	1 1 1 1 1 8 1 1 1 
//	1 1 1 2 8 1 
//	1 1 1 1 8 1
//	1 1 1 2 3 8 1
//	5 4 3 2 1
//	1 2 3 4 3 2 2
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = Integer.valueOf(scanner.nextLine());
		if(n < 1 || n > 100) {
			scanner.close();
			throw new IllegalArgumentException();
		}
		String string = scanner.nextLine();
		int[] nums = Arrays.stream(string.split("\\s")).mapToInt(Integer::valueOf).toArray();
		for(int num : nums) {
			if(num >= 10 || num <= 0) {
				scanner.close();
				throw new IllegalArgumentException();
			}
		}
		scanner.close();
//		String string = "1 2 3 4 3 2 2";
//		int[] nums = Arrays.stream(string.split("\\s")).mapToInt(Integer::valueOf).toArray();
		List<Integer> symmetry = getSymmetryWayTwo(nums);
		String result = symmetry.stream().map(String::valueOf).collect(Collectors.joining(" "));
		System.out.println(symmetry.size());
		System.out.println(result);
	}
	
	private static List<Integer> getSymmetryWayTwo(int[] nums) {
		int start = 0;
		for(int i = 0; i < nums.length; i++) {
			if(isSymmetry(nums, i)) {
				start = i;
				break;
			}
		}
		List<Integer> result = new ArrayList<>();
		for(int i = start - 1; i >= 0; i--) {
			result.add(nums[i]);
		}
		return result;
	}
	
	private static boolean isSymmetry(int[] nums, int idx) {
		int left = idx, right = nums.length - 1;
		while(right >= left) {
			if(nums[left] != nums[right]) {
				return false;
			}
			left++; right--;
		}
		return true;
	}
	
	@SuppressWarnings("unused")
	private static List<Integer> getSymmetryWayOne(int[] nums) {
		int leftBorder = 0, rightBorder = 0, idxAddHeadSymetryNum = 0;
		boolean isSymmetryDefine = false;
		boolean isAddedMirrorNumbers = false;
		boolean isAddedHeadSymmetryNumber = false;
		List<Integer> additionSymetryNumsStorage = new ArrayList<>();
		List<Integer> mirrorNumsStorage = new ArrayList<>();
		List<Integer> borderNumsStorage = new ArrayList<>();
		List<Integer> symmetry = new ArrayList<>();
//		Stage ONE check symmetry or define leftBorder
		for(int i = 0, j = nums.length - 1; i < nums.length && j >= 0; ) {
			if(nums[i] == nums[j]) {
				mirrorNumsStorage.add(nums[i]);
				i++; j--;
			} else {
				leftBorder = i;
				break;
			}
			if(i >= j) {
				return symmetry;
			}
		}
//		Stage TWO define rightBorder
		for(int i = nums.length - 1; i > 0; i--) {
			if(nums[i] != nums[i - 1]) {
				rightBorder = i - 1;
				break;
			}
		}
//		Fill border numbers storage
		for(int i = leftBorder; i <= rightBorder; i++) {
			borderNumsStorage.add(nums[i]);
		}
//		Compare numbers from [borderNumbers] with [nums]
		int compareCount = borderNumsStorage.size();
		for(int i = 0, j = 0; i < borderNumsStorage.size() && j < nums.length; j++) {
			if(compareCount > 0) {
				if(borderNumsStorage.get(i) == nums[j]) {
					additionSymetryNumsStorage.add(borderNumsStorage.get(i));
					i++; 
				}
			} else {
				break;
			}
			compareCount--;
		}
//		Add in symmetry all the numbers from border
		for(int i = additionSymetryNumsStorage.size() - 1; i >= 0; i--) {
			symmetry.add(additionSymetryNumsStorage.get(i));
		}
//		Define symmetry
		while(!isSymmetryDefine) {
			for(int i = 0, j = symmetry.size() - 1; i < nums.length && j >= 0; i++, j--) {
				if(nums[i] != symmetry.get(j)) {
					if(!isAddedMirrorNumbers) {
						for(int k = mirrorNumsStorage.size() - 1; k >= 0; k--) {
							symmetry.add(mirrorNumsStorage.get(k));
						}
						isAddedMirrorNumbers = true;
						break;
					}
				} else {
					if(j == 0) {
						idxAddHeadSymetryNum = i + 1;
						for(int m = idxAddHeadSymetryNum, p = nums.length - 1; m < nums.length; m++, p--) {
							if(nums[m] != nums[p]) {
								if(!isAddedHeadSymmetryNumber) {
									symmetry.addFirst(nums[m]);
									isAddedHeadSymmetryNumber = true;
								}
							} else {
								if(m >= p) {
									isSymmetryDefine = true;
									break;
								}
							}
						}
					}
				}
			}
		}
		return symmetry;
	}
}
