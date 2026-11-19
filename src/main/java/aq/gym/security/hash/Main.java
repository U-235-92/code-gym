package aq.gym.security.hash;

public class Main {

	public static void main(String[] args) {
		String line1 = "line";
		String line2 = "line";
		
		String hashLine1 = HashUtil.getHash(line1, "SHA-256");
		String hashLine2 = HashUtil.getHash(line2, "SHA-256");
		
		System.out.println(hashLine1);
		System.out.println(hashLine2);
	}
}
