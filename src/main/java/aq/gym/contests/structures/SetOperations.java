package aq.gym.contests.structures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetOperations {

	public static void main(String[] args) {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			Set<Integer> set = new HashSet<Integer>();
			List<Integer> result = new ArrayList<Integer>();
			int q = Integer.valueOf(br.readLine());
			for(int i = 0; i < q; i++) {
				int[] requestData = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::valueOf).toArray();
				int request = requestData[0];
				if(request == 1) { //add
					set.add(requestData[1]);
				} else if(request == 2) { //check 
					if(set.contains(requestData[1])) { 
						result.add(1);
					} else {
						result.add(0);
					}
				}
			}
			result.stream().forEach(System.out::println);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
