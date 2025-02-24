package aq.gym.patterns.state;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NoMoneyState implements State {

	private CoffeeMachine coffeeMachine;
	
	@Override
	public void insert() {
		System.out.println("The money inserted. You can order coffee.");
		coffeeMachine.setCurrentState(coffeeMachine.getHasMoneyState());
	}

	@Override
	public void order() {
		System.out.println("Insert the money!");
	}

	@Override
	public void take() {
		System.out.println("Insert the money!");
	}
}
