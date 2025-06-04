package aq.gym.patterns.flyweight;

import java.util.HashMap;
import java.util.Map;

public class LetterFactory {

	private Map<String, Letter> map = new HashMap<>();
	private static LetterFactory factory = new LetterFactory();
	
	private LetterFactory() {
		super();
	}
	
	public static LetterFactory getInstace() {
		return factory;
	}
	
	public Letter getLetter(String letter) {
		return getLetterOrPut(letter);
	}
	
	private Letter getLetterOrPut(String letter) {
		if(map.keySet().contains(letter)) {			
			return map.get(letter);
		} else {
			switch (letter) {
			case "a": 
				map.put(letter, new LetterA());
				return map.get(letter);
			case "b":
				map.put(letter, new LetterB());
				return map.get(letter);
			default:
				throw new IllegalArgumentException(letter);
			}
		}
	}
}
