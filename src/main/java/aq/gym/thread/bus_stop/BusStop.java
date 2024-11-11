package aq.gym.thread.bus_stop;

import java.util.concurrent.Semaphore;

public class BusStop {

	private Semaphore semaphore;
	private String name;
	
	public BusStop(String name, int allowedBumberBuses) {
		this.name = name;
		semaphore = new Semaphore(allowedBumberBuses);
	}
	
	public boolean isAbleReceiveBus() {
		return semaphore.tryAcquire(1);
	}

	public void release() {
		semaphore.release();
	}
	
	public String getName() {
		return name;
	}
}
