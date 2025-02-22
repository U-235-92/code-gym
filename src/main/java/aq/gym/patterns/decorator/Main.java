package aq.gym.patterns.decorator;

public class Main {

	public static void main(String[] args) {
		Milkshake vanilaBourbon = new VanilaBourbon();
		vanilaBourbon = new Whip(vanilaBourbon);
		vanilaBourbon = new Whip(vanilaBourbon);
		vanilaBourbon = new Cinnamon(vanilaBourbon);
		Milkshake perfectChocolate = new PerfectChocolate();
		Milkshake strawberryCream = new StrawberryCream();
		strawberryCream = new Whip(strawberryCream);
		print(perfectChocolate);
		print(strawberryCream);
		print(vanilaBourbon);
	}
	
	public static void print(Milkshake milkshake) {
		System.out.println("Milkshake description: " + milkshake.getDescription() + ", Total price: " + milkshake.getPrice());
	}
}
