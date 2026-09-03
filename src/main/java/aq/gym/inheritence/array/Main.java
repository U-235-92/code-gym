package aq.gym.inheritence.array;

public class Main {

	public static void main(String[] args) {
		Manager ceo = new Manager("CEO", 5885, 858);
		Employee emp = new Employee("EMP", 585);
		Manager[] managers = { ceo };
		Employee[] employees = managers;
		employees[0] = ceo;
		employees[0] = emp; // Throws ArrayStoreException
		System.out.println("Done!");
	}

}
