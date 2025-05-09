package aq.gym.contests.structures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LinkedListOperations {

	public static void main(String[] args) {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			List<Integer> list = new LinkedList<Integer>();
			int q = Integer.valueOf(br.readLine());
			for(int i = 0; i < q; i++) {
				int[] requestData = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::valueOf).toArray();
				int request = requestData[0];
				if(request == 1) {  //add
					int value = requestData[2];
					int position = requestData[1];
					list.add(position, value);
				} else if(request == 2) { //print
					int position = requestData[1] - 1;
					System.out.println(list.get(position));
				} else if(request == 3) { //remove
					int position = requestData[1] - 1;
					list.remove(position);
				}
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
