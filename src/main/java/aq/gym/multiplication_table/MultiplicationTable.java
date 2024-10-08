package aq.gym.multiplication_table;

public class MultiplicationTable {

	public static void show() {
		int[][] table = new int[10][9];
		int x = 1;
		System.out.print("    ");
		for(int i = 1; i < table.length; i++) {
			System.out.printf("%-4d", i);
		}
		System.out.println();
		System.out.print("    ");
		for(int i = 1; i < table.length; i++) {
			System.out.printf("%-4s", "_");
		}
		System.out.println();
		for(int i = 0; i < table.length; i++) {
			int y = 1;
			System.out.printf("%-2d| ", x);
			for(int j = 0; j < table[0].length; j++) {
				table[i][j] = x * y++;
				System.out.printf("%-4d", table[i][j]);
			}
			x++;
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		show();
	}
}
