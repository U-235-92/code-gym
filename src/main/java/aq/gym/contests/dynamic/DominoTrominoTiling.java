package aq.gym.contests.dynamic;

public class DominoTrominoTiling {

	public static void main(String[] args) {
		System.out.println(new DominoTrominoTiling().numTilings(30));
	}

	public int numTilings(int n) {
        if(n == 1) {        	
        	return 1;
        } else if(n == 2) {        	
        	return 2;
        } else {
        	long[] tiling = new long[n + 1];
        	tiling[0] = 1; tiling[1] = 1; tiling[2] = 2;
        	for(int i = 3; i < tiling.length; i++) {
        		tiling[i] = ((tiling[i - 1] * 2) + tiling[i - 3]) % ((long) Math.pow(10, 9) + 7);
        	}
        	return (int) tiling[tiling.length - 1];
        }
    }
}
