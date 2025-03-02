package aq.gym.contests.tricky_cipher;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class TrickyCipher {

	private static final List<Character> alphabet = IntStream.rangeClosed(97, 122).mapToObj(num -> (char) num).toList();
	
	public static void main(String[] args) {
		String result = makeCiphers();
		System.out.println(result);
	}

	private static String makeCiphers() {
		StringBuilder result = new StringBuilder();
		try(BufferedReader br = new BufferedReader(new FileReader(new File("src/main/java/aq/gym/contests/tricky_cipher/input.txt")))) {
			String line = br.readLine();
			int num = Integer.valueOf(line);
			while((line = br.readLine()) != null && num-- > 0) {
				String[] parts = line.split(",");
				String lName = parts[0];
				String fName = parts[1];
				String mName = parts[2];
				int day = Integer.valueOf(parts[3]);
				int month = Integer.valueOf(parts[4]);
				int diffNums = numberOfDiiferentLetters(lName, fName, mName);
				int sumNumbersOfDayAndMonth = sumOfNumbersDayAndMonth(day, month);
				int numberLetterAlphabet = getNumberOfPositionLastnameLetterInAlphabet(lName);
				int number = diffNums + (sumNumbersOfDayAndMonth * 64) + (numberLetterAlphabet * 256);
				String cipher = Integer.toHexString(number & 0xFFF);
				if(cipher.length() < 3) {
					while(cipher.length() != 3) {
						cipher = "0" + cipher;
					}
				}
				result.append(cipher.toUpperCase());
				result.append(" ");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result.toString().trim();
	}
	
	private static int numberOfDiiferentLetters(String firstname, String middlename, String lastname) {
		Set<Integer> firstLetters = firstname.chars().collect(HashSet::new, Set::add, Set::addAll);
		firstLetters.addAll(middlename.chars().collect(HashSet::new, Set::add, Set::addAll));
		firstLetters.addAll(lastname.chars().collect(HashSet::new, Set::add, Set::addAll));
		return firstLetters.size();
	}
	
	private static int sumOfNumbersDayAndMonth(int day, int month) {
		int sum = 0;
		while(day > 0) {
			sum += day % 10;
			day = day / 10;
		}
		while(month > 0) {
			sum += month % 10;
			month = month / 10;
		}
		return sum;
	}
	
	private static int getNumberOfPositionLastnameLetterInAlphabet(String lastname) {
		char ch = lastname.toLowerCase().charAt(0);
		int pos = alphabet.indexOf(ch);
		return pos + 1;
	}
}
