package aq.gym.contests.other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class RobotCleaner {

	public static void main(String[] args) {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			int[] data = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::valueOf).toArray();
			int n = data[0];
			int m = data[1];
			String[][] roomMap = new String[n][m];
			for(int i = 0; i < roomMap.length; i++) {
				String[] line = br.readLine().split("");
				roomMap[i] = line;
			}
			data = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::valueOf).toArray();
			int i = data[0];
			int j = data[1];
			int q = Integer.valueOf(br.readLine());
			String[] movementCommands = br.readLine().split("");
			if(n == 1 && m == 1) {
				if(roomMap[0][0].equals("#")) {
					System.out.println(0);
				} else {
					System.out.println(1);
				}
			} else {				
				Robot robot = new RobotCleaner().new Robot(roomMap, i, j);
				for(int c = 0; c < movementCommands.length; c++) {
					String command = movementCommands[c];
					if(command.equals("R")) {
						robot.turnRight();
					} else if(command.equals("L")) {
						robot.turnLeft();
					} else if(command.equals("M")) {
						robot.move();
					}
				}
				System.out.println(robot.numberVisitedCells);
	//			for(String[] line : roomMap) {
	//				for(String cell : line) {
	//					System.out.print(cell);
	//				}
	//				System.out.println();
	//			}
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	private class Robot {
		
		private int i;
		private int j;
		private int numberVisitedCells;
		private String[][] roomMap;
		private MovementState currentState;
		private MovementState upState;
		private MovementState downState;
		private MovementState leftState;
		private MovementState rightState;
		
		private Robot(String[][] roomMap, int i, int j) {
			this.roomMap = roomMap;
			this.i = i - 1;
			this.j = j - 1;
			this.numberVisitedCells = 0;
			if(this.i >= 0 && this.i < roomMap.length && this.j >= 0 && this.j < roomMap[this.i].length) {
				if(!roomMap[this.i][this.j].equals("#")) {
					roomMap[this.i][this.j] = "v";
					numberVisitedCells = 1;
				}				
			}
			upState = new UpMovementState(this);
			downState = new DownMovementState(this);
			leftState = new LeftMovementState(this);
			rightState = new RightMovementState(this);
			currentState = upState;
		}
		
		public void turnLeft() {
			currentState.turnLeft();
		}
		
		public void turnRight() {
			currentState.turnRight();
		}
		
		public void move() {
			currentState.move();
		}
	}
	
	private interface MovementState {
		void turnRight();
		void turnLeft();
		void move();
	}
	
	private class UpMovementState implements MovementState {

		private Robot robot;
		
		private UpMovementState(Robot robot) {
			this.robot = robot;
		}
		
		@Override
		public void turnRight() {
			robot.currentState = robot.rightState;
		}

		@Override
		public void turnLeft() {
			robot.currentState = robot.leftState;
		}
		
		@Override
		public void move() {
			if(robot.i > 0 && !robot.roomMap[robot.i - 1][robot.j].equals("#")) {
				robot.i -= 1;
				if(!robot.roomMap[robot.i][robot.j].equals("v")) {
					robot.roomMap[robot.i][robot.j] = "v";
					robot.numberVisitedCells++;
				}
			}
		}
	}
	
	private class DownMovementState implements MovementState {

		private Robot robot;
		
		private DownMovementState(Robot robot) {
			this.robot = robot;
		}
		
		@Override
		public void turnRight() {
			 robot.currentState = robot.leftState;
		}

		@Override
		public void turnLeft() {
			robot.currentState = robot.rightState;
		}

		@Override
		public void move() {
			if(robot.i < robot.roomMap.length - 1 && !robot.roomMap[robot.i + 1][robot.j].equals("#")) {
				robot.i += 1;
				if(!robot.roomMap[robot.i][robot.j].equals("v")) {
					robot.roomMap[robot.i][robot.j] = "v";
					robot.numberVisitedCells++;
				}
			}
		}
	}

	private class LeftMovementState implements MovementState {

		private Robot robot;
		
		public LeftMovementState(Robot robot) {
			this.robot = robot;
		}
		
		@Override
		public void turnRight() {
			robot.currentState = robot.upState;
		}

		@Override
		public void turnLeft() {
			robot.currentState = robot.downState;
		}

		@Override
		public void move() {
			if(robot.j > 0 && !robot.roomMap[robot.i][robot.j - 1].equals("#")) {				
				robot.j -= 1;
				if(!robot.roomMap[robot.i][robot.j].equals("v")) {
					robot.roomMap[robot.i][robot.j] = "v";
					robot.numberVisitedCells++;
				}
			}
		}
	}

	private class RightMovementState implements MovementState {
		
		private Robot robot;
		
		public RightMovementState(Robot robot) {
			this.robot = robot;
		}

		@Override
		public void turnRight() {
			robot.currentState = robot.downState;
		}

		@Override
		public void turnLeft() {
			robot.currentState = robot.upState;
		}

		@Override
		public void move() {
			if(robot.j < robot.roomMap[robot.i].length - 1 && !robot.roomMap[robot.i][robot.j + 1].equals("#")) {				
				robot.j += 1;
				if(!robot.roomMap[robot.i][robot.j].equals("v")) {
					robot.roomMap[robot.i][robot.j] = "v";
					robot.numberVisitedCells++;
				}
			}
		}
	}
}
