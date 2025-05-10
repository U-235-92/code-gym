package aq.gym.contests.array;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle_II {

	public static void main(String[] args) {
		int rowIndex = 5;
		List<Integer> row = new PascalTriangle_II().getRow(rowIndex);
		System.out.println(row);
	}

	public List<Integer> getRow(int rowIndex) {
		if(rowIndex == 0) {
			return List.of(1);
		} else if(rowIndex == 1) {
			return List.of(1, 1);
		} else {
			List<Integer> prev = List.of(1, 1);
			List<Integer> curr = new ArrayList<>(prev.size() + 1);
			int currIndex = 1;
			while(currIndex != rowIndex) {
				curr.add(1);
				for(int i = 0; i < prev.size() - 1; i++) {
					int val = prev.get(i) + prev.get(i + 1);
					curr.add(val);
				}
				curr.add(1);
				prev = curr;
				curr = new ArrayList<>(prev.size() + 1);
				currIndex++;
			}
			return prev;
		}
	}
}
