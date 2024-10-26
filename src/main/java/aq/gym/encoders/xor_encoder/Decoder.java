package aq.gym.encoders.xor_encoder;

public class Decoder {

	private char[] key;
	private String message;
	
	private Decoder() {
		super();
	}
	
	public Decoder(char[] key, String message) {
		this();
		this.key = key;
		this.message = message;
	}
	
	public String decode() {
		String decodeMessage = "";
		int j = 0;
		for(int i = 0; i < message.length(); i++) {
			decodeMessage = decodeMessage + (char) (message.charAt(i) ^ key[j++]);
			if(j >= key.length) j = 0;
		}
		return decodeMessage;
	}
}
