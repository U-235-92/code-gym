package aq.gym.patterns.command;

public class Fan {
	
	private Speed speed;
	
	public Fan() {
		speed = Speed.LOW;
	}
	
	public void low() {
		speed = Speed.LOW;
		System.out.println("Fan speed: " + Speed.LOW);
	}
	
	public void medium() {
		speed = Speed.MEDIUM;
		System.out.println("Fan speed: " + Speed.MEDIUM);
	}
	
	public void high() {
		speed = Speed.HIGH;
		System.out.println("Fan speed: " + Speed.HIGH);
	}
	
	public void off() {
		speed = Speed.OFF;
		System.out.println("Fan turned off");
	}
	
	public Speed getSpeed() {
		return speed;
	}
	
	public enum Speed {
		LOW, MEDIUM, HIGH, OFF;
	}
}
