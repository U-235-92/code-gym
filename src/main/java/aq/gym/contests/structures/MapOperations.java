package aq.gym.contests.structures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MapOperations {

	public static void main(String[] args) {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			Map<Integer, Integer> map = new HashMap<Integer, Integer>();
			int q = Integer.valueOf(br.readLine());
			for(int i = 0; i < q; i++) {
				int[] data = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::valueOf).toArray();
				int request = data[0];
				if(request == 1) {
					int key = data[1];
					int value = data[2];
					map.put(key, value);
				} else if(request == 2) {
					int key = data[1];
					System.out.println(map.getOrDefault(key, -1));
				}
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
