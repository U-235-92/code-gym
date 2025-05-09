package aq.gym.contests.array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SymbolInAnotherLine {

	public static void main(String[] args) {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			String firstLine = br.readLine();
			String secondLine = br.readLine();
			int count = 0;
			for(int i = 0; i < secondLine.length(); i++) {
				char ch = secondLine.charAt(i);
				if(firstLine.indexOf(ch) >= 0) {
					count++;
				}
			}
			System.out.println(count);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
