package aq.gym.primitive_types_work;

public class PrimitiveTypesTestDrive {

	public static void main(String[] args) {
		int iNum = Integer.MAX_VALUE;
		long lNum = 1L;
		long lNum2 = Integer.MAX_VALUE;
//		long result = iNum + lNum; // Correct answer [2147483648] because by default type convert to biggest type operand
//		long result = iNum + 1L; // Correct answer [2147483648] because by default type convert to biggest type operand
//		long result = iNum + 1; // ERR! Incorrect answer [-2147483648] because by default type convert to biggest type operand (in this case to integer)
		long result = lNum2 + 1; // Correct answer [2147483648] because by default type convert to biggest type operand (in this case to long)
		System.out.println(result); // But by default types convert either [integer] or [double] (example short + byte = integer) (integer + double = double) But (integer + long = long etc.)
	}
}
