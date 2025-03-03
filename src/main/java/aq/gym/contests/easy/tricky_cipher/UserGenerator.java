package aq.gym.contests.easy.tricky_cipher;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class UserGenerator {

	private static final String[] firstanames = {"John", "Bob", "Trent"};
	private static final String[] middlenames = {"Mapper", "Setter", "Getter"};
	private static final String[] lastnames = {"Roberts", "Edison", "Walker"};
	
	public static void main(String[] args) {
		generate(2_000);
	}
	
	public static void generate(int n) {
		try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("src/main/java/aq/gym/contests/tricky_cipher/input.txt"))))) {
			pw.println(n);
			while(n-- > 0) {
				int fPos = new Random().nextInt(firstanames.length);
				int mPos = new Random().nextInt(middlenames.length);
				int lPos = new Random().nextInt(lastnames.length);
				int day = 1 + (int)(Math.random() * ((28 - 1) + 1));
				int month = 1 + (int)(Math.random() * ((12 - 1) + 1));
				int year = 1950 + (int)(Math.random() * ((2000 - 1950) + 1));
				String user = String.format("%s,%s,%s,%d,%d,%d", 
						lastnames[fPos], firstanames[mPos], middlenames[lPos], day, month, year);
				pw.println(user);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
