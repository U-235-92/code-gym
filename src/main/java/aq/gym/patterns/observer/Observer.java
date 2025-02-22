package aq.gym.patterns.observer;

@FunctionalInterface
public interface Observer {

	void notify(String line);
}
