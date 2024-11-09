package aq.gym.thread.car_stop;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CarStop {

	private final int PLACE_LIMIT = 2;
	private List<Car> cars = new ArrayList<Car>();

	public synchronized void tryPark(Car car) {
		System.out.println("The car " + car.getName() + " is trying to park.");
		sleep(200);
		try {
			while (cars.size() >= PLACE_LIMIT) {
				wait(5000);
				if(cars.size() >= PLACE_LIMIT)
					Thread.currentThread().interrupt();
			}
			cars.add(car);
			System.out.println("The car " + car.getName() + " parked. " + cars.size());
		} catch (InterruptedException e) {
			System.out.println("To long waiting for free place " + car.getName() + " left car stop.");
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
