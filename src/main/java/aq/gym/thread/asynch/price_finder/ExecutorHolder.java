package aq.gym.thread.asynch.price_finder;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExecutorHolder {

	private static int nCPU = Runtime.getRuntime().availableProcessors();
	private static int uCPU = 1; // 0...1 (The ratio of using CPU)
	private static int waitCalculateRatio = 100; //0...100 (The ratio of time to calculation to time to waiting = (1 + w/c)) 
	private static int maxNumberOfThreadsInPool = nCPU * uCPU * waitCalculateRatio;
	
	public static Executor getExecutor(int minThreadNumber) {
		Executor executor = Executors.newFixedThreadPool(Math.min(minThreadNumber, maxNumberOfThreadsInPool), run -> {
			Thread thread = new Thread(run);
			thread.setDaemon(true);
			return thread;
		});
		return executor;
	}
}
