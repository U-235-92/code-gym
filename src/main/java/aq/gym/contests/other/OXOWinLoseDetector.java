package aq.gym.contests.other;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OXOWinLoseDetector {

	private static final char X = 'X';
	private static final char O = 'O';
	private static final char DOT = '.';
	private static final int WIN_COUNT = 5;
	
	public static void main(String[] args) {
//		char[][] game = new char[][]{{'.',  '.',  '.',  'X',  '.',  '.',  'X',  'X', 'X'}, {'.',  'X',  '.',  'X',  'X',  'O',  'O',  '.',  'O'}, {'X',  '.',  '.',  'X',  '.',  'X',  'X',  'O',  'O'}, {'X',  'X',  'X',  '.', 'X',  '.',  '.',  '.',  '.'}};
//		char[][] game = new char[][]{{'.',  '.',  '.',  '.',  'X',  'O',  'X',  'O', 'O'}, {'X',  'O',  'O',  'O',  '.',  'X',  'X',  'O',  'X'}, {'.',  '.',  'O',  'O',  '.',  '.',  'O',  'O',  'X'}};
//		char[][] game = generateGame(3, 5);
//		printGame(game);
		Scanner scanner = new Scanner(System.in);
		int[] nums = Arrays.stream(scanner.nextLine().split("\\s")).mapToInt(Integer::valueOf).toArray();
		int n = nums[0], m = nums[1];
		char[][] game = new char[n][m];
		for(int i = 0; i < n; i++) {
			char[] line = scanner.nextLine().toCharArray();
			game[i] = line;
		}
		scanner.close();
		if(game.length < WIN_COUNT && game[0].length < WIN_COUNT) {
			printNo();
		} else if(game.length == 1) {
			if(horizontalDetect(game)) {				
				printYes();
			} else {
				printNo();
			}
		} else if(game[0].length == 1) {
			if(verticalDetect(game)) {
				printYes();
			} else {
				printNo();
			}
		} else if(game.length < WIN_COUNT || game[0].length < WIN_COUNT) {
			if(horizontalDetect(game) || verticalDetect(game)) {
				printYes();
			} else {
				printNo();
			}
		} else if(game[0].length >= WIN_COUNT && game.length >= WIN_COUNT) {
			if(horizontalDetect(game)) {
				printYes();
			} else if(verticalDetect(game)) {
				printYes();
			} else if(diagonalDetectFromTopLeftCornerToDownThenToRigh(game)) {
				printYes();
			} else if(diagonalDetectFromTopRightCornerToDownThenToLeft(game)) {
				printYes();
			} else {
				printNo();
			}
		} else {
			printNo();
		}
	}
	
	private static boolean horizontalDetect(char[][] game) {
		int xCount = 0, oCount = 0;
		for(int i = 0; i < game.length; i++) {
			for(int k = 0; k < game[0].length; k++) {
				if(game[i][k] == X) {
					oCount = 0;
					xCount++;
					if(isYes(xCount)) {
						return true;
					}
				} else if(game[i][k] == O){
					xCount = 0;
					oCount++;
					if(isYes(oCount)) {
						return true;
					}
				} else if(game[i][k] == DOT) {
					xCount = 0;
					oCount = 0;
				}
			}
			xCount = 0; 
			oCount = 0;
		}
		return false;
	}
	
	private static boolean verticalDetect(char[][] game) {
		int xCountLeft = 0;
		int oCountLeft = 0;
		for(int i = 0; i < game[0].length; i++) {
			for(int k = 0; k < game.length; k++) {
				if(game[k][i] == X) {
					oCountLeft = 0;
					xCountLeft++;
					if(isYes(xCountLeft)) {
						return true;
					}
				} else if(game[k][i] == O) {
					xCountLeft = 0;
					oCountLeft++;
					if(isYes(oCountLeft)) {
						return true;
					}
				} else if(game[k][i] == DOT) {
					xCountLeft = 0;
					oCountLeft = 0;
				}
			}
			xCountLeft = 0;
			oCountLeft = 0;
		}
		return false;
	}
	
	private static boolean diagonalDetectFromTopLeftCornerToDownThenToRigh(char[][] game) {
		int xCountDiagonal = 0;
		int oCountDiagonal = 0;
		int startI = WIN_COUNT - 1, startJ = 0, i = startI, j = startJ, nextLineIdx = i;
		while(nextLineIdx < game.length) {
			if(game[i][j] == X) {
				oCountDiagonal = 0;
				xCountDiagonal++;
				if(xCountDiagonal == WIN_COUNT) {
					return true;
				}
			} else if(game[i][j] == O) {
				xCountDiagonal = 0;
				oCountDiagonal++;
				if(oCountDiagonal == WIN_COUNT) {
					return true;
				}
			} else if(game[i][j] == DOT) {
				oCountDiagonal = 0;
				xCountDiagonal = 0;
			}
			if(i == 0 || j == game[0].length - 1) {
				nextLineIdx++;
				if(nextLineIdx < game.length) {
					startI++;					
				}
				i = startI; j = startJ;
				oCountDiagonal = 0;
				xCountDiagonal = 0;
			} else {
				i--; j++; 
			}
		}
		while(j <= game[0].length - 1) {
			if(game[i][j] == X) {
				oCountDiagonal = 0;
				xCountDiagonal++;
				if(xCountDiagonal == WIN_COUNT) {
					return true;
				}
			} else if(game[i][j] == O) {
				xCountDiagonal = 0;
				oCountDiagonal++;
				if(oCountDiagonal == WIN_COUNT) {
					return true;
				}
			} else if(game[i][j] == DOT) {
				oCountDiagonal = 0;
				xCountDiagonal = 0;
			}
			if(i == 0) {
				startJ++;
				i = startI; j = startJ;
				oCountDiagonal = 0;
				xCountDiagonal = 0;
			} else {
				i--; j++; 
			}
		}
		return false;
	}
	
	private static boolean diagonalDetectFromTopRightCornerToDownThenToLeft(char[][] game) {
		int xCountDiagonal = 0;
		int oCountDiagonal = 0;
		int startI = WIN_COUNT - 1, startJ = game[0].length - 1, i = startI, j = startJ, nextLineIdx = i;
		while(nextLineIdx < game.length) {
			if(game[i][j] == X) {
				oCountDiagonal = 0;
				xCountDiagonal++;
				if(xCountDiagonal == WIN_COUNT) {
					return true;
				}
			} else if(game[i][j] == O) {
				xCountDiagonal = 0;
				oCountDiagonal++;
				if(oCountDiagonal == WIN_COUNT) {
					return true;
				}
			} else if(game[i][j] == DOT) {
				oCountDiagonal = 0;
				xCountDiagonal = 0;
			}
			if(i == 0 || j == 0) {
				nextLineIdx++;
				if(nextLineIdx < game.length) {
					startI++;					
				}
				i = startI; j = startJ;
				oCountDiagonal = 0;
				xCountDiagonal = 0;
			} else {
				i--; j--; 
			}
		}
		while(j >= 0) {
			if(game[i][j] == X) {
				oCountDiagonal = 0;
				xCountDiagonal++;
				if(xCountDiagonal == WIN_COUNT) {
					return true;
				}
			} else if(game[i][j] == O) {
				xCountDiagonal = 0;
				oCountDiagonal++;
				if(oCountDiagonal == WIN_COUNT) {
					return true;
				}
			} else if(game[i][j] == DOT) {
				oCountDiagonal = 0;
				xCountDiagonal = 0;
			}
			if(i == 0) {
				startJ--;
				i = startI; j = startJ;
				oCountDiagonal = 0;
				xCountDiagonal = 0;
			} else {
				i--; j--; 
			}
		}
		return false;
	}
	
	private static boolean isYes(int count) {
		return count == WIN_COUNT;
	}
	
	private static void printYes() {
		System.out.println("Yes");
	}
	
	private static void printNo() {
		System.out.println("No");
	}
	
	@SuppressWarnings("unused")
	private static void printGame(char[][] game) {
		for(char[] line : game) {
			for(char ch : line) {
				System.out.printf("%-3s", String.valueOf(ch));
			}
			System.out.println();
		}
	}
	
	@SuppressWarnings("unused")
	private static char[][] generateGame(int n, int m) {
		char[][] game = new char[n][m];
		final char[] symbols = {X, O, DOT};
		for(int i = 0; i < n; i++) {
			char[] line = IntStream
					.generate(() -> symbols[(int) (Math.random() * symbols.length)])
					.limit(m)
					.mapToObj(num -> String.valueOf((char) num))
					.collect(Collectors.joining())
					.toCharArray();
			game[i] = line;
		}
		return game;
	}
}
