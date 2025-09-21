package aq.gym.work_with_objects.method_params;

public class Main {

	public static void main(String[] args) {
		Toy doll = new Toy("Doll");
		Toy car = new Toy("Car");
		Child alice = new Child("Alice", doll);
		Child bob = new Child("Bob", car);
		System.out.println("Before trying change toys Alice has " + alice.toy + ", Bob has " + bob.toy);
		tryChangeToys(alice, bob);
		System.out.println("After trying change toys Alice has " + alice.toy + ", Bob has " + bob.toy);
		System.out.println("Before trying swap toy car and toy doll");
		System.out.println("Car = " + car + " Doll = " + doll);
		trySwapToys(car, doll);
		System.out.println("After trying swap toy car and toy doll");
		System.out.println("Car = " + car + " Doll = " + doll);
	}
	
	private static void tryChangeToys(Child a, Child b) {
		Toy tmp = a.toy;
		a.toy = b.toy;
		b.toy = tmp;
	}
	
	private static void trySwapToys(Toy a, Toy b) {
		Toy tmp = a;
		a = b;
		b = tmp;
	}
}
