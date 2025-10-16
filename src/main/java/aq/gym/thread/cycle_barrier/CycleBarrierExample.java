package aq.gym.thread.cycle_barrier;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CyclicBarrier;

public class CycleBarrierExample {

	public static void main(String[] args) {
		int[][] lines = { {1,1,1}, {2,2,2}, {3,3,3} };
		List<Integer> lineSums = new CopyOnWriteArrayList<>();
		Runnable doFinalSum = () -> System.out.printf("Total sum: %d", lineSums.stream().mapToInt(Integer::valueOf).sum());
		CyclicBarrier barrier = new CyclicBarrier(lines.length, doFinalSum);
		for(int[] line : lines) {
			new Thread(() -> {
				try {
					System.out.printf("%s is calculating a sum...%n", Thread.currentThread().getName());
					int sum = Arrays.stream(line).sum();
					lineSums.add(sum);
					barrier.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					e.printStackTrace();
				}
			}).start();
		}
	}
}
