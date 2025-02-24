package aq.gym.patterns.template_method;

public class VegSoup extends Soup {

	@Override
	protected void addIngredients() {
		System.out.println("Add some vegs");
	}

}
