package aq.gym.fun.byter;

import java.security.SecureRandom;

public class Main {

	public static void main(String[] args) {
		printCafe();
		printRandomBytes();
		byte b = 17;
		System.out.println(String.format("%02x", b));
		int i = 255;
		System.out.println(String.format("%02x", i));
		byte c = 127;
		System.out.println(String.format("%02x", (c | 0xFF)));
		System.out.println(String.format("%02x", c));
		
	}
	
	private static void printCafe() {
		byte a = 12;
		byte b = 10;
		byte c = 15;
		byte d = 14;
		System.out.println(String.format("%x%x%x%x", a, b, c, d)); // cafe
	}
	
	private static void printRandomBytes() {
		StringBuilder sb = new StringBuilder();
		
		SecureRandom secureRandom = new SecureRandom();
		
		byte[] bytes = new byte[8];
		
		secureRandom.nextBytes(bytes);
		
		for(int i = 0; i < bytes.length; i++) {
			sb.append(String.format("%02x ", bytes[i]));
		}
		
		System.out.println(sb);
	}

}
