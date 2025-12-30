package aq.gym.contests.backtracking;

public class WordSearch {

//	https://leetcode.com/problems/word-search/
	public static void main(String[] args) {
//		char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
//		String word = "ABCCED";
//		char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
//		String word = "SEE";
//		char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
//		String word = "ABCB";
//		char[][] board = {{'z','z','z','z','z','z'},{'z','z','z','z','z','z'},{'z','z','z','z','z','z'},{'z','z','z','z','z','z'},{'z','z','z','z','a','z'},{'z','z','z','z','z','z'}};
//		String word = "zzzzzzazzzzz";
//		char[][] board = {  {'a','b','z','z','z','z'},
//							{'b','z','z','z','z','z'},
//							{'z','z','z','z','z','z'},
//							{'z','z','z','z','z','z'},
//							{'z','z','z','z','a','b'},
//							{'z','z','z','z','z','b'}};
//		String word = "zzzzzzbbazzzzz";
		char[][] board = {  {'C','A','A'},
							{'A','A','A'},
							{'B','C','D'}};
		String word = "AAB";
//		char[][] board = {  {'a','b'},
//							{'c','d'}};
//		String word = "abcd";
		System.out.println(new WordSearch().exist(board, word));
	}

	public boolean exist(char[][] board, String word) {
		for(int i = 0; i < board.length; i++) {
        	for(int j = 0; j < board[i].length; j++) {
				if(dfs(board, i, j, word, 0)) {
        			return true;
        		}
        	}
        }
		return false;
    }
    
    private boolean dfs(char[][] board, int i, int j, String word, int index) {
        if(index == word.length()) return true;
        if(i < 0 || j < 0|| i >= board.length || j >= board[0].length || board[i][j] != word.charAt(index)) return false;

        char temp = board[i][j];
        board[i][j] = '#';
        boolean found = dfs(board , i+1, j , word , index+1) || dfs(board, i-1, j, word, index +1) || dfs(board, i , j+1, word , index+1) || dfs(board, i , j-1, word, index+1);
        board[i][j] = temp;
        return found;
    }
}
