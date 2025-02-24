package aq.gym.work_with_objects.clone;

public class Main {

	public static void main(String[] args) throws CloneNotSupportedException {
		Address address = new Address("Japan", "Tokyo");
		Person mariko = new Person(1, "Mariko", address);
		Person junko = mariko.clone();
		System.out.println("Orig:  " + mariko);
		System.out.println("Clone: " + junko);
		junko.setId(2);
		junko.setName("Junko");
		junko.setAddress(new Address("Kusari", "Honshu"));
		System.out.println("After: " + junko);
		
	}

}
