package aq.gym.contests.easy.array;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {

	public static void main(String[] args) {
		List<List<Integer>> triangle = new PascalTriangle().generate(6);
		System.out.println(triangle);
	}

	public List<List<Integer>> generate(int numRows) {
		List<List<Integer>> triangle = new ArrayList<>();
		if(numRows == 1) {
			triangle.add(List.of(1));
		} else if(numRows == 2) {
			triangle.add(List.of(1));
			triangle.add(List.of(1, 1));
		} else {
			triangle.add(List.of(1));
			triangle.add(List.of(1, 1));
			for(int i = 1; i < numRows - 1; i++) {
				List<Integer> currLine = new ArrayList<>(triangle.get(i).size() + 1);
				List<Integer> prevLine = triangle.get(i);
				currLine.addFirst(1);
				for(int c = 1, p = 0; c < prevLine.size() + 1 && p < prevLine.size() - 1; c++, p++) {
					int val = prevLine.get(p) + prevLine.get(p + 1);
					currLine.add(val);
				}
				currLine.addLast(1);
				triangle.add(currLine);
			}
		}
		return triangle;
	}
}
