package aq.gym.simple_encode_decode_string;

public class Encoder {

	private char[] key;
	private String message;
	
	private Encoder() {
		super();
	}
	
	public Encoder(char[] key, String message) {
		this();
		this.key = key;
		this.message = message;
	}
	
	public String encode() {
		String encodeMessage = "";
		int j = 0;
		for(int i = 0; i < message.length(); i++) {
			encodeMessage = encodeMessage + (char) (message.charAt(i) ^ key[j++]);
			if(j >= key.length) j = 0;
		}
		return encodeMessage;
	}
}
