package aq.gym.patterns.template_method;

public class Main {

	public static void main(String[] args) {
		Soup vegSoup = new VegSoup();
		Soup meatSoup = new MeatSoup();
		System.out.println("[Making veg soup]");
		vegSoup.makeSoup();
		System.out.println("[Making meat soup]");
		meatSoup.makeSoup();
	}

}
