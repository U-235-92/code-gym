package aq.gym.contests.string;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class PairOfLetters {

	public static void main(String[] args) throws IOException {
//		ABCABC A //input
//		BC //output
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] words = reader.readLine().split("\\s");
        System.out.println(getPair(words));
        reader.close();
        writer.close();
    }

	@SuppressWarnings("unused")
	private static String getPair(String[] words) {
		Map<String, Integer> map = new HashMap<>();
		for(String word : words) {
			if(word.length() >= 2) {				
				char[] chrs = word.toCharArray();
				for(int i = 0; i < chrs.length - 1; i++) {
					char[] chPair = {chrs[i], chrs[i + 1]};
					String pair = new String(chPair);
					map.compute(pair, (k, v) -> {
						if(v == null) return 1;
						else return v + 1;
					});
				}
			}
		}
		Comparator<Map.Entry<String, Integer>> valComp = (e1, e2) -> Integer.compare(e2.getValue(), e1.getValue());
		Comparator<Map.Entry<String, Integer>> keyComp = (e1, e2) -> e2.getKey().compareTo(e1.getKey());
		String pair = map
				.entrySet()
				.stream()
				.sorted(valComp.thenComparing(keyComp))
				.map(Map.Entry::getKey)
				.collect(Collectors.toCollection(ArrayList::new))
				.get(0);
		return pair;
	}
}
