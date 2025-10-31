package aq.gym.contests.queue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.IntStream;

public class MinimumNumberOfIncrementsOnSubarraysToFormTargetArray {

//	https://leetcode.com/problems/minimum-number-of-increments-on-subarrays-to-form-a-target-array/description
	public static void main(String[] args) {
//		int[] target = {1,2,3,2,1}; // 3
//		int[] target = {3,1,1,2}; // 4
//		int[] target = {3,1,5,4,2}; // 7
//		int[] target = {1,1,5,1,9}; // 13
		int[] target = {3,4,1,1,2,6,6,4,4,5,3}; // 10
//		int[] target = {4,4}; // 4
//		int[] target = {4}; // 4
		System.out.println(new MinimumNumberOfIncrementsOnSubarraysToFormTargetArray().minNumberOperations(target));
//		test();
	}

	@SuppressWarnings("unused")
	private static void test() {
//		int n = (int) Math.pow(10, 5);
//		int limit = (int) Math.pow(10, 5);
//		int[] target = IntStream.generate(() -> 1 + (int) (Math.random() * (n - 1) + 1)).limit(limit).toArray();
//		System.out.println(new MinimumNumberOfIncrementsOnSubarraysToFormTargetArray().minNumberOperations(target));
		while(true) {
			int n = (int) Math.pow(10, 5);
			int limit = (int) Math.pow(10, 2);
			int[] target = IntStream.generate(() -> 1 + (int) (Math.random() * (n - 1) + 1)).limit(limit).toArray();
			int recursive = new MinimumNumberOfIncrementsOnSubarraysToFormTargetArray().minNumberOperationsRecursiveWay(target);
			int bruteforce = new MinimumNumberOfIncrementsOnSubarraysToFormTargetArray().minNumberOperationsBruteForceWay(target);
			if(recursive == bruteforce) {
				System.out.println("OK");
			} else {
				System.out.println("W/A");
				System.out.printf("BruteForce: %d, Recursive: %d%n", bruteforce, recursive);
				System.out.println(Arrays.toString(target));
				return;
			}
		}
	}
	
    public int minNumberOperations(int[] target) {
    	return minNumbersOperationsTrickyLinearWay(target);
    }
    
    private int minNumbersOperationsTrickyLinearWay(int[] target) {
    	if(target.length == 1) {
    		return target[0];
    	} else {
    		int result = target[0];
    		for(int i = 1; i < target.length; i++) {
    			result += Math.max(target[i] - target[i - 1], 0);
    		}
    		return result;
    	}
    }
    
    public int minNumberOperationsRecursiveWay(int[] target) {
    	int[] accumulator = {0};
    	Queue<List<Integer>> diaposones = new LinkedList<>();
    	diaposones.add(new ArrayList<Integer>(Arrays.stream(target).boxed().toList()));
    	evaluateRecursive(diaposones, accumulator);
    	return accumulator[0];
    }
    
    private void evaluateRecursive(Queue<List<Integer>> diaposones, int[] accumulator) {
    	while(!diaposones.isEmpty()) {
    		List<Integer> tmp = new ArrayList<>(diaposones.peek()); 
    		Collections.sort(tmp);
    		int min = tmp.getFirst();    		
    		List<Integer> diaposone = diaposones.poll().stream().map(num -> num - min).toList();
			if(diaposone.size() == 1 && diaposone.get(0) != 0) {
				diaposones.add(diaposone);
			} else {
				int end = 0, start = 0;
				int curr = 0, next = 0;
				for(int i = 0; i < diaposone.size() - 1; i++) {
					curr = diaposone.get(i);
					next = diaposone.get(i + 1);
					if(curr != 0 && next == 0) {
						end = i + 1;
						if(end - start > 0) {						
							List<Integer> subDiaposone = diaposone.subList(start, end);
							diaposones.offer(subDiaposone);
						}
					} else if(curr == 0 && next != 0) {
						start = i + 1;
					} 
				}
				if(next != 0) {
					end = diaposone.size();
					List<Integer> subDiaposone = diaposone.subList(start, end);
					diaposones.offer(subDiaposone);
				}
			}
			accumulator[0] += min;
    	}
    }

    public int minNumberOperationsBruteForceWay(int[] target) {
    	int[] accumulator = {0};
    	evaluateBruteForce(target, accumulator);
    	return accumulator[0];
    }
    
	private void evaluateBruteForce(int[] target, int[] accumulator) {
		int totalWeight = getTotalWeight(target);
		boolean isZeroPicked = false, isNoZeroPicked = false;
		Deque<Integer> freeStack = new ArrayDeque<>();
		Deque<Integer> fillStack = new ArrayDeque<>();
		while(totalWeight > 0) {
			int prevAccumulatorValue = accumulator[0];
			if(fillStack.isEmpty() && freeStack.isEmpty()) {
				int idx = 0;
				while(idx < target.length) {
					int val = target[idx];
					if(val == 0) {
						if(isNoZeroPicked) {
							isZeroPicked = true; 
						}
					} else {
						isNoZeroPicked = true;
						if(isDiapasoneAchieved(isZeroPicked, isNoZeroPicked)) {
							if(accumulator[0] == prevAccumulatorValue) {								
								accumulator[0]++;
							}
						} else {
							val--;
							totalWeight--;
						}
					}
					fillStack.push(val);
					idx++;
				}
				if(accumulator[0] == prevAccumulatorValue) {								
					accumulator[0]++;
				}
				isZeroPicked = false; isNoZeroPicked = false;
			} else if(!fillStack.isEmpty() && freeStack.isEmpty()) {
				while(!fillStack.isEmpty()) {
					int val = fillStack.pop();
					if(val == 0) {
						if(isNoZeroPicked) {
							isZeroPicked = true; 
						}
					} else {
						isNoZeroPicked = true;
						if(isDiapasoneAchieved(isZeroPicked, isNoZeroPicked)) {
							if(accumulator[0] == prevAccumulatorValue) {								
								accumulator[0]++;
							}
						} else {
							val--;
							totalWeight--;
						}
					}
					freeStack.push(val);
				}
				if(accumulator[0] == prevAccumulatorValue) {								
					accumulator[0]++;
				}
				isZeroPicked = false; isNoZeroPicked = false;
			} else {
				while(!freeStack.isEmpty()) {
					int val = freeStack.pop();
					if(val == 0) {
						if(isNoZeroPicked) {
							isZeroPicked = true; 
						}
					} else {
						isNoZeroPicked = true;
						if(isDiapasoneAchieved(isZeroPicked, isNoZeroPicked)) {
							if(accumulator[0] == prevAccumulatorValue) {								
								accumulator[0]++;
							}
						} else {
							val--;
							totalWeight--;
						}
					}
					fillStack.push(val);
				}
				if(accumulator[0] == prevAccumulatorValue) {								
					accumulator[0]++;
				}
				isZeroPicked = false; isNoZeroPicked = false;
			}
		}
	}
	
	private int getTotalWeight(int[] target) {
		int total = 0;
		for(int i = 0; i < target.length; i++) {
			total += target[i];
		}
		return total;
	}
	
	private boolean isDiapasoneAchieved(boolean isZeroPicked, boolean isNoZeroPicked) {
		return isZeroPicked && isNoZeroPicked;
	}
}
