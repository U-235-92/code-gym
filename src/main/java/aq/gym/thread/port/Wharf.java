package aq.gym.thread.port;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Wharf {
	
	@NonNull
	private String name;
	private volatile boolean isProcessShip = false;
	private Semaphore semaphore = new Semaphore(1);
	
	public void unload(Ship ship) {
		try {
			startProcessShip(ship);
			unloadProcess(ship);
			finishProcessShip(ship);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void unloadProcess(Ship ship) {
		System.out.println("Unload ship: " + ship.getName() + " by " + name); 
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void load(Ship ship) {
		try {
			startProcessShip(ship);
			loadProcess(ship);
			finishProcessShip(ship);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void loadProcess(Ship ship) {
		System.out.println("Load ship: " + ship.getName() + " by " + name); 
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void startProcessShip(Ship ship) throws InterruptedException {
		semaphore.acquire();
		isProcessShip = true;
		System.out.println("Start to process ship: " + ship.getName() + " by wharf: " + name);
		try {
			TimeUnit.MILLISECONDS.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void finishProcessShip(Ship ship) {
		System.out.println("Ship: " + ship.getName() + " processed succesfully by wharf: " + name + "!");
		semaphore.release();
		isProcessShip = false;
		try {
			TimeUnit.MILLISECONDS.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean isProcessShip() {
		return isProcessShip;
	}
}
