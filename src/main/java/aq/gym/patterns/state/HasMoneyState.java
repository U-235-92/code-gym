package aq.gym.patterns.state;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HasMoneyState implements State {

	private CoffeeMachine coffeeMachine;
	
	@Override
	public void insert() {
		System.out.println("You can't insert the money again!");
	}

	@Override
	public void order() {
		System.out.println("Wait please. Your coffee is making.");
		coffeeMachine.setCurrentState(coffeeMachine.getSoldState());
	}
	
	@Override
	public void take() {
		System.out.println("You have to order coffee!");
	}
}
