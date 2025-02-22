package aq.gym.patterns.observer;

public interface Observable {

	public void subscribe(Observer obs);
	public void unsubscribe(Observer obs);
}
