package aq.gym.contests.algorithms.structures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class StackOperations {

	public static void main(String[] args) {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			int q = Integer.valueOf(br.readLine());
			Deque<Integer> stack = new LinkedList<Integer>();
			for(int i = 0; i < q; i++) {
				int[] data = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::valueOf).toArray();
				int request = data[0];
				if(request == 1) {
					int value = data[1];
					stack.push(value);
					System.out.println(stack.peekFirst());
				} else if(request == 2) {
					stack.pop();
					if(stack.size() > 0) {						
						System.out.println(stack.peekFirst());
					} else {
						System.out.println(-1);
					}
				}
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
