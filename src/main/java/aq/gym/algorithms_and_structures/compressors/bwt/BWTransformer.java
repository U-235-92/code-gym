package aq.gym.algorithms_and_structures.compressors.bwt;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BWTransformer {

	private static final char EOF = '$';
	
	public static void main(String[] args) throws IOException {
		String string = "Hello, java!" + EOF;
		BWTransformer transformer = new BWTransformer();
		String transformedToBW = transformer.transformToBW(string);
		String trnsformedFromBW = transformer.transformFromBW(transformedToBW);
		System.out.println("Origin: " + string);
		System.out.println("Transformed to BW: " + transformedToBW);
		System.out.println("Trnsformed from BW: " + trnsformedFromBW);
	}

	@SuppressWarnings("unused")
	private static String getString() throws IOException {
		System.out.println("Hello, write a string.\nTo end write $");		
		char read = Character.MAX_VALUE;
		String string = "";
		while((read = (char) System.in.read()) != EOF)  {
			string = string + read;
		}
		return string + EOF;
	}
	
	private String transformToBW(String string) {
		List<String> shifts = new LinkedList<String>();
		for(int i = 1; i <= string.length(); i++) {
			String tail = string.substring(0, i);
			String head = string.substring(i, string.length());
			String shift = head + tail;
			shifts.add(shift);
		}
		return shifts.stream()
				.sorted()
				.map(shift -> shift.charAt(shift.length() - 1) + "")
				.reduce(String::concat)
				.get();
	}
	
	private String transformFromBW(String string) {
		String[] shifts = new String[string.length()];
		int iteration = 0;
		while(iteration < string.length()) {			
			for(int i = 0; i < string.length(); i++) {
				if(shifts[i] == null)
					shifts[i] = string.charAt(i) + "";
				else
					shifts[i] = string.charAt(i) + shifts[i];
			}
			Arrays.sort(shifts);
			iteration++;
		}
		return Arrays.stream(shifts)
				.dropWhile(shift -> shift.charAt(shift.length() - 1) != EOF)
				.findFirst()
				.get();
	}
}
