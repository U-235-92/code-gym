package aq.gym.contests.dynamic;

public class EditDistance {

//	https://leetcode.com/problems/edit-distance/
	public static void main(String[] args) {
		String word1 = "horse", word2 = "ros";
		System.out.println(new EditDistance().minDistance(word1, word2));
	}

    public int minDistance(String word1, String word2) {
        int[][] levensteinDistance = new int[word1.length() + 1][word2.length() + 1];
        initBaseRowDistance(levensteinDistance);
        initBaseColumnDistance(levensteinDistance);
        for(int i = 1; i < levensteinDistance.length; i++) {
        	for(int j = 1; j < levensteinDistance[i].length; j++) {
        		evaluateLevensteinDistance(levensteinDistance, i, j, word1.charAt(i - 1), word2.charAt(j - 1));
        	}
        }
        return levensteinDistance[levensteinDistance.length - 1][levensteinDistance[0].length - 1];
    }
    
    private void initBaseRowDistance(int[][] levensteinDistance) {
    	for(int i = 0; i < levensteinDistance[0].length; i++) {
    		levensteinDistance[0][i] = i;
    	}
    }
    
    private void initBaseColumnDistance(int[][] levensteinDistance) {
    	for(int i = 0; i < levensteinDistance.length; i++) {
    		levensteinDistance[i][0] = i;
    	}
    }
    
    private void evaluateLevensteinDistance(int[][] levensteinDistance, int i, int j, char ch1, char ch2) {
    	int deleteCost = levensteinDistance[i - 1][j] + 1;
    	int insertCost = levensteinDistance[i][j - 1] + 1;
    	int replaceCost = levensteinDistance[i - 1][j - 1] + ((ch1 == ch2) ? 0 : 1);
    	levensteinDistance[i][j] = Math.min(Math.min(deleteCost, insertCost), replaceCost);
    }
}
