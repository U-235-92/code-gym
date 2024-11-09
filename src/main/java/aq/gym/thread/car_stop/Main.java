package aq.gym.thread.car_stop;

public class Main {

	public static void main(String[] args) {
		Car bmw = new Car("BMW");
		Car honda = new Car("Honda");
		Car lamborghini = new Car("Lamborghini");
		Car ferrari = new Car("Ferrari");
		CarStop carStop = new CarStop();
		Thread bmwThread = new Thread(() -> carStop.tryPark(bmw));
		Thread hondaThread = new Thread(() -> carStop.tryPark(honda));
		Thread lamborghiniThread = new Thread(() -> carStop.tryPark(lamborghini));
		Thread ferrariThread = new Thread(() -> carStop.tryPark(ferrari));
		bmwThread.start();
		hondaThread.start();
		lamborghiniThread.start();
		ferrariThread.start();
	}

}
