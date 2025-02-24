package aq.gym.patterns.state;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SoldState implements State {

	private CoffeeMachine coffeeMachine;
	
	@Override
	public void insert() {
		System.out.println("Take your coffee please");
	}

	@Override
	public void order() {
		System.out.println("Take your coffee please");
	}

	@Override
	public void take() {
		System.out.println("Your coffee is ready!");
		coffeeMachine.setCurrentState(coffeeMachine.getNoMoneyState());
	}
}
