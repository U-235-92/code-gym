package aq.gym.simple_encode_decode_string;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		char[] key = IOManager.readKey();
		String origMessage = IOManager.readMessage("orig_message.txt");
		Encoder encoder = new Encoder(key, origMessage);
		String encodeMessage = encoder.encode();
		Decoder decoder = new Decoder(key, encodeMessage);
		String decodeMessage = decoder.decode();
		System.out.println("Orig message: " + origMessage);
		System.out.println("Encode message: " + encodeMessage);
		System.out.println("Decode message: " + decodeMessage);
	}

}
