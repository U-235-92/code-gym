package aq.gym.contests.array;

import java.util.Arrays;
import java.util.stream.IntStream;

public class RightGreatestElementReplacer {

	public static void main(String[] args) {
//		int[] arr = {17};
//		int[] arr = {5,8};
//		int[] arr = {17,18,5,4,6,1};
//		new RightGreatestElementReplacer().replaceElementsLinear(arr);
//		System.out.println(Arrays.toString(arr));
		new RightGreatestElementReplacer().test();
	}

	public int[] replaceElementsBrutforce(int[] arr) {
		if(arr.length == 1) {
			arr[0] = -1;
			return arr;
		} else if(arr.length == 2) {
			arr[0] = arr[1];
			arr[1] = -1;
			return arr;
		} else {			
			for(int i = 0; i < arr.length - 1; i++) {
				int max = 0;
				for(int j = i + 1; j < arr.length; j++) {
					if(arr[j] > max) {
						max = arr[j];
					}
				}
				arr[i] = max;
			}
			arr[arr.length - 1] = -1;
			return arr;
		}
    }
	
	public int[] replaceElementsLinear(int[] arr) {
		if(arr.length == 1) {
			arr[0] = -1;
			return arr;
		} else if(arr.length == 2) {
			arr[0] = arr[1];
			arr[1] = -1;
			return arr;
		} else {		
			int curMax = arr[arr.length - 1];
			arr[arr.length - 1] = -1;
			for(int i = arr.length - 2; i >= 0; i--) {
				if(arr[i] > curMax) {
					int tmp = curMax;
					curMax = arr[i];
					arr[i] = tmp;
				} else {
					arr[i] = curMax;
					curMax = arr[i];
				}
			}
			return arr;
		}
    }
	
	public void test() {
		while(true) {
			int[] orig = IntStream.generate(() -> Math.abs((int) (Math.random() * 10))).limit(10).toArray();
			int[] copy1 = Arrays.copyOf(orig, orig.length);
			int[] copy2 = Arrays.copyOf(orig, orig.length);
			replaceElementsBrutforce(copy2);
			replaceElementsLinear(copy1);
			if(Arrays.equals(copy1, copy2)) {
				System.out.println("OK");
			} else {
				System.out.println("W/A");
				System.out.println("Orig:       " + Arrays.toString(orig));
				System.out.println("Linear:     " + Arrays.toString(copy1));
				System.out.println("Bruteforce: " + Arrays.toString(copy2));
				return;
			}
		}
	}
}
