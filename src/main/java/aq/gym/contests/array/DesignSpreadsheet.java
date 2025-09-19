package aq.gym.contests.array;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DesignSpreadsheet {

	public static void main(String[] args) {
		Spreadsheet spreadsheet = new Spreadsheet(5);
		System.out.println(spreadsheet.getValue("=5+7")); // returns 12 (5+7)
		spreadsheet.setCell("A1", 10); // sets A1 to 10
		System.out.println(spreadsheet.getValue("=A1+6")); // returns 16 (10+6)
		spreadsheet.setCell("B2", 15); // sets B2 to 15
		System.out.println(spreadsheet.getValue("=A1+B2")); // returns 25 (10+15)
		spreadsheet.resetCell("A1"); // resets A1 to 0
		System.out.println(spreadsheet.getValue("=A1+B2")); // returns 15 (0+15)
		spreadsheet.setCell("Z5", 58);
		System.out.println(spreadsheet.getValue("=Z5+A1")); // returns 58 (0+58)
	}
}

class Spreadsheet {

	private int[][] spreadsheet;
	private Map<String, Integer> columnIndexMap;
	
    public Spreadsheet(int rows) {
    	super();
    	ToIntFunction<String> letterToColumnIndexMapper = letter -> -1 * ((97 + 26) % letter.codePointAt(0) - 26);
        spreadsheet = new int[rows][26]; 
        columnIndexMap = Stream.iterate(97, n -> n + 1)
        		.limit(26)
        		.map(n -> Character.toString(n))
        		.collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(letterToColumnIndexMapper)));
    }
    
    public void setCell(String cell, int value) {
        int row = getRow(cell);
        int column = getColumn(cell);
        spreadsheet[row][column] = value;
    }
    
    public void resetCell(String cell) {
    	setCell(cell, 0);
    }
    
    public int getValue(String formula) {
    	int result = 0;
    	
    	Pattern npnPattern = Pattern.compile("^=[0-9]+\\+[0-9]+$"); // a number plus a number
    	Pattern npcPattern = Pattern.compile("^=[0-9]+\\+[A-Z][0-9]+$"); // a number plus a cell value
    	Pattern cpnPattern = Pattern.compile("^=[A-Z][0-9]+\\+[0-9]+$"); // a cell value plus a number
    	Pattern cpcPattern = Pattern.compile("^=[A-Z][0-9]+\\+[A-Z][0-9]+$"); // a cell value plus a cell value
    	
    	if(npnPattern.matcher(formula).matches()) 
    		result = evaluate(Arrays.stream(formula.substring(1).split("\\+")).mapToInt(Integer::valueOf).toArray());
    	
    	else if(npcPattern.matcher(formula).matches()) 
            result = evaluate(0, 1, formula.substring(1).split("\\+"));
    	
    	else if(cpnPattern.matcher(formula).matches()) 
            result = evaluate(1, 0, formula.substring(1).split("\\+"));
    	
    	else if(cpcPattern.matcher(formula).matches()) 
            result = evaluate(formula.substring(1).split("\\+"));
    	
    	return result;
    }
    
    private int evaluate(int[] operands) {
    	return Arrays.stream(operands).reduce(Integer::sum).getAsInt();
    }
    
    private int evaluate(int numberIdx, int cellIdx, String[] operands) {
    	return Integer.valueOf(operands[numberIdx]) + spreadsheet[getRow(operands[cellIdx])][getColumn(operands[cellIdx])];
    }
    
    private int evaluate(String[] operands) {
    	return spreadsheet[getRow(operands[0])][getColumn(operands[0])] + 
    				spreadsheet[getRow(operands[1])][getColumn(operands[1])];
    }
    
    private int getRow(String cell) {
    	int row = Integer.valueOf(cell.substring(1));
    	return row - 1;
    }
    
    private int getColumn(String cell) {
    	int column = columnIndexMap.get(cell.substring(0, 1).toLowerCase());
    	return column;
    }
}
