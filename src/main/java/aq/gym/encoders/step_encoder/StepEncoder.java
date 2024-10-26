package aq.gym.encoders.step_encoder;

public class StepEncoder {

	public static void main(String[] args) {
		String text = "The quick brown fox jumps over a lazy dog."; 
		String encode = encodeByStep(text, 3);
		String decode = decodeByStep(encode, 3);
		System.out.println("Orig: " + text);
		System.out.println("Encoded: " + encode);
		System.out.println("Decoded: " + decode);
	}

	private static String encodeByStep(String text, int step) {
		StringBuilder encoded = new StringBuilder();
		for(int i = 0; i < text.length(); i++) {
			for(int j = i; j < text.length(); j += step) {
				if(encoded.length() == text.length())
					return encoded.toString();
				encoded.append(text.charAt(j));
			}
		}
		return encoded.toString();
	}
	
	private static String decodeByStep(String text, int step) {
		int size = text.length();
		char[] decoded = new char[size];
		int startFromIdx = 0, encodedCharIdx = 0;
		while(encodedCharIdx < size) {
			for(int decodedCharIdx = startFromIdx; decodedCharIdx < size; decodedCharIdx += step) {
				decoded[decodedCharIdx] = text.charAt(encodedCharIdx);
				encodedCharIdx++; 
			}
			startFromIdx++;
		}
		return new String(decoded);
	}
}
