package aq.gym.contests.string;

public class AddBinary {

	public static void main(String[] args) {
		String a = "11", b = "0";
		String result = new AddBinary().addBinary(a, b);
		System.out.println(result);
	}

	public String addBinary(String a, String b) {
		StringBuilder result = new StringBuilder();
		char[] aChrs = a.toCharArray(), bChrs = b.toCharArray();
		int i = aChrs.length - 1, j = bChrs.length - 1, carry = 0;
		while(i >= 0 || j >= 0) {
			int sum = 0, aNum = 0, bNum = 0;
			if(i >= 0) aNum = (aChrs[i--] - '0');
			if(j >= 0) bNum = (bChrs[j--] - '0');
			sum = aNum + bNum;
			if(sum == 2 && carry == 1) {
				sum = 1; carry = 1; 
				result.append(sum);
			} else if(sum == 2 && carry == 0) {
				sum = 0; carry = 1;
				result.append(sum);
			} else if(sum == 1 && carry == 1) {
				sum = 0; carry = 1;
				result.append(sum);
			} else if(sum == 0 && carry == 1) {
				sum = 1; carry = 0;
				result.append(sum);
			} else {
				result.append(sum);
			}
		}
		if(carry == 1) result.append(carry);
		return result.reverse().toString();
	}
}
