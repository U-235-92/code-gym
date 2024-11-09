package aq.gym.thread.store;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		Person a = new Person("A");
		Person b = new Person("B");
		Person c = new Person("C");
		Person d = new Person("D");
		Person e = new Person("E");
		Person f = new Person("F");
		Person g = new Person("G");
		Person h = new Person("H");
		Person i = new Person("I");
		Person j = new Person("J");
		Person k = new Person("K");
		Person l = new Person("L");
		Person m = new Person("M");
		Store store = new Store();
		CashDesk cashDesk1 = new CashDesk("CASH DESK #1", List.of(a, b, c, d, e), store);
		CashDesk cashDesk2 = new CashDesk("CASH DESK #2", List.of(f, g, h, i), store);
		CashDesk cashDesk3 = new CashDesk("CASH DESK #3", List.of(j, k, l, m), store);
		store.addCashDesks(List.of(cashDesk1, cashDesk2, cashDesk3));
		System.out.println("Store has: " + store.getTotalNumberOfPersons() + " persons");
		store.service();
	}

}
