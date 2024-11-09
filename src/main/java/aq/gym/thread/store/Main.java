package aq.gym.thread.store;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		Customer a = new Customer("A");
		Customer b = new Customer("B");
		Customer c = new Customer("C");
		Customer d = new Customer("D");
		Customer e = new Customer("E");
		Customer f = new Customer("F");
		Customer g = new Customer("G");
		Customer h = new Customer("H");
		Customer i = new Customer("I");
		Customer j = new Customer("J");
		Customer k = new Customer("K");
		Customer l = new Customer("L");
		Customer m = new Customer("M");
		CashDesk cashDesk1 = new CashDesk("CASH DESK #1", List.of(a, b, c, d, e, f, j, g));
		CashDesk cashDesk2 = new CashDesk("CASH DESK #2", List.of(h, i));
		CashDesk cashDesk3 = new CashDesk("CASH DESK #3", List.of(k, l, m));
		Store store = new Store(List.of(cashDesk1, cashDesk2, cashDesk3));
		System.out.println("Store has: " + store.getTotalNumberCustomers() + " persons");
		store.service();
	}
}
