package aq.gym.contests.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

@SuppressWarnings("unused")
public class OpenTheLock {

	public static void main(String[] args) {
		TestCase case1 = new TestCase(new String[] {"0201","0101","0102","1212","2002"}, "0202");
		TestCase case2 = new TestCase(new String[] {"8888"}, "0009");
		TestCase case3 = new TestCase(new String[] {"8887","8889","8878","8898","8788","8988","7888","9888"}, "8888");
		TestCase case4 = new TestCase(new String[] {"0000"}, "8888");
		System.out.println(new OpenTheLock().openLock(case4.deadends, case4.target));
	}
	
	public int openLock(String[] deadends, String target) { 
		return openLock(List.of(deadends), target);
	}
	
	private int openLock(List<String> deadends, String target) {
		final String START_LOCK = "0000";
		if(target.equals(START_LOCK)) {
			return 0;
		} else if(deadends.contains(START_LOCK)) {
			return -1;
		} else {
			int minTurnNumber = 0;
			Queue<String> pendingLockCombinations = new ArrayDeque<>();
			Set<String> visitedLockCombinations = new HashSet<>();
			pendingLockCombinations.offer(START_LOCK);
			while(!pendingLockCombinations.isEmpty()) {
				int currentPendingLockNumbers = pendingLockCombinations.size();
				for(int p = 0; p < currentPendingLockNumbers; p++) {					
					String lock = pendingLockCombinations.poll();
					if(!visitedLockCombinations.contains(lock)) {					
						if(lock.equals(target)) {
							return minTurnNumber;
						}
						List<String> locks = getLockCombinations(lock); 
						for(int i = 0; i < locks.size(); i++) {
							if(!visitedLockCombinations.contains(locks.get(i)) && !deadends.contains(locks.get(i))) {
								pendingLockCombinations.offer(locks.get(i));
							}
						}
						visitedLockCombinations.add(lock);
					}
				}
				minTurnNumber++;
			}
			return -1;
		}
    }
	
	private List<String> getLockCombinations(String lock) {
		int combinationsNumber = 8;
		List<String> locks = new ArrayList<>(combinationsNumber);
		for(int i = 0, j = 0; i < combinationsNumber / 2; i++, j++) {
			char[] lockChrs = lock.toCharArray();
			if(lockChrs[j] == '0') {
				lockChrs[j] = '9';
				locks.add(new String(lockChrs));
				lockChrs[j] = '1';
				locks.add(new String(lockChrs));
			} else if(lockChrs[j] == '9') {
				lockChrs[j] = '8';
				locks.add(new String(lockChrs));
				lockChrs[j] = '0';
				locks.add(new String(lockChrs));
			} else {
				lockChrs[j]++;
				locks.add(new String(lockChrs));
				lockChrs[j] -= 2;
				locks.add(new String(lockChrs));
			}
		}
		return locks;
	}
	
	private static class TestCase {
		
		private String[] deadends;
		private String target;
		
		public TestCase(String[] deadends, String target) {
			super();
			this.deadends = deadends;
			this.target = target;
		}
		
		@Override
		public String toString() {
			return Arrays.toString(deadends) + " " + target;
		}
	}
}
