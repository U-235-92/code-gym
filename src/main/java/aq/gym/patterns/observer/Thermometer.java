package aq.gym.patterns.observer;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Thermometer implements Observable {

	private List<Observer> observers;
	
	public Thermometer() {
		observers = new CopyOnWriteArrayList<Observer>();
	}
	
	@Override
	public void subscribe(Observer obs) {
		observers.add(obs);
		System.out.println("[" + obs + " subscribed]");
	}

	@Override
	public void unsubscribe(Observer obs) {
		synchronized(this) {
			observers.remove(obs);
			System.out.println("[" + obs + " unsubscribed]");
		}
	}

	public void measure() {
		Executor executor = Executors.newSingleThreadExecutor();
		executor.execute(() -> {
			while(true) {
				Random rnd = new Random();
				int temp = rnd.nextInt(30);
				observers.forEach(obs -> {
					obs.notify("Today: " + temp + " degrees");
					try {
						TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				});
			}
		});
	}
}
