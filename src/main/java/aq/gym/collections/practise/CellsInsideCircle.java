package aq.gym.collections.practise;

import java.util.Comparator;
import java.util.PriorityQueue;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

public class CellsInsideCircle {

	public static void main(String[] args) {
		Sheet sheet = Sheet.instance(4, 4);
		Circle circle = Circle.instance(2, 2, 2);
//		Sheet sheet = Sheet.instance(5, 5);
//		Circle circle = Circle.instance(3, 2, 2);
//		Sheet sheet = Sheet.instance(7, 9);
//		Circle circle = Circle.instance(4, 3, 3);
		PriorityQueue<Cell> cellsInsideCircle = cellsInsideCircle(sheet, circle);
		System.out.println("Number of cells: " + cellsInsideCircle.size());
		cellsInsideCircle.stream().forEach(System.out::println);
	}
	
	private static PriorityQueue<Cell> cellsInsideCircle(Sheet sheet, Circle circle) {
		Comparator<Cell> cellComparator = (cell1, cell2) -> {
			return (cell1.x - cell2.x) + (cell1.y - cell2.y);
		};
		PriorityQueue<Cell> cells = new PriorityQueue<>(cellComparator);
		if(!isCorrectPlaceCircle(sheet, circle)) 
			throw new IllegalStateException("Wrong parameters of circle!");
		int xTopCornerCellPosition = circle.x;
		int yTopCornerCellPosition = circle.y;
		int radius = circle.radius;
		int cellDiaonalSum = 0;
		int diagonalWalkedCellCount = 0;
		//Look for max diagonal count cells that inside the circle 
		//All the coordinates [x, y] of the cells begin at left-top corner of the sheet
		//The inner coordinates [x, y] of cell begin at left-top corner
		while(xTopCornerCellPosition >= 0 && yTopCornerCellPosition >= 0) {
			cellDiaonalSum += sheet.cells[yTopCornerCellPosition][xTopCornerCellPosition].diameter;
			if(cellDiaonalSum >= radius) 
				break;
			yTopCornerCellPosition--; xTopCornerCellPosition--;
			diagonalWalkedCellCount++;
		}
		int cellsLineSize = diagonalWalkedCellCount * 2;
		int columnCellInsideCircleCounter = cellsLineSize;
		int rowCellInsideCircleCounter = cellsLineSize;
		int i = yTopCornerCellPosition, j = xTopCornerCellPosition;
		//Getting all the cells inside circle and put them in priority queue
		while(rowCellInsideCircleCounter > 0) {
			while(columnCellInsideCircleCounter > 0) {
				cells.add(sheet.cells[i][j]);
				columnCellInsideCircleCounter--; j++;
			}
			columnCellInsideCircleCounter = cellsLineSize;
			j = xTopCornerCellPosition;
			i++; rowCellInsideCircleCounter--;
		}
		return cells;
	}
	
	private static boolean isCorrectPlaceCircle(Sheet sheet, Circle circle) {
		int xCirclePosition = circle.getX();
		int yCirclePosition = circle.getY();
		int radius = circle.getRadius();
		int rightSheetBorder = sheet.getCells()[0].length;
		int leftSheetBorder = 0;
		int topSheetBorder = 0;
		int downSheetBorder = sheet.getCells().length;
		return (xCirclePosition + radius <= rightSheetBorder) && 
				(xCirclePosition - radius >= leftSheetBorder) && 
				(yCirclePosition - radius >= topSheetBorder) && 
				(yCirclePosition + radius <= downSheetBorder);
	}

	@Getter
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	@ToString
	private static class Cell {
		
		private int x;
		private int y;
		private int diameter;
		private int size;

		static Cell instance(int x, int y, int size) {
			int sq = (int) (Math.pow(size, 2) + Math.pow(size, 2));
			int diameter = (int) Math.sqrt(sq);
			return new Cell(x, y, diameter, size);
		}
	}

	@Getter
	private static class Sheet {
		
		private Cell[][] cells;
		
		private Sheet(int rows, int columns) {
			cells = new Cell[rows][columns];
			for(int i = 0; i < rows; i++) {
				for(int j = 0; j < columns; j++) {
					cells[i][j] = Cell.instance(j, i, 1);
				}
			}
		}
		
		static Sheet instance(int rows, int columns) {
			return new Sheet(rows, columns);
		}
	}

	@Getter
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	private static class Circle {
		
		private int radius;
		private int x;
		private int y;
		
		static Circle instance(int x, int y, int radius) {
			return new Circle(radius, x, y);
		}
	}
}
