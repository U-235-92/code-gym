package aq.gym.encoders.xor_encoder;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class IOManager {

	public static String readMessage(String file) throws IOException {
		String message = new String(IOManager.class.getResourceAsStream(file).readAllBytes());
		return message;
	}
	
	public static boolean writeMessage(String message, String file) throws IOException {
		DataOutputStream dos = new DataOutputStream(new FileOutputStream(IOManager.class.getResource(file).getFile()));
		dos.writeChars(message);
		return true;
	}
	
	public static char[] readKey() throws IOException {
		System.out.println("Write a key:");
		Scanner scanner = new Scanner(System.in);
		char[] key = scanner.nextLine().toCharArray();
		scanner.close();
		return key;
	}
}
