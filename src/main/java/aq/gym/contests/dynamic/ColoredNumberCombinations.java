package aq.gym.contests.dynamic;

import java.util.Scanner;

public class ColoredNumberCombinations {

    public static void main(String[] args) {
        final int MOD = 1_000_000_007;
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        scanner.close();
        long[] dp = new long[n + 1];
        dp[0] = 1;
        for (int x = 1; x <= n; x++) {
            for (int c = 1; c <= k; c++) {
                for (int s = x; s <= n; s++) {
                    dp[s] = (dp[s] + dp[s - x]) % MOD;
                }
            }
        }
        System.out.println(dp[n]); 
    }
}
