package aq.gym.thread.count_down_latch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountDownLatchDemo {

	public static void main(String[] args) {
		CountDownLatch cdl1 = new CountDownLatch(5);
		CountDownLatch cdl2 = new CountDownLatch(5);
		CountDownLatch cdl3 = new CountDownLatch(5);
		CountDownLatch cdl4 = new CountDownLatch(5);
		ExecutorService es = Executors.newFixedThreadPool(2);
		System.out.println("Start!");
		es.execute(new CountDownLatchDemo().new MyThread(cdl1, "A"));
		es.execute(new CountDownLatchDemo().new MyThread(cdl2, "B"));
		es.execute(new CountDownLatchDemo().new MyThread(cdl3, "C"));
		es.execute(new CountDownLatchDemo().new MyThread(cdl4, "D"));
		try {
			cdl1.await();
			cdl2.await();
			cdl3.await();
			cdl4.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		es.shutdown();
		System.out.println("Stop!");
		
	}
	
	private class MyThread implements Runnable {
		
		private CountDownLatch cdl;
		private String name;
		
		public MyThread(CountDownLatch cdl, String name) {
			this.cdl = cdl;
			this.name = name;
		}
		
		public void run() {
			for (int i = 0; i < 5; i++) {
				System.out.printf("%s: %d%n", name, i + 1);
				sleep(500);
				cdl.countDown();
			}
		}
		
		private void sleep(int mills) {			
			try {
				TimeUnit.MILLISECONDS.sleep(mills);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
