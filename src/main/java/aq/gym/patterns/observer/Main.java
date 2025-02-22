package aq.gym.patterns.observer;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		Sensor mx585 = new Sensor("mx585");
		Sensor hj825 = new Sensor("hj825");
		Sensor km456 = new Sensor("km456");
		Thermometer thermometer = new Thermometer();
		thermometer.subscribe(mx585);
		thermometer.subscribe(hj825);
		thermometer.subscribe(km456);
		thermometer.measure();
		Thread.sleep(3000);
		thermometer.unsubscribe(km456);
	}

}
