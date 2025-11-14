package aq.gym.contests.dynamic;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ArrayPermutations {

static final int MOD = 1000000007;
    
    public static void main(String[] args) {
    	
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        scanner.close();
        Map<Integer, Integer> dp = new HashMap<>();
        for (int num : a) {
            dp.put(num, dp.getOrDefault(num, 0) + 1);
        }
        long total = 1;
        for (int count : dp.values()) {
            total = (total * (count + 1)) % MOD;
        }
        total = (total - 1 + MOD) % MOD;
        System.out.println(total);
    }
}
