package aq.gym.patterns.template_method;

public abstract class Soup {

	public final void makeSoup() {
		boilWater();
		addIngredients();
	}
	
	protected void boilWater() {
		System.out.println("Boil water");
	}
	
	protected abstract void addIngredients();
}
