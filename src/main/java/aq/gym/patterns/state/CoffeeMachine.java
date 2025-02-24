package aq.gym.patterns.state;

import lombok.Getter;
import lombok.Setter;

public class CoffeeMachine {

	@Getter
	private final State hasMoneyState;
	@Getter
	private final State noMoneyState;
	@Getter
	private final State soldState;
	@Setter
	private State currentState;
	
	public CoffeeMachine() {
		hasMoneyState = new HasMoneyState(this);
		noMoneyState = new NoMoneyState(this);
		soldState = new SoldState(this);
		currentState = noMoneyState;
	}
	
	public void insertMoney() {
		currentState.insert();
	}
	
	public void orderCoffee() {
		currentState.order();
	}
	
	public void takeCoffee() {
		currentState.take();
	}
}
