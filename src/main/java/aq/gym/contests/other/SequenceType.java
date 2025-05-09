package aq.gym.contests.other;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SequenceType {

	private static final int CONSTANT = 1;
	private static final int ASCENDING = 2;
	private static final int WEAKLY_ASCEDING = 3;
	private static final int DESCENDING = 4;
	private static final int WEAKLY_DESCENDING = 5;
	private static final int RANDOM = 6;
	private static final long END_OF_READ = (int) (-2 * Math.pow(10, 9));
	
//	5 5 5 5 5 -2000000000
//	5 5 5 5 5 1 
//	-5 -5 -5 -5 -5
//	-5 -5 -5 -5 -5 -1 
//	1 2 3 4 5
//	-1 -2 -3 -4 -5 
//	5 4 3 2 1 
//	-5 -4 -3 -2 -1
//	5 5 4 3 2 2 1 
//	1 1 2 3 3 4 5
//	1 2 3 1 5 6 5 4
	public static void main(String[] args) {
		getSequenceTypeLineWay();
	}

	private static void getSequenceTypeLineWay() {
		Scanner scanner = new Scanner(System.in);
		long prev = Long.valueOf(scanner.nextLine());
		if(prev == END_OF_READ) {
			System.out.println("RANDOM");
			scanner.close();
			return;
		} else {
			boolean isConst = true, isAsc = true, isDesc = true, isWAsc = true, isWDesc = true;
			long next = 0;
			while((next = scanner.nextLong()) != END_OF_READ) {
				if(next != prev) {
					isConst = false;
				} 
				if(next >= prev) {
					isDesc = false;
				}
				if(next <= prev) {
					isAsc = false;
				}
				if(next > prev) {
					isWDesc = false;
				}
				if(next < prev) {
					isWAsc = false;
				}
				prev = next;
			}
			if(isConst) {
				System.out.println("CONSTANT");
			} else if(isAsc) {
				System.out.println("ASCENDING");
			} else if(isDesc) {
				System.out.println("DESCENDING");
			} else if(isWAsc) {
				System.out.println("WEAKLY ASCENDING");
			} else if(isWDesc) {
				System.out.println("WEAKLY DESCENDING");
			} else {
				System.out.println("RANDOM");
			}
			scanner.close();
		}
	}
	
	@SuppressWarnings("unused")
	private static void getSequenceTypeListWay() {
		Scanner scanner = new Scanner(System.in);
		List<Long> nums = new ArrayList<>();
		while(true) {
			long num = Long.valueOf(scanner.nextLine());
			if(num == END_OF_READ) {
				scanner.close();
				break;
			} else {
				nums.add(num);
			}
		}
		int type = -1;
		boolean isConst = true, isAsc = false, isDesc = false, isWAsc = false, isWDesc = false, isRnd = false;
		if(nums.size() == 0) {
			type = RANDOM;
		}
		if(nums.size() == 1) {
			type = CONSTANT;
		} else {
			for(int i = 0; i < nums.size() - 1; i++) {
				if(isConst) {
					 if(nums.get(i + 1) > nums.get(i)) {
						 if(i == 0) {
							 type = ASCENDING;
							 isConst = false;
							 isAsc = true; 
						 } else {
							 type = WEAKLY_ASCEDING;
							 isConst = false;
							 isWAsc = true;
						 }
					 } else if(nums.get(i + 1) < nums.get(i)) {
						 if(i == 0) {
							 type = DESCENDING;
							 isConst = false;
							 isDesc = true;
						 } else {
							 type = WEAKLY_DESCENDING;
							 isConst = false;
							 isWDesc = true; 
						 }
					 }
				} else if(isAsc) {
					if(nums.get(i) == nums.get(i + 1)) {
						 type = WEAKLY_ASCEDING;
						 isAsc = false;
						 isWAsc = true;
					 } else if(nums.get(i + 1) < nums.get(i)) {
						 type = RANDOM;
						 isRnd = true;
						 break;
					 }
				} else if(isDesc) {
					if(nums.get(i) == nums.get(i + 1)) {
						 type = WEAKLY_DESCENDING;
						 isDesc = false;
						 isWDesc = true;
					 } else if(nums.get(i + 1) > nums.get(i)) {
						 type = RANDOM;
						 isRnd = true;
						 break;
					 }
				} else if(isWAsc) {
					if(nums.get(i + 1) < nums.get(i)) {
						type = RANDOM;
						isRnd = true;
						break;
					}
				} else if(isWDesc) {
					if(nums.get(i + 1) > nums.get(i)) {
						type = RANDOM;
						isRnd = true;
						break;
					}
				} else if(isRnd) {
					type = RANDOM;
					break;
				}
			}
		}
		switch (type) {
		case CONSTANT:
			System.out.println("CONSTANT");
			break;
		case ASCENDING:
			System.out.println("ASCENDING");
			break;
		case WEAKLY_ASCEDING:
			System.out.println("WEAKLY ASCENDING");
			break;
		case DESCENDING:
			System.out.println("DESCENDING");
			break;
		case WEAKLY_DESCENDING:
			System.out.println("WEAKLY DESCENDING");
			break;
		case RANDOM:
			System.out.println("RANDOM");
			break;
		}
	}
}
