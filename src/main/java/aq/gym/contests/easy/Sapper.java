package aq.gym.contests.easy;

import java.util.Arrays;
import java.util.Scanner;

public class Sapper {

	private static final String MINE = "*";
	
//	3 2 2
//	1 1
//	2 2
//	
//	2 2 0
//	
//	4 4 4
//	1 3
//	2 1
//	4 2
//	4 4
//	
//	2 2 4
//	1 1
//	1 2
//	2 1
//	2 2
//	
//	2 1 1
//	1 1
//	
//	1 2 1
//	1 1 
	
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int[] sapperMapData = Arrays.stream(scanner.nextLine().split("\\s")).mapToInt(Integer::valueOf).toArray();
		int row = sapperMapData[0];
		int column = sapperMapData[1];
		int mines = sapperMapData[2];
		int idx = 0;
		Coordinate[] mineCords = new Coordinate[mines];
		while(mines-- > 0) {
			String line = scanner.nextLine();
			int[] coordinates = Arrays.stream(line.split("\\s")).mapToInt(Integer::valueOf).toArray();
			Coordinate c = new Coordinate(coordinates[0], coordinates[1]);
			mineCords[idx++] = c;
		}
		scanner.close();
		String[][] sapperMap = buildSapperMap(row, column, mineCords);
		for(String[] line : sapperMap) {
			for(String s : line) {
				if(s == null) {
					System.out.print("0 ");
				} else {
					System.out.print(s + " ");
				}
			}
			System.out.println();
		}
	}
	
	private static String[][] buildSapperMap(int row, int column, Coordinate[] mineCoords) {
		String[][] sapperMap = new String[row][column];
		sapperMap = putMinesToMap(sapperMap, mineCoords);
		sapperMap = putPromptsToMap(sapperMap);
		return sapperMap;
	}
	
	private static String[][] putMinesToMap(String[][] sapperMap, Coordinate[] mineCoords) {
		for(Coordinate c : mineCoords) {
			int x = c.x - 1;
			int y = c.y - 1;
			sapperMap[x][y] = MINE;
		}
		return sapperMap;
	}
	
	private static String[][] putPromptsToMap(String[][] sapperMap) {
		int row = sapperMap.length, column = sapperMap[0].length;
		if(row == 1 && column == 1) {
			if(!isMine(0, 0, sapperMap)) {
				sapperMap[0][0] = "0";
			}
		} else if(row == 1) {
			String[] line = sapperMap[0];
			for(int i = 0; i < line.length; i++) {
				if(!isMine(i, line)) {
					if(i == 0) {
						if(line[i + 1].equals(MINE)) {
							line[i] = "1";
						}
					} else if(i == line.length - 1) {
						if(line[i - 1].equals(MINE)) {
							line[i] = "1";
						}
					} else {
						if(line[i + 1].equals(MINE)) {
							line[i] = "1";
						}
					}
				}
			}
		} else if(column == 1) {
			int i = 0;
			for(int j = 0; j < sapperMap[0].length; j++) {
				if(!isMine(i, j, sapperMap)) {
					if(j == 0) {
						if(sapperMap[i][j + 1].equals(MINE)) {
							sapperMap[i][j] = "1";
						}
					} else if(j == sapperMap[0].length - 1) {
						if(sapperMap[i][j - 1].equals(MINE)) {
							sapperMap[i][j] = "1";
						}
					} else {
						if(sapperMap[i][j + 1].equals(MINE)) {
							sapperMap[i][j] = "1";
						}
					}
				}
			}
		} else {
			for(int i = 0; i < row; i++) {
				for(int j = 0; j < column; j++) {
					if(!isMine(i, j, sapperMap)) {
						if(isOnLeftTopCorner(i, j)) {
							int prompt = 0;
//						Look for mines in row which is below current
							for(int k = i + 1, m = 0; m <= j + 1; m++) {
								if(isMine(k, m, sapperMap)) {
									prompt++;
								}
							}
//						Look for mines in column which is right current
							for(int k = j + 1, m = 0; m <= 0; m++) {
								if(isMine(m, k, sapperMap)) {
									prompt++;
								}
							}
							sapperMap[i][j] = prompt + "";
						} else if(isOnRightTopCorner(i, j, sapperMap)) {
							int prompt = 0;
//						Look for mines in row which is below current
							for(int k = i + 1, m = j - 1; m < sapperMap[0].length; m++) {
								if(isMine(k, m, sapperMap)) {
									prompt++;
								}
							}
//						Look for mines in column which is left current
							for(int k = j - 1, m = 0; m <= 0; m++) {
								if(isMine(m, k, sapperMap)) {
									prompt++;
								}
							}
							sapperMap[i][j] = prompt + "";
						} else if(isOnRightFloorCorner(i, j, sapperMap)) {
							int prompt = 0;
//						Look for mines in row which is above current
							for(int k = i - 1, m = j - 1; m < sapperMap[0].length; m++) {
								if(isMine(k, m, sapperMap)) {
									prompt++;
								}
							}
//						Look for mines in column which is left current
							for(int k = j - 1, m = i; m < sapperMap.length; m++) {
								if(isMine(m, k, sapperMap)) {
									prompt++;
								}
							}
							sapperMap[i][j] = prompt + "";
						} else if(isOnLeftFloorCorner(i, j, sapperMap)) {
							int prompt = 0;
//						Look for mines in row which is above current
							for(int k = i - 1, m = 0; m <= j + 1; m++) {
								if(isMine(k, m, sapperMap)) {
									prompt++;
								}
							}
//						Look for mines in column which is right current
							for(int k = j + 1, m = i; m < sapperMap.length; m++) {
								if(isMine(m, k, sapperMap)) {
									prompt++;
								}
							}
							sapperMap[i][j] = prompt + "";
						} else if(isOnTop(i)) {
							int prompt = 0;
//						Look for mines in row which is below current
							for(int k = i + 1, m = j - 1; m <= j + 1; m++) {
								if(isMine(k, m, sapperMap)) {
									prompt++;
								}
							}
//						Look for mines in column which is right current
							for(int k = i, m = j + 1; m <= j + 1; m++) {
								if(isMine(k, m, sapperMap)) {
									prompt++;
								}
							}
//						Look for mines in column which is left current
							for(int k = i, m = j - 1; m <= j - 1; m++) {
								if(isMine(k, m, sapperMap)) {
									prompt++;
								}
							}
							sapperMap[i][j] = prompt + "";
						} else if(isOnRight(j, sapperMap)) {
							int prompt = 0;
//						Look for mines in row which is above current
							for(int k = i - 1, m = j - 1; m < sapperMap[0].length; m++) {
								if(isMine(k, m, sapperMap)) {
									prompt++;
								}
							}
//						Look for mines in row which is below current
							for(int k = i + 1, m = j - 1; m < sapperMap[0].length; m++) {
								if(isMine(k, m, sapperMap)) {
									prompt++;
								}
							}
//						Look for mines in column which is left current
							for(int k = j - 1, m = i; m <= i; m++) {
								if(isMine(m, k, sapperMap)) {
									prompt++;
								}
							}
							sapperMap[i][j] = prompt + "";
						} else if(isOnFloor(i, sapperMap)) {
							int prompt = 0;
//						Look for mines in row which is above current
							for(int k = i - 1, m = j - 1; m <= j + 1; m++) {
								if(isMine(k, m, sapperMap)) {
									prompt++;
								}
							}
//						Look for mines in column which is left current
							for(int k = j - 1, m = i; m <= i; m++) {
								if(isMine(m, k, sapperMap)) {
									prompt++;
								}
							}
//						Look for mines in column which is right current
							for(int k = j + 1, m = i; m <= i; m++) {
								if(isMine(m, k, sapperMap)) {
									prompt++;
								}
							}
							sapperMap[i][j] = prompt + "";
						} else if(isOnLeft(j)) {
							int prompt = 0;
//						Look for mines in column which is right current
							for(int k = j + 1, m = i; m <= i; m++) {
								if(isMine(m, k, sapperMap)) {
									prompt++;
								}
							}
//						Look for mines in row which is above current
							for(int k = i - 1, m = j; m <= j + 1; m++) {
								if(isMine(k, m, sapperMap)) {
									prompt++;
								}
							}
//						Look for mines in row which is below current
							for(int k = i + 1, m = j; m <= j + 1; m++) {
								if(isMine(k, m, sapperMap)) {
									prompt++;
								}
							}
							sapperMap[i][j] = prompt + "";
						} else {
							int prompt = 0;
//						Look for mines in column which is left current
							for(int k = j - 1, m = i; m <= i; m++) {
								if(isMine(m, k, sapperMap)) {
									prompt++;
								}
							}
//						Look for mines in column which is right current
							for(int k = j + 1, m = i; m <= i; m++) {
								if(isMine(m, k, sapperMap)) {
									prompt++;
								}
							}
//						Look for mines in row which is above current
							for(int k = i - 1, m = j - 1; m <= j + 1; m++) {
								if(isMine(k, m, sapperMap)) {
									prompt++;
								}
							}
//						Look for mines in row which is below current
							for(int k = i + 1, m = j - 1; m <= j + 1; m++) {
								if(isMine(k, m, sapperMap)) {
									prompt++;
								}
							}
							sapperMap[i][j] = prompt + "";
						}
					}
				}
			}
		}
		return sapperMap;
	}
	
	private static boolean isMine(int x, String[] sapperMap) {
		return sapperMap[x] != null && sapperMap[x].equals(MINE);
	}
	
	private static boolean isMine(int x, int y, String[][] sapperMap) {
		return sapperMap[x][y] != null && sapperMap[x][y].equals(MINE);
	}
	
	private static boolean isOnTop(int x) {
		return x == 0;
	}
	
	private static boolean isOnFloor(int x, String[][] map) {
		return x == map.length - 1;
	}
	
	private static boolean isOnRight(int y, String[][] map) {
		return y == map[0].length - 1;
	}

	private static boolean isOnLeft(int x) {
		return x == 0;
	}
	
	private static boolean isOnLeftTopCorner(int x, int y) {
		return x == 0 && y == 0;
	}
	
	private static boolean isOnRightTopCorner(int x, int y, String[][] map) {
		return x == 0 && y == map[0].length - 1;
	}
	
	private static boolean isOnLeftFloorCorner(int x, int y, String[][] map) {
		return x == map.length - 1 && y == 0;
	}
	
	private static boolean isOnRightFloorCorner(int x, int y, String[][] map) {
		return x == map.length - 1 && y == map[0].length - 1;
	}
	
	private static class Coordinate {
		private int x;
		private int y;
		
		public Coordinate(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
