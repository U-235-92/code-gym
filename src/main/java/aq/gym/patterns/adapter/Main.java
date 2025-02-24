package aq.gym.patterns.adapter;

public class Main {

	public static void main(String[] args) {
		Cat cat = new Cat();
		System.out.println("Cat says...");
		cat.mew();
		Duck duck = new Duck();
		System.out.println("Duck says...");
		duck.makeNoise();
		Duck duckAdapter = new DuckAdapter(cat);
		System.out.println("Duck says... (=");
		duckAdapter.makeNoise();
	}

}
