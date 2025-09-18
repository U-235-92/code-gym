package aq.gym.contests.numbers;

public class IntegerToRoman {

	public static void main(String[] args) {
		String roman = new IntegerToRoman().intToRoman(2085);
		System.out.println(roman);
	}
	
	public String intToRoman(int num) {
		String[] thousands = {"", "M", "MM", "MMM"};
		String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
		String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
		String[] ones = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
		String thousand = "", hundred = "", ten = "", one = "";
		thousand = thousands[(num / 1000)];
		hundred = hundreds[(num / 100 % 10)];
		one = ones[(num % 10)];
		ten = tens[(num / 10 % 10)];
		return thousand + hundred + ten + one;
	}
}
