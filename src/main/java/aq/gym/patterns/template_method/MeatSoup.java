package aq.gym.patterns.template_method;

public class MeatSoup extends Soup {

	@Override
	protected void addIngredients() {
		System.out.println("Add some vegs and some meat");
	}
}
