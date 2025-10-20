package aq.gym.contests.string;

public class FinalValueOfVariableAfterPerformingOperations {

//	https://leetcode.com/problems/final-value-of-variable-after-performing-operations/description
	public static void main(String[] args) {
		String[] operations = {"X++","++X","--X","X--"};
		System.out.println(new FinalValueOfVariableAfterPerformingOperations().finalValueAfterOperations(operations));
	}

    public int finalValueAfterOperations(String[] operations) {
        int result = 0;
        for(String operation : operations) {
        	result = doOperation(operation, result);
        }
        return result;
    }
    
    private int doOperation(String operation, int accumulator) {
    	switch(operation) {
    	case "X++":
    	case "++X":
    		accumulator += 1;
    		break;
    	case "X--":
    	case "--X":
    		accumulator -= 1;
    		break;
    	}
    	return accumulator;
    }
}
