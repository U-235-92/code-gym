package aq.gym.thread.store;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Store {

	private List<CashDesk> cashDesks = new ArrayList<CashDesk>();
	private AtomicInteger numberServicedPersons = new AtomicInteger(0);
	private int totalPersons;
	
	public void addCashDesks(List<CashDesk> cashDesks) {
		totalPersons = cashDesks.stream().map(CashDesk::getQueueSize).reduce(Integer::sum).get();
		this.cashDesks.addAll(cashDesks);
	}
	
	public void service() {
		for(CashDesk cashDesk : cashDesks) {			
			Thread cashDeskThread = new Thread(() -> cashDesk.servicePersons());
			cashDeskThread.start();
		}
	}
	
	public List<CashDesk> getCashDesks() {
		return List.copyOf(cashDesks);
	}
	
	
	public void incrementCountServicedPersons() {
		numberServicedPersons.incrementAndGet();
	}
	
	public int getTotalNumberOfPersons() {
		return totalPersons;
	}

	public int getCountServicedPersons() {
		return numberServicedPersons.get();
	}
}
