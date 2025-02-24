package aq.gym.patterns.state;

public class Main {

	public static void main(String[] args) {
		CoffeeMachine coffeeMachine = new CoffeeMachine();
		coffeeMachine.insertMoney();
		coffeeMachine.orderCoffee();
		coffeeMachine.takeCoffee();
	}
}
